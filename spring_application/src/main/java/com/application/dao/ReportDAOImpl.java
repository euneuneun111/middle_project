package com.application.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.application.dto.ReportVO;

public class ReportDAOImpl implements ReportDAO {
	
	private SqlSession session;
	
	public ReportDAOImpl(SqlSession session) {
		this.session = session;
		
	}

	@Override
	public ReportVO selectReportByRno(int rno) throws SQLException {
		return session.selectOne("Report-Mapper.selectReportByRno", rno);
	}

	@Override
	public int selectReportSequenceNextValue() throws SQLException {
		return session.selectOne("Report-Mapper.selectReportSequenceNextValue");
	}

	@Override
	public void insertReport(ReportVO report) throws SQLException {
		session.insert("Report-Mapper.insertReport", report);
	}

	@Override
	public void updatetReport(ReportVO report) throws SQLException {
		session.update("Report-Mapper.updateReport", report);
	}

	@Override
	public void deleteReport(int rno) throws SQLException {
		session.delete("Report-Mapper.deleteReport", rno);
		
	}

	@Override
	public List<ReportVO> selectReportList() throws SQLException {
		
		return session.selectList("Report-Mapper.selectReportList");
	}

}
