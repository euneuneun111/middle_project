package com.Semicolon.funding.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Semicolon.command.MeetingModifyCommand;
import com.Semicolon.command.MeetingRegistCommand;
import com.Semicolon.command.PageMaker;
import com.Semicolon.dao.MeetingDAO;
import com.Semicolon.dto.MeetingVO;
import com.Semicolon.service.MeetingService;
import com.josephoconnell.html.HTMLInputFilter;

@Controller
@RequestMapping("/organization/{projectId}/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingDAO meetingDAO;

    @GetMapping("/list")
    public ModelAndView meetingList(
            @PathVariable("projectId") String projectId,
            @ModelAttribute PageMaker pageMaker,
            ModelAndView mnv) throws SQLException {

        String url = "organization/meeting/list";

        List<MeetingVO> meetingList = meetingService.getMeetingList(pageMaker);

        mnv.addObject("meetingList", meetingList);
        mnv.addObject("pageMaker", pageMaker);
        mnv.addObject("projectId", projectId); // ✅ JSP에서 쓸 수 있도록 전달
        mnv.setViewName(url);

        return mnv;
    }

    // 회의 등록 폼
    @GetMapping("/regist")
    public String registForm(@PathVariable("projectId") String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "/organization/meeting/regist";
    }

    @PostMapping("/regist")
    public String registPost(
            @PathVariable("projectId") String projectId,
            MeetingRegistCommand regCommand,
            Model model) throws Exception {

        String url = "/organization/meeting/regist_success";

        MeetingVO meeting = regCommand.toMeetingVO();
        meeting.setTitle(HTMLInputFilter.htmlSpecialChars(meeting.getTitle()));

        meetingService.registMeeting(meeting);

        model.addAttribute("projectId", projectId);
        return url;
    }

    @GetMapping("/detail")
    public ModelAndView detail(
            @PathVariable("projectId") String projectId,
            int id,
            ModelAndView mnv) throws Exception {

        String url = "/organization/meeting/detail";

        MeetingVO meeting = meetingService.getMeetingById(id);

        mnv.addObject("meeting", meeting);
        mnv.addObject("projectId", projectId);
        mnv.setViewName(url);
        return mnv;
    }

    @GetMapping("/modify")
    public String modifyForm(
            @PathVariable("projectId") String projectId,
            int id,
            Model model) throws Exception {

        MeetingVO meeting = meetingService.getMeetingById(id);
        model.addAttribute("meeting", meeting);
        model.addAttribute("projectId", projectId);
        return "/organization/meeting/modify";
    }

    @PostMapping("/modify")
    public ModelAndView modifyPost(
            @PathVariable("projectId") String projectId,
            MeetingModifyCommand modifyCommand,
            ModelAndView mnv) throws Exception {

        String url = "/organization/meeting/modify_success";

        MeetingVO meeting = modifyCommand.toMeetingVO();
        meeting.setTitle(HTMLInputFilter.htmlSpecialChars(meeting.getTitle()));

        meetingService.modifyMeeting(meeting);

        mnv.addObject("id", meeting.getId());
        mnv.addObject("projectId", projectId);
        mnv.setViewName(url);

        return mnv;
    }

    @GetMapping("/remove")
    public String remove(
            @PathVariable("projectId") String projectId,
            int id,
            Model model) throws Exception {

        String url = "/organization/meeting/remove_success";
        meetingService.removeMeeting(id);

        model.addAttribute("projectId", projectId);
        return url;
    }
}

