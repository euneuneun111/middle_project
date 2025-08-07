package com.application.dao;

import java.sql.SQLException;
import java.util.List;

import com.application.dto.ReportVO;

public interface ReportDAO {

		ReportVO selectReportByRno(int rno) throws SQLException;
		
		int selectReportSequenceNextValue() throws SQLException;
		
		void insertReport(ReportVO report) throws SQLException;
		void updatetReport(ReportVO report) throws SQLException;

		void deleteReport(int rno) throws SQLException;
		
		List<ReportVO> selectReportList() throws SQLException;

		
}
