package com.application.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.application.dto.ReportVO;

public class ReportRegistCommand {
	
	private String title;
	private String content;
	private String writer;

	private String reportDate;
	
	private List<MultipartFile> uploadFile;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public ReportVO toReportVO() throws ParseException{
		ReportVO report = new ReportVO();

		report.setContent(this.content);
		report.setTitle(this.title);
		report.setWriter(this.writer);
		report.setRegDate(new Date());
		report.setReportDate(new SimpleDateFormat("yyyy-MM-dd").parse(this.reportDate));

		return report;
	}

}
