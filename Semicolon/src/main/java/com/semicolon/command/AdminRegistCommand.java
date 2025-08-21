package com.semicolon.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.semicolon.dto.AdminVO;

public class AdminRegistCommand {

    private String title;       // 신고 or 문의
    private String applicant;   // 신청자
    private String reason;      // 사유
    private String content;

    private String regDate;
   
    public AdminRegistCommand() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplicant() {
        return applicant;
    }
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	// 🔹 Command → VO 변환
    public AdminVO toAdminVO() throws ParseException {
        AdminVO admin = new AdminVO();
        admin.setTitle(this.title);
        admin.setApplicant(this.applicant);
        admin.setReason(this.reason);
        admin.setContent(this.content);
        
        admin.setRegDate(new SimpleDateFormat("yyyy-MM-dd").parse(this.regDate));
   
        // id와 regDate는 DB/서버에서 자동 세팅됨
        return admin;
    }
}
