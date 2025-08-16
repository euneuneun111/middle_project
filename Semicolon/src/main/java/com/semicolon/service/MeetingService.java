package com.semicolon.service;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.command.PageMaker;
import com.semicolon.dto.MeetingVO;

public interface MeetingService {

    List<MeetingVO> getMeetingList(PageMaker pageMaker) throws SQLException;

    MeetingVO getMeetingById(int id) throws SQLException;

    void registMeeting(MeetingVO meeting) throws SQLException;

    void modifyMeeting(MeetingVO meeting) throws SQLException;

    void removeMeeting(int id) throws SQLException;
}
