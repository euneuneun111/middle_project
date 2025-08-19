package com.Semicolon.org.controller;

import org.springframework.stereotype.Controller; // 이 어노테이션은 유지
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Semicolon.org.service.OrgDetailService;
import com.Semicolon.org.dto.OrgDetailDTO;

@Controller
@RequestMapping("/org")
public class OrgDetailController {
    
    private OrgDetailService orgDetailService;
    
    // 생성자를 통한 의존성 주입
    public OrgDetailController(OrgDetailService orgDetailService) {
        this.orgDetailService = orgDetailService;
    }

    // ... 기존 메서드들 그대로 유지 ...
    @GetMapping("/detail")
    public String orgDetail(@RequestParam(value = "orId", required = false) String orId, Model model) {
        
        OrgDetailDTO org;

        if (orId == null) {
            org = new OrgDetailDTO();
            org.setOrId("TEMP_ORG_ID");
            org.setOrName("임시 조직명");
            org.setOrIntroduce("테스트를 위한 임시 조직입니다.");
        } else {
            org = orgDetailService.getOrgDetailByOrId(orId);
        }

        if (org != null) {
            model.addAttribute("org", org);
        } else {
            return "redirect:/error";
        }
        
        return "organization/orgDetail";
    }

    @PostMapping("/update")
    public String updateOrg(OrgDetailDTO orgDetailDTO, RedirectAttributes redirectAttributes) {
        int result = orgDetailService.updateOrg(orgDetailDTO);
        
        if (result > 0) {
            redirectAttributes.addAttribute("orId", orgDetailDTO.getOrId());
            return "redirect:/org/detail";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/delete")
    public String deleteOrg(@RequestParam("orId") String orId) {
        int result = orgDetailService.deleteOrg(orId);
        
        if (result > 0) {
            return "redirect:/org/list";
        } else {
            return "redirect:/error";
        }
    }
}