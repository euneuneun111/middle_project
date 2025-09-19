package com.Semicolon.funding.controller;

import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.Semicolon.command.ReportModifyCommand;
import com.Semicolon.command.ReportPageMaker;
import com.Semicolon.command.ReportRegistCommand;
import com.Semicolon.dao.AttachReportDAO;
import com.Semicolon.dto.AttachReportVO;
import com.Semicolon.dto.ReportVO;
import com.Semicolon.service.ReportService;
import com.josephoconnell.html.HTMLInputFilter;

@Controller
@RequestMapping("/organization/{projectId}/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private AttachReportDAO attachreportDAO;

    @javax.annotation.Resource(name = "reportSavedFilePath")
    private String fileUploadPath;

    // -------------------------------
    // 파일 저장
    // -------------------------------
    private List<AttachReportVO> saveFileToAttaches(List<MultipartFile> multiFiles, String savePath) throws Exception {
        if (multiFiles == null || multiFiles.isEmpty())
            return null;

        List<AttachReportVO> attachreportList = new ArrayList<>();

        for (MultipartFile multi : multiFiles) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = uuid + "$$" + multi.getOriginalFilename();

            File target = new File(savePath, fileName);
            target.mkdirs();
            multi.transferTo(target);

            AttachReportVO attach = new AttachReportVO();
            attach.setUploadPath(savePath);
            attach.setFileName(fileName);
            attach.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());

            attachreportList.add(attach);
        }

        return attachreportList;
    }

    // -------------------------------
    // 리스트 페이지
    // -------------------------------
    @GetMapping("/list")
    public ModelAndView list(@PathVariable("projectId") String projectId,
                             @ModelAttribute ReportPageMaker reportpage, ModelAndView mnv) throws Exception {

        List<ReportVO> reportList = reportService.reportList(projectId, reportpage); // projectId 전달

        mnv.addObject("reportList", reportList);
        mnv.addObject("pageMaker", reportpage);
        mnv.addObject("projectId", projectId);
        mnv.setViewName("organization/report/list");

        return mnv;
    }

    // -------------------------------
    // 등록 폼
    // -------------------------------
    @GetMapping("/regist")
    public String registForm(@PathVariable("projectId") String projectId, Model model) throws SQLException {
        // projectId JSP로 전달
        model.addAttribute("projectId", projectId);

 
        // 등록 JSP 경로
        return "/organization/meeting/regist";
    }

    // -------------------------------
    // 등록 처리
    // -------------------------------
    @PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
    public String registPost(@PathVariable("projectId") String projectId,
                             ReportRegistCommand regCommand, ModelAndView mnv) throws Exception {
        String url = "/organization/report/regist_success";

        ReportVO report = regCommand.toReportVO();
        report.setTitle(HTMLInputFilter.htmlSpecialChars(report.getTitle()));
        report.setProjectId(projectId); // ✅ PROJECT_ID 설정

        List<MultipartFile> uploadFiles = regCommand.getUploadFile();
        List<AttachReportVO> attaches = saveFileToAttaches(uploadFiles, fileUploadPath);
        report.setAttaches(attaches);

        reportService.regist(report);

        return url;
    }

    // -------------------------------
    // 상세 페이지
    // -------------------------------
    @GetMapping("/detail")
    public ModelAndView detail(int rno, ModelAndView mnv) throws Exception {
        String url = "/organization/report/detail";
        ReportVO report = reportService.getRno(rno);
        mnv.addObject("report", report);
        mnv.setViewName(url);
        return mnv;
    }

    // -------------------------------
    // 파일 다운로드
    // -------------------------------
    @GetMapping("/getFile")
    @ResponseBody
    public ResponseEntity<Resource> getFile(int arno) throws Exception {
        AttachReportVO attachreport = attachreportDAO.selectAttachReportByArno(arno);
        String filePath = attachreport.getUploadPath() + File.separator + attachreport.getFileName();
        Resource resource = new UrlResource(Paths.get(filePath).toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + UriUtils.encode(attachreport.getFileName().split("\\$\\$")[1], "UTF-8") + "\"")
                .body(resource);
    }

    // -------------------------------
    // 수정 폼
    // -------------------------------
    @GetMapping("/modify")
    public void modifyForm(int rno, Model model) throws Exception {
        ReportVO report = reportService.getRno(rno);
        model.addAttribute("report", report);
    }

    // -------------------------------
    // 수정 처리
    // -------------------------------
    @PostMapping("/modify")
    public ModelAndView modify(@PathVariable("projectId") String projectId,
                               ReportModifyCommand modCommand, ModelAndView mnv) throws Exception {
        String url = "/organization/report/modify_success";

        // 삭제 파일 처리
        if (modCommand.getDeleteFile() != null && modCommand.getDeleteFile().length > 0) {
            for (int arno : modCommand.getDeleteFile()) {
                AttachReportVO attachreport = attachreportDAO.selectAttachReportByArno(arno);
                File deleteFile = new File(attachreport.getUploadPath(), attachreport.getFileName());
                if (deleteFile.exists()) deleteFile.delete();
                attachreportDAO.deletAttach(arno);
            }
        }

        List<AttachReportVO> attachList = saveFileToAttaches(modCommand.getUploadFile(), fileUploadPath);

        ReportVO report = modCommand.toReportVO();
        report.setAttaches(attachList);
        report.setTitle(HTMLInputFilter.htmlSpecialChars(report.getTitle()));
        report.setProjectId(projectId); // ✅ PROJECT_ID 유지

        reportService.modify(report);

        mnv.addObject("rno", report.getRno());
        mnv.setViewName(url);

        return mnv;
    }

    // -------------------------------
    // 삭제 처리
    // -------------------------------
    @GetMapping("/remove")
    public ModelAndView remove(int rno, ModelAndView mnv) throws Exception {
        String url = "/organization/report/remove_success";

        // 첨부파일 삭제
        List<AttachReportVO> attachList = reportService.getRno(rno).getAttaches();
        if (attachList != null) {
            for (AttachReportVO attach : attachList) {
                File target = new File(attach.getUploadPath(), attach.getFileName());
                if (target.exists()) target.delete();
            }
        }

        reportService.remove(rno);
        mnv.setViewName(url);
        return mnv;
    }

    // -------------------------------
    // REST API
    // -------------------------------

    // 리스트
    @GetMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<ReportVO> listApi(@ModelAttribute ReportPageMaker reportpage) throws Exception {
        return reportService.reportList(reportpage);
    }

    // 등록
    @PostMapping(value = "/regist", produces = "application/json")
    @ResponseBody
    public String registApi(@PathVariable("projectId") String projectId,
                            @RequestBody ReportRegistCommand regCommand) throws Exception {
        ReportVO report = regCommand.toReportVO();
        report.setProjectId(projectId); // ✅ PROJECT_ID 설정
        reportService.regist(report);
        return "success";
    }

    // 상세
    @GetMapping(value = "/detail", produces = "application/json")
    @ResponseBody
    public ReportVO detailApi(int rno) throws Exception {
        return reportService.getRno(rno);
    }

    // 수정
    @PostMapping(value = "/modify", produces = "application/json")
    @ResponseBody
    public String modifyApi(@PathVariable("projectId") String projectId,
                            @RequestBody ReportModifyCommand modCommand) throws Exception {
        ReportVO report = modCommand.toReportVO();
        report.setProjectId(projectId); // ✅ PROJECT_ID 유지
        reportService.modify(report);
        return "success";
    }

    // 삭제
    @PostMapping(value = "/remove", produces = "application/json")
    @ResponseBody
    public String removeApi(@RequestBody Map<String, Integer> param) throws Exception {
        Integer rno = param.get("rno");
        if (rno == null) return "fail";
        reportService.remove(rno);
        return "success";
    }
}
