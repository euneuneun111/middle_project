package com.semicolon.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.semicolon.dto.AdminVO;
import com.semicolon.dto.FundingVO;
import com.semicolon.dto.MeetingVO;
import com.semicolon.service.AdminService; // MemberService 가 아니라 AdminService 일 가능성 높음
import com.semicolon.command.AdminRegistCommand;
import com.semicolon.command.PageMaker;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    public ModelAndView adminList(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws SQLException {
        String url = "/admin/list";
		List<AdminVO> adminList = adminService.getAdminList(pageMaker);
		
        mnv.addObject("adminList", adminList);
        mnv.addObject("pageMaker",pageMaker);
        mnv.setViewName(url);

        return mnv;
    }
    
    @GetMapping("/detail")
	public ModelAndView detail(int id, ModelAndView mnv) throws Exception {
		String url = "/admin/detail";

		AdminVO admin = adminService.getAdminById(id);

		mnv.addObject("admin", admin);
		mnv.setViewName(url);
		return mnv;
    }
	
    
    @GetMapping("/remove")
	public String remove(int id)throws Exception{
		String url="/admin/remove_success";		
		
		adminService.removeAdmin(id);
		
		return url;
	}
}
