package com.semicolon.service;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.command.PageMaker;
import com.semicolon.dao.MeetingDAO;
import com.semicolon.dto.MeetingVO;

public class MeetingServiceImpl implements MeetingService {

	private MeetingDAO meetingDAO;

	public MeetingServiceImpl(MeetingDAO meetingDAO) {
		this.meetingDAO = meetingDAO;
	}

	@Override
	public List<MeetingVO> getMeetingList(PageMaker pageMaker) throws SQLException {
		int totalCount = meetingDAO.selectMeetingListCount(pageMaker);
		pageMaker.setTotalCount(totalCount);
		List<MeetingVO> meetingList = meetingDAO.selectMeetingList(pageMaker);

		return meetingList;
	}

	@Override
	public void registMeeting(MeetingVO meeting) throws SQLException {
		int id = meetingDAO.selectMeetingSequenceNextValue();
		meeting.setId(id);
		meetingDAO.insertMeeting(meeting); // insertMeeting 호출 필요
	}

	@Override
	public MeetingVO getMeetingById(int id) throws SQLException {
		MeetingVO meeting = meetingDAO.selectMeetingById(id);

		return meeting;
	}

	@Override
	public void modifyMeeting(MeetingVO meeting) throws SQLException {
		meetingDAO.updateMeeting(meeting);

	}

	@Override
	public void removeMeeting(int id) throws SQLException {
		
		
		MeetingVO meeting = meetingDAO.selectMeetingById(id);

		meetingDAO.deleteMeeting(id);
	}
}
	
