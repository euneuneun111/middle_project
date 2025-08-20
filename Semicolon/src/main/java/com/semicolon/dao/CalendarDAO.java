package com.semicolon.dao;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.dto.CalendarDto;

public interface CalendarDAO {
    void insertCalendar(CalendarDto calendarDto) throws SQLException;
    List<CalendarDto> getAllCalendars() throws SQLException;
    CalendarDto getCalendarById(String calendarId) throws SQLException;
    void updateCalendar(CalendarDto calendarDto) throws SQLException;
    void deleteCalendar(String calendarId) throws SQLException;
}