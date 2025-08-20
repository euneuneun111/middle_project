// src/main/java/com/Semicolon/org/controller/CreateOrgController.java
package com.semicolon.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semicolon.dto.CreateOrgDTO;
import com.semicolon.dto.MemberVO;
import com.semicolon.service.CreateOrgService;

@Controller
@RequestMapping("/org")
public class CreateOrgController {

    private CreateOrgService createOrgService;

    public CreateOrgController(CreateOrgService createOrgService) {
        this.createOrgService = createOrgService;
    }
    
    @GetMapping("/create")
    public String showCreateOrgPage() {
        return "organization/createOrg";
    }

    @PostMapping("/create")
    public String createOrganization(CreateOrgDTO org, HttpSession session) {
        
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        org.setOrManagerId(loginUser.getUser_id());
        
        createOrgService.createOrganization(org);

        return "redirect:/org/detail/" + org.getOrId();
    }
}