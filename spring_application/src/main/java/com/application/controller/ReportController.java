package com.application.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.application.command.ReportModifyCommand;
import com.application.command.ReportPageMaker;
import com.application.command.ReportRegistCommand;
import com.application.dao.AttachReportDAO;
import com.application.dto.AttachReportVO;
import com.application.dto.ReportVO;
import com.application.service.ReportService;
import com.josephoconnell.html.HTMLInputFilter;

@Controller
@RequestMapping("/organization/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@Autowired
	private AttachReportDAO attachreportDAO;

	@GetMapping("/list")
	public ModelAndView list(@ModelAttribute ReportPageMaker reportpage, ModelAndView mnv) throws Exception {

		List<ReportVO> reportList = reportService.reportList(reportpage);

		mnv.addObject("reportList", reportList);
		mnv.addObject("pageMaker", reportpage);
		mnv.setViewName("/organization/report/list"); // 뷰 이름

		return mnv;
	}

	@GetMapping("/regist")
	public void regist() {
	}

	@javax.annotation.Resource(name = "reportSavedFilePath")
	private String fileUploadPath;

	private List<AttachReportVO> saveFileToAttaches(List<MultipartFile> multiFiles, String savePath) throws Exception {

		if (multiFiles == null || multiFiles.isEmpty())
			return null;

		List<AttachReportVO> attachreportList = new ArrayList<AttachReportVO>();

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

	@PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
	public String registPost(ReportRegistCommand regCommand, ModelAndView mnv) throws Exception {
		String url = "/organization/report/regist_success";

		ReportVO report = regCommand.toReportVO();

		report.setTitle(HTMLInputFilter.htmlSpecialChars(report.getTitle()));

		List<MultipartFile> uploadFiles = regCommand.getUploadFile();
		String uploadPath = fileUploadPath;

		List<AttachReportVO> attaches = saveFileToAttaches(uploadFiles, uploadPath);
		report.setAttaches(attaches);

		reportService.regist(report);

		return url;
	}

	@GetMapping("/detail")
	public ModelAndView detail(int rno, ModelAndView mnv) throws Exception {
		String url = "/organization/report/detail";

		ReportVO report = reportService.getRno(rno);

		mnv.addObject("report", report);

		mnv.setViewName(url);

		return mnv;
	}

	@GetMapping("/getFile")
	@ResponseBody
	public ResponseEntity<Resource> getFile(int arno) throws Exception {

		AttachReportVO attachreport = attachreportDAO.selectAttachReportByArno(arno);

		String filePath = attachreport.getUploadPath() + File.separator + attachreport.getFileName();

		Resource resource = new UrlResource(Paths.get(filePath).toUri());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\""
								+ UriUtils.encode(attachreport.getFileName().split("\\$\\$")[1], "UTF-8") + "\"")
				.body(resource);
	}

	@GetMapping("/modify")
	public void modifyForm(int rno, Model model) throws Exception {
		ReportVO report = reportService.getRno(rno);

		model.addAttribute("report", report);

	}

	@PostMapping("/modify")
	public ModelAndView modify(ReportModifyCommand modCommand, ModelAndView mnv) throws Exception {
		String url = "/organization/report/modify_success";

		if (modCommand.getDeleteFile() != null && modCommand.getDeleteFile().length > 0) {
			for (int arno : modCommand.getDeleteFile()) {
				AttachReportVO attachreport = attachreportDAO.selectAttachReportByArno(arno);

				File deleteFile = new File(attachreport.getUploadPath(), attachreport.getFileName());

				if (deleteFile.exists()) {
					deleteFile.delete();
				}
				attachreportDAO.deletAttach(arno);

			}
		}

		List<AttachReportVO> attachList = saveFileToAttaches(modCommand.getUploadFile(), fileUploadPath);

		ReportVO report = modCommand.toReportVO();
		report.setAttaches(attachList);

		report.setTitle(HTMLInputFilter.htmlSpecialChars(report.getTitle()));

		reportService.modify(report);

		mnv.addObject("rno", report.getRno());

		mnv.setViewName(url);

		return mnv;

	}

	@GetMapping("/remove")
	public ModelAndView remove(int rno, ModelAndView mnv) throws Exception {
		String url = "/organization/report/remove_success";

		// 첨부파일 삭제
		List<AttachReportVO> attachList = reportService.getRno(rno).getAttaches();
		if (attachList != null) {
			for (AttachReportVO attach : attachList) {
				File target = new File(attach.getUploadPath(), attach.getFileName());
				if (target.exists()) {
					target.delete();
				}
			}
		}

		// DB 삭제
		reportService.remove(rno);

		mnv.setViewName(url);
		return mnv;
	}

}
