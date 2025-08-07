package com.application.dto;

import java.util.Date;
import java.util.List;

public class ReportVO {
	
	private int rno;          // 보고 번호
	private String title="";     // 제목
	private String writer;	  // 작성자 
	private String content="";   // 내용
	private Date regDate= new Date();     // 등록날짜
	private Date reportDate = new Date(); // 보고 일자
	
	private boolean check; // 관리자 확인

	List<AttachReportVO> attaches;

	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public Date getReportDate() {
		return reportDate;
	}


	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}


	public boolean ischeck() {
		return check;
	}


	public void setPmcheck(boolean check) {
		this.check = check;
	}


	public List<AttachReportVO> getAttaches() {
		return attaches;
	}


	public void setAttaches(List<AttachReportVO> attaches) {
		this.attaches = attaches;
	}

}
