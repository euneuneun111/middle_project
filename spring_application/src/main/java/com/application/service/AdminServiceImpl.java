package com.application.service;

import java.sql.SQLException;
import java.util.List;

import com.application.command.PageMaker;
import com.application.dao.AdminDAO;
import com.application.dto.AdminVO;

public class AdminServiceImpl implements AdminService {
	
	private AdminDAO adminDAO;

	public AdminServiceImpl(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;

}

	@Override
	public List<AdminVO> getAdminList(PageMaker pageMaker) throws SQLException {
		List<AdminVO> adminList = adminDAO.selectAdminList(pageMaker);

		return adminList;
	}

	@Override
	public AdminVO getAdminById(int id) throws SQLException {
		AdminVO admin = adminDAO.selectAdminById(id);

		return admin;
	}

	@Override
	public void removeAdmin(int id) throws SQLException {
		AdminVO admin = adminDAO.selectAdminById(id);

		adminDAO.deleteAdmin(id);
		
	}
}
