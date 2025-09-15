package com.Semicolon.pms.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession; // HttpSession import
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Semicolon.cmnt.dto.MemberVO; // MemberVO import
import com.Semicolon.pms.dto.IssueDto;
import com.Semicolon.pms.dto.IssueReplyDTO; // IssueReplyDTO import
import com.Semicolon.pms.dto.TaskDto;
import com.Semicolon.pms.service.IssueReplyService; // IssueReplyService import
import com.Semicolon.pms.service.IssueService;
import com.Semicolon.pms.service.TaskService;
import com.Semicolon.command.PageMaker;
import java.util.ArrayList;
@Controller
@RequestMapping("/main/project") // 기본 경로를 /main으로 변경하여 URL 일관성 확보
public class IssueController {

    private final IssueService issueService;
    private final TaskService taskService;
    private final IssueReplyService issueReplyService; // 댓글 처리를 위한 서비스

    public IssueController(IssueService issueService, TaskService taskService, IssueReplyService issueReplyService) {
        this.issueService = issueService;
        this.taskService = taskService;
        this.issueReplyService = issueReplyService;
    }

    // 이슈 목록 페이지
    @GetMapping("/{projectId}/issuelist")
    public String getIssueList(
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "perPageNum", defaultValue = "10") int perPageNum,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               Model model) throws SQLException {
    	String projectId = "PJ-001";
    	
        PageMaker pageMaker = new PageMaker();
        pageMaker.setProjectId(projectId);
        pageMaker.setPage(page);
        pageMaker.setPerPageNum(perPageNum);
        pageMaker.setKeyword(keyword);

        pageMaker.setTotalCount(issueService.getTotalCount(pageMaker));
        
        List<IssueDto> issueList = issueService.getIssueList(pageMaker);
        
        // 3. 이제 this.taskService로 정상적으로 접근 가능
        List<TaskDto> taskList = new ArrayList<>();
        
        TaskDto task1 = new TaskDto();
        task1.setTaskId("TSK-001");
        task1.setTaskTitle("TSK-001");
        taskList.add(task1);

        TaskDto task2 = new TaskDto();
        task2.setTaskId("TSK-002");
        task2.setTaskTitle("TSK-002");
        taskList.add(task2);
        
        TaskDto task3 = new TaskDto();
        task3.setTaskId("TSK-003");
        task3.setTaskTitle("TSK-003");
        taskList.add(task3);

        model.addAttribute("issueList", issueList);
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("projectId", projectId);
        model.addAttribute("taskList", taskList);

        return "organization/pms/issue/issuelist";
    }

    @PostMapping("/{projectId}/issuelist")
    @ResponseBody
    public ResponseEntity<Map<String, String>> createIssue(
            @PathVariable("projectId") String projectId,
            @RequestBody IssueDto issue,
            HttpSession session) { // ✅ HttpSession 파라미터 추가

        Map<String, String> response = new HashMap<>();
        try {
            // ✅ 1. 세션에서 로그인 사용자 정보 가져오기
            MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
            if (loginUser == null) {
                response.put("message", "로그인이 필요합니다.");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
            
            // ✅ 2. DTO에 필요한 값 설정
            issue.setProjectId(projectId);
            issue.setIssueCreatorId(loginUser.getUser_id()); // 작성자 ID 설정
            issue.setIssueManagerId(loginUser.getUser_id()); // 담당자 ID를 작성자 ID와 동일하게 설정

            // ✅ 3. 서비스 호출
            issueService.createNewIssue(issue);
            
            response.put("message", "이슈가 성공적으로 등록되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.put("message", "이슈 등록 중 오류가 발생했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // 이슈 상세 페이지
    @GetMapping("/{projectId}/issuelist/{issueId}")
    public String getIssueDetail(@PathVariable String projectId, @PathVariable String issueId, Model model) throws SQLException {
        IssueDto issue = issueService.getIssueById(issueId);
        model.addAttribute("issue", issue);
        model.addAttribute("projectId", projectId);
        return "organization/pms/issue/issuedetail";
    }

    // --- API 메소드 (JavaScript fetch와 연동) ---

    // 이슈 수정 (PUT)
    @PutMapping("/issue/update") // JSP의 fetch 경로와 일치
    @ResponseBody
    public ResponseEntity<Map<String, String>> updateIssue(@RequestBody IssueDto issue) {
        Map<String, String> response = new HashMap<>();
        try {
            issueService.updateIssue(issue);
            response.put("message", "이슈가 성공적으로 수정되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.put("error", "이슈 수정 중 오류 발생");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 이슈 삭제 (DELETE)
    @DeleteMapping("/issue/{issueId}") // JSP의 fetch 경로와 일치
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteIssue(@PathVariable String issueId) {
        Map<String, String> response = new HashMap<>();
        try {
            issueService.deleteIssue(issueId);
            response.put("message", "이슈가 삭제되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.put("error", "이슈 삭제 중 오류 발생");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 댓글 추가 (POST)
    @PostMapping("/reply") // JSP의 fetch 경로와 일치
    @ResponseBody
    public ResponseEntity<Map<String, String>> addReply(@RequestBody IssueReplyDTO reply, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            response.put("error", "로그인이 필요합니다.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        reply.setUserId(loginUser.getUser_id()); // 하드코딩된 사용자 ID를 실제 세션 정보로 변경
        
        try {
            issueReplyService.insertReply(reply);
            response.put("message", "댓글이 추가되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.put("error", "댓글 추가 중 오류 발생");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 댓글 삭제 (DELETE)
    @DeleteMapping("/issue/reply/{replyNumber}") // JSP의 fetch 경로와 일치
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteReply(@PathVariable String replyNumber) {
        Map<String, String> response = new HashMap<>();
        try {
            issueReplyService.deleteReply(replyNumber);
            response.put("message", "댓글이 삭제되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.put("error", "댓글 삭제 중 오류 발생");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/{projectId}/issues")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getIssueListForReact(
            @PathVariable("projectId") String projectId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "perPageNum", defaultValue = "10") int perPageNum,
            @RequestParam(value = "keyword", required = false) String keyword) {

        Map<String, Object> response = new HashMap<>();
        try {
            PageMaker pageMaker = new PageMaker();
            pageMaker.setProjectId(projectId);
            pageMaker.setPage(page);
            pageMaker.setPerPageNum(perPageNum);
            pageMaker.setKeyword(keyword);

            pageMaker.setTotalCount(issueService.getTotalCount(pageMaker));
            List<IssueDto> issueList = issueService.getIssueList(pageMaker);

            response.put("issueList", issueList);
            response.put("pageMaker", pageMaker); // 페이지 정보도 함께 전달

            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.put("error", "이슈 목록을 불러오는 데 실패했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * [API] 특정 이슈의 상세 정보를 JSON으로 반환 (React용)
     */
    @GetMapping("/api/issue/{issueId}")
    @ResponseBody
    public ResponseEntity<?> getIssueDetailForReact(@PathVariable String issueId) {
        try {
            IssueDto issue = issueService.getIssueById(issueId);
            if (issue != null) {
                return new ResponseEntity<>(issue, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Issue not found", HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error fetching issue details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}