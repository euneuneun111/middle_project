package com.semicolon.command;

import java.text.ParseException;

import com.semicolon.dto.MeetingVO;

public class MeetingModifyCommand extends MeetingRegistCommand {

	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override 
	public MeetingVO toMeetingVO() throws ParseException {
		
		MeetingVO meeting = super.toMeetingVO();
		meeting.setId(id);
		
		return meeting;
	}
	
}
