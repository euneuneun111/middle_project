package com.Semicolon.commons.controller;

import java.sql.SQLException;

import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Semicolon.cmnt.dto.MemberVO;
import com.Semicolon.cmnt.service.MemberService;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/commons") // React baseURL과 일치
public class CommonsController {

    private final MemberService memberService;

    @Autowired
    public CommonsController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody MemberVO loginUser, HttpSession session) throws SQLException {
        MemberVO member = memberService.getMember(loginUser.getUser_id());
        if (member != null && loginUser.getUser_pwd().equals(member.getUser_pwd())) {
            session.setAttribute("loginUser", member);
            return Map.of("success", true, "message", "로그인 성공");
        } else {
            return Map.of("success", false, "message", "아이디 또는 비밀번호 오류");
        }
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        return Map.of("success", true, "message", "로그아웃 완료");
    }

    @GetMapping("/check-session")
    public Map<String, Object> checkSession(HttpSession session) {
        Object user = session.getAttribute("loginUser");
        if (user != null) {
            return Map.of("authenticated", true);
        } else {
            return Map.of("authenticated", false);
        }
    }
}
