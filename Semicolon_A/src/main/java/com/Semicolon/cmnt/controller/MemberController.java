package com.Semicolon.cmnt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.Semicolon.cmnt.command.MemberModifyCommand;
import com.Semicolon.cmnt.command.MemberRegistCommand;
import com.Semicolon.cmnt.dto.EngineerVO;
import com.Semicolon.cmnt.dto.MemberVO;
import com.Semicolon.cmnt.service.EngineerService;
import com.Semicolon.cmnt.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService service;
    private final EngineerService engineerService;

    @Autowired
    public MemberController(MemberService service, EngineerService engineerService) {
        this.service = service;
        this.engineerService = engineerService;
    }

    // ---------------- View 기반 ----------------
    @GetMapping("/main")
    public void main() {}

    @GetMapping("/regist")
    public void registForm() {}

    @GetMapping("/idCheck")
    @ResponseBody
    public String idCheck(String user_id) throws Exception {
        String result = "duplicated";
        MemberVO member = service.getMember(user_id);
        if (member == null) result = "";
        return result;
    }

    @PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
    public ModelAndView regist(MemberRegistCommand regCommand, ModelAndView mnv) {
        String url = "/member/regist_success";
        try {
            // DB 저장
            MemberVO member = regCommand.toMemberVO();
            service.regist(member);

            EngineerVO engineer = regCommand.toEngineerVO();
            engineerService.registerEngineer(engineer);

        } catch (Exception e) {
            url = "/error/500";
            e.printStackTrace();
        }
        mnv.setViewName(url);
        return mnv;
    }

    @GetMapping("/detail")
    public ModelAndView detail(String id, ModelAndView mnv) throws Exception {
        String url = "/member/detail";
        MemberVO member = service.getMember(id);
        mnv.addObject("member", member);
        mnv.setViewName(url);
        return mnv;
    }

    @GetMapping("/modify")
    public ModelAndView modifyForm(String id, ModelAndView mnv) throws Exception {
        String url = "/member/modify";
        MemberVO member = service.getMember(id);
        mnv.addObject("member", member);
        mnv.setViewName(url);
        return mnv;
    }

    @PostMapping(value = "/modify", produces = "text/plain;charset=utf-8")
    public ModelAndView modify(MemberModifyCommand modifyCommand, ModelAndView mnv) throws Exception {
        String url = "/member/modify_success";
        MemberVO member = modifyCommand.toMemberVO();
        service.modify(member);
        mnv.addObject("id", member.getUser_id());
        mnv.setViewName(url);
        return mnv;
    }

    @GetMapping(value = "/remove")
    public ModelAndView remove(String id, ModelAndView mnv) throws Exception {
        String url = "/member/remove_success";
        service.remove(id);
        mnv.setViewName(url);
        return mnv;
    }

    // ---------------- React API(JSON) ----------------

    /* 닉네임 중복 체크
    @ResponseBody
    @GetMapping("/api/members/checkName")
    public Map<String, Boolean> checkName(@RequestParam String name) throws Exception {
        boolean available = (service.getMember(name) == null);
        return Map.of("available", available);
    }
    */

    // 회원가입
    @ResponseBody
    @PostMapping("/api/members/signup")
    public Map<String, Object> signup(@RequestBody MemberVO member) {
        try {
            service.regist(member);

            EngineerVO engineer = new EngineerVO();
            engineer.setUser_id(member.getUser_id());
            engineerService.registerEngineer(engineer);

            return Map.of("success", true, "message", "회원가입 성공");
        } catch (Exception e) {
            return Map.of("success", false, "message", "회원가입 실패: " + e.getMessage());
        }
    }
}
