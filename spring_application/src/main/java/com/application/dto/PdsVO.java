package com.application.dto;

import java.util.Date;
import java.util.List;

public class PdsVO {

	private int pno;
	private String title;
	private String content;
	private String picture;

	private String writer;
	private int viewcnt;
	private Date regDate;
	
	private Date startDate;
	private Date endDate;
	
	private int tgMoney;
	
	private int cmoney;
	
	

	private List<AttachVO> attachList;

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

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

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public List<AttachVO> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public int getTgMoney() {
		return tgMoney;
	}

	public void setTgMoney(int tgMoney) {
		this.tgMoney = tgMoney;
	}
	
	public int getCmoney() {
		return cmoney;
	}

	public void setCmoney(int cmoney) {
		this.cmoney = cmoney;
	}
	
	
}
