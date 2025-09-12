// com.Semicolon.pms.controller.GanttController.java
package com.Semicolon.pms.controller;

import java.sql.SQLException;
import java.util.HashMap; // HashMap 임포트
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Semicolon.pms.dto.GanttDto;
import com.Semicolon.pms.service.GanttService;

@Controller
@RequestMapping("/main")
public class GanttController {

    @Autowired
    private GanttService ganttService;

    @GetMapping("/gantt/{projectId}")
    public String getGanttPage(@PathVariable("projectId") String projectId, Model model) {
    	String currentProjectId = "PJ-001";
    	try {
            List<GanttDto> ganttData = ganttService.getGanttDataByProjectId(projectId);
            model.addAttribute("ganttData", ganttData);
            model.addAttribute("currentProjectId", projectId);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "간트 차트 데이터를 불러오는 중 오류가 발생했습니다.");
        }
        return "organization/pms/gantt/ganttchart";
    }

    // 2. projectId 없이 /main/gantt 로만 요청이 온 경우를 처리하는 메소드
    @GetMapping("/gantt")
    public String getGanttPageDefault(Model model) {
        // 기본 프로젝트 ID("PJT001")를 사용하여 위의 메소드를 재활용합니다.
        return getGanttPage("PJ-001", model);
    }

    // 새 간트 항목 추가 API (POST /main/gantt/add)
    @PostMapping("/gantt/add")
    @ResponseBody
    public Map<String, Object> addGanttTask(@RequestBody GanttDto ganttDto) {
        // ✅ [수정 2] 일관성 있는 JSON 응답을 위해 HashMap 사용
        Map<String, Object> response = new HashMap<>();
        try {
            int result = ganttService.createNewGantt(ganttDto);
            if (result > 0) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("error", "데이터베이스에 항목이 추가되지 않았습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("error", "간트 항목 추가 중 오류 발생: " + e.getMessage());
        }
        return response;
    }
}