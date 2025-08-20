package com.semicolon.dao;

import java.sql.SQLException;

import com.semicolon.dto.EngineerVO;

public interface EngineerDAO {

	EngineerVO selectEngineerByEng_Id(String eng_id)throws SQLException;
	void insertEngineer(EngineerVO engineer)throws SQLException;
	void updateEngineer(EngineerVO engineer)throws SQLException;	

}




