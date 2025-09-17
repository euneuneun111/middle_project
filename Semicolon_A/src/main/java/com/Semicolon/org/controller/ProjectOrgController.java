package com.Semicolon.org.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Semicolon.cmnt.service.MemberService;
import com.Semicolon.org.command.ProjectCreateCommand;
import com.Semicolon.org.dto.ProjectOrgDTO;
import com.Semicolon.org.service.ProjectOrgService;

@Controller
@RequestMapping("/org/myproject")
public class ProjectOrgController {

    @Autowired
    private ProjectOrgService projectOrgService;
    
    @Autowired
    private MemberService memberService;

    /**
     * 프로젝트 목록 조회
     */
    @GetMapping("/list")
    public String projectList(Model model) {
        List<ProjectOrgDTO> projectList = projectOrgService.getProjectList();
        model.addAttribute("projectList", projectList);
        return "organization/myproject"; 
    }

    /**
     * 프로젝트 생성 페이지 이동
     */
    @GetMapping("/create")
    public String createProjectForm() {
        return "organization/projectcreate"; 
    }

    /**
     * 프로젝트 생성 처리
     */
    @PostMapping("/create")
    @ResponseBody
    public String createProject(@ModelAttribute ProjectCreateCommand command) throws Exception {
        ProjectOrgDTO project = command.toProjectOrgDTO();
        
        // 시퀀스 조회
        int seq = projectOrgService.getProjectSeq(); // Mapper의 getProjectSeq 호출
        String projectId = String.format("PRJ-%03d", seq); // PRJ-001, PRJ-002 ...
        project.setProjectId(projectId);

        projectOrgService.insertProject(project);

        return "<script>alert('프로젝트가 생성되었습니다.'); opener.location.reload(); window.close();</script>";
    }

    /**
     * 프로젝트 상세
     */
    @GetMapping("/{projectId}")
    public String projectDetail(@PathVariable("projectId") String projectId, Model model) {
    	ProjectOrgDTO project = projectOrgService.getProjectDetail(projectId);
        model.addAttribute("project", project);
        return "project/projectDetail"; 
    }
    
    @GetMapping("/search")
    @ResponseBody
    public List<String> searchNicknames(@RequestParam String keyword) throws SQLException {
        System.out.println("keyword = " + keyword);
        List<String> list = memberService.findNicknamesByKeyword(keyword);
        System.out.println("result = " + list);
        return list; // JSON 배열로 반환됨
    }
}
