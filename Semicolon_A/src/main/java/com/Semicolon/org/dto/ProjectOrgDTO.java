package com.Semicolon.org.dto;

import java.util.Date;

public class ProjectOrgDTO {
	private String projectId; // PROJECT_ID VARCHAR2(50)
	private String projectName; // PROJECT_NAME VARCHAR2(40)
	private String projectLogo; // PROJECT_LOGO VARCHAR2(30)
	private String projectManager; // PROJECT_MANAGER VARCHAR2(200)
	private Date projectStartDate = new Date(); // PROJECT_START_DATE DATE
	private String projectDesc; // PROJECT_DESC VARCHAR2(400)
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectLogo() {
		return projectLogo;
	}
	public void setProjectLogo(String projectLogo) {
		this.projectLogo = projectLogo;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public Date getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	
}