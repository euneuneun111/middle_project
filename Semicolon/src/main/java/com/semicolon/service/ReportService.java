package com.semicolon.service;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.command.ReportPageMaker;
import com.semicolon.dto.ReportVO;

public interface ReportService {

	void regist(ReportVO report) throws SQLException;
	
	ReportVO detail(int rno) throws SQLException;
	
	void modify(ReportVO report) throws SQLException;
	ReportVO getRno(int rno) throws SQLException;
	
	void remove(int rno) throws SQLException;
	
	List<ReportVO> reportList(ReportPageMaker reportpage) throws SQLException;
	
	
}
