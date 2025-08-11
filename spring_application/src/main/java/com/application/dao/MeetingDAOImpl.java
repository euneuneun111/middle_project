package com.application.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.application.command.PageMaker;
import com.application.dto.MeetingVO;

public class MeetingDAOImpl implements MeetingDAO {

	private SqlSession session;

	public MeetingDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<MeetingVO> selectMeetingList(PageMaker pageMaker) throws SQLException {
		int offset = pageMaker.getStartRow()-1;
		int limit = pageMaker.getPerPageNum();
		
		RowBounds rows = new RowBounds(offset,limit);
		return session.selectList("Meeting-Mapper.selectMeetingList", pageMaker,rows);
	}

	@Override
	public int selectMeetingListCount(PageMaker pageMaker) throws SQLException {
		return session.selectOne("Meeting-Mapper.selectMeetingListCount", pageMaker);
	}

	@Override
	public MeetingVO selectMeetingById(int id) throws SQLException {
		return session.selectOne("Meeting-Mapper.selectMeetingById", id);
	}

	@Override
	public void insertMeeting(MeetingVO meeting) throws SQLException {
		session.insert("Meeting-Mapper.insertMeeting", meeting);
	}

	@Override
	public void updateMeeting(MeetingVO meeting) throws SQLException {
		session.update("Meeting-Mapper.updateMeeting", meeting);
	}

	@Override
	public void deleteMeeting(int id) throws SQLException {
		session.delete("Meeting-Mapper.deleteMeeting", id);
	}

	@Override
	public int selectMeetingSequenceNextValue() throws SQLException {
		return session.selectOne("Meeting-Mapper.selectMeetingSequenceNextValue");
	}

	
}
