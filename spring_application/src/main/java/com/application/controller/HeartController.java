package com.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.service.MemberService;

@RestController
@RequestMapping("/heart")
public class HeartController {

    @Autowired
    private MemberService memberService;
    
    

    @PostMapping("/increase")
    public ResponseEntity<Void> increase(@RequestBody Map<String, String> map) {
        String id = map.get("id");
        try {
            memberService.increaseHeartCount(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/decrease")
    public ResponseEntity<Void> decrease(@RequestBody Map<String, String> map) {
        String id = map.get("id");
        try {
            memberService.decreaseHeartCount(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

