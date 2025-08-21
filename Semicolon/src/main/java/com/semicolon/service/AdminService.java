package com.semicolon.service;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.command.PageMaker;
import com.semicolon.dto.AdminVO;

public interface AdminService {

	List<AdminVO> getAdminList(PageMaker pageMaker) throws SQLException;
	
	AdminVO getAdminById(int id) throws SQLException;
	
	void regist(AdminVO admin) throws SQLException;
	void removeAdmin(int id) throws SQLException;
}