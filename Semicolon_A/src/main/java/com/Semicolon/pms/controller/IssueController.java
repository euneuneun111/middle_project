package com.Semicolon.pms.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller; // 유지
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Semicolon.pms.dto.IssueDto;
import com.Semicolon.pms.service.IssueService;
import com.Semicolon.command.PageMaker;

@Controller
@RequestMapping("/main/project")
public class IssueController {

    private IssueService issueService;

    // 생성자 주입
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // ... 나머지 메소드는 그대로 유지 ...
    @GetMapping("/{projectId}/issuelist")
    public String getIssueList(@PathVariable("projectId") String projectId,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "perPageNum", defaultValue = "10") int perPageNum,
                               Model model) {
        
        System.out.println("------------------ Debugging Start ------------------");
        System.out.println("projectId received from URL: " + projectId);
        System.out.println("Current page: " + page);
        System.out.println("Items per page: " + perPageNum);
        System.out.println("----------------------------------------------");

        try {
            PageMaker pageMaker = new PageMaker();
            pageMaker.setProjectId(projectId);
            pageMaker.setPage(page);
            pageMaker.setPerPageNum(perPageNum);

            int totalCount = issueService.getTotalCountByProjectId(pageMaker.getProjectId(), pageMaker);
            
            pageMaker.setTotalCount(totalCount);
            
            List<IssueDto> issueList = issueService.getIssueListByProjectId(pageMaker.getProjectId(), pageMaker);
            
            model.addAttribute("issueList", issueList);
            model.addAttribute("pageMaker", pageMaker);
            model.addAttribute("projectId", projectId);

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "An error occurred while loading the issue list.");
            return "errorPage";
        }
        return "organization/pms/issue/issuelist";
    }

    // ... 나머지 메소드들 ...
}