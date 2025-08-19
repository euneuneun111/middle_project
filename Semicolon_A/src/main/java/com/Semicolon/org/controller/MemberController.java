package com.Semicolon.org.controller;

import com.Semicolon.org.dto.MemberDTO;
import com.Semicolon.org.service.MemberService;
import org.springframework.stereotype.Controller; // 이 어노테이션은 유지
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;
    
    // 생성자 주입 방식
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // ... 기존 메서드들 그대로 유지 ...
    @GetMapping("/profile")
    public String viewMemberProfile(@RequestParam("memberId") int memberId, Model model) {
        try {
            MemberDTO member = memberService.getMemberById(memberId);
            model.addAttribute("member", member);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "회원 정보를 불러오는 중 오류가 발생했습니다.");
            return "errorPage";
        }
        return "member-profile";
    }
}