package com.semicolon.service;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.command.PageMaker;
import com.semicolon.dao.AdminDAO;
import com.semicolon.dto.AdminVO;

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
    public void regist(AdminVO admin) throws SQLException {
        adminDAO.insertAdmin(admin);
    }

	@Override
	public void removeAdmin(int id) throws SQLException {
		AdminVO admin = adminDAO.selectAdminById(id);

		adminDAO.deleteAdmin(id);
		
	}
}