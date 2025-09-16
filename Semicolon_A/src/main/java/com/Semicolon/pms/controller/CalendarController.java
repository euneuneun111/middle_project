package com.Semicolon.pms.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // ✅ import 변경
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Semicolon.cmnt.dto.MemberVO;
import com.Semicolon.pms.dto.CalendarDto;
import com.Semicolon.pms.service.CalendarService;

// ✅ @RestController 대신 @Controller를 사용합니다.
@Controller
@RequestMapping("/main/calendar")
public class CalendarController {

    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // @Controller를 사용하면 이 메서드는 기본적으로 페이지(View)를 반환합니다.
    @GetMapping
    public ModelAndView calendarPage() {
        return new ModelAndView("organization/pms/calendar/calendar");
    }

    // ✅ 데이터를 반환하는 API 메서드에는 @ResponseBody를 직접 붙여줍니다.
    @GetMapping("/all")
    @ResponseBody 
    public List<Map<String, Object>> getAllCalendarsForFullCalendar() {
        try {
            List<CalendarDto> calendars = calendarService.getAllCalendars();
            
            List<Map<String, Object>> events = new ArrayList<>();
            for (CalendarDto c : calendars) {
                Map<String, Object> event = new HashMap<>();
                event.put("id", c.getCalendarId());
                event.put("title", c.getCalendarTitle());
                event.put("start", c.getCalendarStartDate());
                event.put("end", c.getCalendarEndDate());
                event.put("calendarContent", c.getCalendarContent());
                event.put("allDay", true);
                
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    // ✅ 데이터를 반환하는 API 메서드에는 @ResponseBody를 직접 붙여줍니다.
    @PostMapping("/add")
    @ResponseBody
    // ✅ 4. HttpSession 파라미터를 받고, 로그인 세션을 확인하는 로직 추가
    public ResponseEntity<Map<String, String>> addCalendar(@RequestBody CalendarDto calendarDto, HttpSession session) {
        // 세션에서 로그인 사용자 정보 가져오기
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인되어 있지 않으면 401 Unauthorized 에러 반환
            return new ResponseEntity<>(
                Collections.singletonMap("message", "로그인이 필요합니다."),
                HttpStatus.UNAUTHORIZED
            );
        }

        try {
            calendarDto.setProjectId("PJ-001");
            calendarService.addCalendar(calendarDto);
            return ResponseEntity.ok(Collections.singletonMap("message", "일정이 성공적으로 추가되었습니다."));
        } catch (SQLException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "일정 추가 중 오류가 발생했습니다."));
        }
    }
    
    // ✅ 데이터를 반환하는 API 메서드에는 @ResponseBody를 직접 붙여줍니다.
    @GetMapping("/{calendarId}")
    @ResponseBody
    public ResponseEntity<CalendarDto> getCalendar(@PathVariable("calendarId") String calendarId) {
        try {
            CalendarDto calendar = calendarService.getCalendarById(calendarId);
            if (calendar != null) {
                return ResponseEntity.ok(calendar);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (SQLException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // ✅ 데이터를 반환하는 API 메서드에는 @ResponseBody를 직접 붙여줍니다.
    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, String>> updateCalendar(@RequestBody CalendarDto calendarDto) {
        try {
            // 프로젝트 ID를 임시로 하드코딩
            calendarDto.setProjectId("PJ-001");
            calendarService.updateCalendar(calendarDto);
            return ResponseEntity.ok(Collections.singletonMap("message", "일정이 성공적으로 수정되었습니다."));
        } catch (SQLException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "일정 수정 중 오류가 발생했습니다."));
        }
    }

    // ✅ 데이터를 반환하는 API 메서드에는 @ResponseBody를 직접 붙여줍니다.
    @DeleteMapping("/{calendarId}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteCalendar(@PathVariable("calendarId") String calendarId) {
        try {
            calendarService.deleteCalendar(calendarId);
            return ResponseEntity.ok(Collections.singletonMap("message", "일정이 성공적으로 삭제되었습니다."));
        } catch (SQLException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "일정 삭제 중 오류가 발생했습니다."));
        }
    }
}