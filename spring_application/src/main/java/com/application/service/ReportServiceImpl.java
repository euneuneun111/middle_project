package com.application.service;

import java.sql.SQLException;
import java.util.List;

import com.application.command.ReportPageMaker;
import com.application.dao.AttachReportDAO;
import com.application.dao.ReportDAO;
import com.application.dto.AttachReportVO;
import com.application.dto.ReportVO;

public class ReportServiceImpl implements ReportService {

	private ReportDAO reportDAO;
	private AttachReportDAO attachreportDAO;

	public ReportServiceImpl(ReportDAO reportDAO, AttachReportDAO attachreportDAO) {
		this.reportDAO = reportDAO;
		this.attachreportDAO = attachreportDAO;
	}

	// 페이지 메이커

	@Override
	public void regist(ReportVO report) throws SQLException {
		int rno = reportDAO.selectReportSequenceNextValue();
		report.setRno(rno);

		// 1. Report 먼저 insert (부모 테이블)
		reportDAO.insertReport(report);

		// 2. AttachReport 나중에 insert (자식 테이블)
		if (report.getAttaches() != null && !report.getAttaches().isEmpty()) {
			for (AttachReportVO attach : report.getAttaches()) {
				attach.setAttacher(report.getWriter());
				attach.setRno(rno);
				attachreportDAO.insertAttach(attach);
			}
		}
	}

	@Override
	public ReportVO detail(int rno) throws SQLException {
		ReportVO report = reportDAO.selectReportByRno(rno);

		List<AttachReportVO> attachreportList = attachreportDAO.selectAttachReportByRno(rno);

		report.setAttaches(attachreportList);

		return report;
	}

	@Override
	public void modify(ReportVO report) throws SQLException {
		reportDAO.updatetReport(report);
	}

	@Override
	public void remove(int rno) throws SQLException {
		reportDAO.deleteReport(rno);
	}

	@Override
	public ReportVO getRno(int rno) throws SQLException {
		ReportVO report = reportDAO.selectReportByRno(rno);
		List<AttachReportVO> attachreportList = attachreportDAO.selectAttachReportByRno(rno);
		report.setAttaches(attachreportList);
		return report;
	}

	@Override
	public List<ReportVO> reportList(ReportPageMaker reportpage) throws SQLException {
		int listTotalCount = reportDAO.selectReportCount(reportpage);
		reportpage.setTotalCount(listTotalCount);
		List<ReportVO> reportlist = reportDAO.selectReportList(reportpage);

		if (reportlist != null) {
			for (ReportVO report : reportlist) {
				int rno = report.getRno();
				List<AttachReportVO> attachreport = attachreportDAO.selectAttachReportByRno(rno);
				report.setAttaches(attachreport);
			}
		}

		return reportlist;

	}

}
