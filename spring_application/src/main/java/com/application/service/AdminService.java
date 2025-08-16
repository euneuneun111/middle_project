package com.application.service;

import java.sql.SQLException;
import java.util.List;

import com.application.command.PageMaker;
import com.application.dto.AdminVO;

public interface AdminService {

	List<AdminVO> getAdminList(PageMaker pageMaker) throws SQLException;
	
	AdminVO getAdminById(int id) throws SQLException;
	
	void removeAdmin(int id) throws SQLException;
}
