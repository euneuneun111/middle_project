package com.semicolon.service;

import java.sql.SQLException;

import com.semicolon.dao.EngineerDAO;
import com.semicolon.dto.EngineerVO;



public class EngineerServiceImpl implements EngineerService {


	private EngineerDAO engineerDAO;

	public EngineerServiceImpl(EngineerDAO engineerDAO) {
		this.engineerDAO = engineerDAO;
	}

	@Override
	public EngineerVO getEngineerByEng_Id(String eng_id) throws SQLException {
		return engineerDAO.selectEngineerByEng_Id(eng_id);
	}

	@Override
	public void registerEngineer(EngineerVO engineer) throws SQLException {
		engineerDAO.insertEngineer(engineer);
	}

	@Override
	public void modifyEngineer(EngineerVO engineer) throws SQLException {
		engineerDAO.updateEngineer(engineer);
	}

}




