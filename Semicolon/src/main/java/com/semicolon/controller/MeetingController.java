package com.semicolon.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.josephoconnell.html.HTMLInputFilter;
import com.semicolon.command.MeetingModifyCommand;
import com.semicolon.command.MeetingRegistCommand;
import com.semicolon.command.PageMaker;
import com.semicolon.dao.MeetingDAO;
import com.semicolon.dto.MeetingVO;
import com.semicolon.service.MeetingService;

@Controller
@RequestMapping("/organization/meeting")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;

	@Autowired
    private MeetingDAO meetingDAO;
    
    @GetMapping("/list")
    public ModelAndView meetingList(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws SQLException {
    	String url = "/organization/meeting/list";

        List<MeetingVO> meetingList = meetingService.getMeetingList(pageMaker);

        mnv.addObject("meetingList", meetingList);
        mnv.addObject("pageMaker",pageMaker);
        mnv.setViewName(url);
        
        return mnv;
        
    }

	// 회의 등록 폼
	@GetMapping("/regist")
	public void registForm() {
	}

	@PostMapping("/regist")
	public String registPost(MeetingRegistCommand regCommand, ModelAndView mnv) throws Exception {
		String url = "/organization/meeting/regist_success";

		MeetingVO meeting = regCommand.toMeetingVO();

		meeting.setTitle(HTMLInputFilter.htmlSpecialChars(meeting.getTitle()));

		meetingService.registMeeting(meeting);
		return url;
	}

	@GetMapping("/detail")
	public ModelAndView detail(int id, ModelAndView mnv) throws Exception {
		String url = "/organization/meeting/detail";

		MeetingVO meeting = meetingService.getMeetingById(id);

		mnv.addObject("meeting", meeting);
		mnv.setViewName(url);
		return mnv;

	}

	@GetMapping("/modify")
	public void modifyForm(int id, Model model) throws Exception {
		MeetingVO meeting = meetingService.getMeetingById(id);

		model.addAttribute("meeting", meeting);
	}

	@PostMapping("/modify")

	public ModelAndView modifyPost(MeetingModifyCommand modifyCommand, ModelAndView mnv) throws Exception {

		String url = "/organization/meeting/modify_success";

		MeetingVO meeting = modifyCommand.toMeetingVO();
		meeting.setTitle(HTMLInputFilter.htmlSpecialChars(meeting.getTitle()));

		meetingService.modifyMeeting(meeting);

		mnv.addObject("id", meeting.getId());
		mnv.setViewName(url);
		
		return mnv;
	}
	
	
	@GetMapping("/remove")
	public String remove(int id)throws Exception{
		String url="/organization/meeting/remove_success";		
		
		meetingService.removeMeeting(id);
		
		return url;
	}
	

}
