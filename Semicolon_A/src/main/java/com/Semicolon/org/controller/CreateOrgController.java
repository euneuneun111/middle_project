package com.Semicolon.org.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller; // 이 어노테이션은 유지
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Semicolon.org.dto.CreateOrgDTO;
import com.Semicolon.org.service.CreateOrgService;

@Controller
@RequestMapping("/org")
public class CreateOrgController {

    private CreateOrgService createOrgService;

    // 생성자를 통한 의존성 주입
    public CreateOrgController(CreateOrgService createOrgService) {
        this.createOrgService = createOrgService;
    }
    
    // ... 나머지 메소드는 그대로 유지 ...
    @GetMapping("/create")
    public String showCreateOrgPage() {
        return "organization/createOrg";
    }

    @PostMapping("/create")
    public ModelAndView createOrganization(
            CreateOrgDTO organization,
            @RequestParam(value = "memberInvite", required = false) String memberInviteStr,
            HttpSession session
    ) {
        String publicStatus = "공개".equals(organization.getOrIsPublic()) ? "Y" : "N";
        organization.setOrIsPublic(publicStatus);
        
        String loggedInUserId = (String) session.getAttribute("userId");
        if (loggedInUserId != null) {
            organization.setMemberId(loggedInUserId);
        } else {
            ModelAndView errorMav = new ModelAndView("redirect:/login");
            return errorMav;
        }

        organization.setMemberRole("관리자");

        List<String> invitedMembersList = null;
        if (memberInviteStr != null && !memberInviteStr.isEmpty()) {
            invitedMembersList = Arrays.asList(memberInviteStr.split(","));
        }
        organization.setInvitedMembers(invitedMembersList);

        createOrgService.createOrganization(organization);

        ModelAndView mav = new ModelAndView("redirect:/org/myorg");
        return mav;
    }
}