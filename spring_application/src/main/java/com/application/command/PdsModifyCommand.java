package com.application.command;

import org.springframework.web.multipart.MultipartFile;

import com.application.dto.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{

	private int pno;
	private int[] deleteFile;
	private MultipartFile picture; // 사진파일 경로/파일명
	
	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int[] getDeleteFile() {
		return deleteFile;
	}

	public void setDeleteFile(int[] deleteFile) {
		this.deleteFile = deleteFile;
	}

	@Override
	public PdsVO toPdsVO() {
		PdsVO pds = super.toPdsVO();
		pds.setPno(this.pno);

		return pds;
	}
}
