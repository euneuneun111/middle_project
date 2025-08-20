package com.semicolon.command;

import com.semicolon.dto.BoardVO;
import com.semicolon.dto.Recruit_BoardVO;

public class BoardModifyCommand extends BoardRegistCommand{

	private int fno;
	private int rno;

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}
	
	public BoardVO toBoardVO() {
		BoardVO board = super.toBoard();
		
		board.setFno(fno);
		
		return board;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}
	
	public Recruit_BoardVO toRecruit_BroadVO() {
		Recruit_BoardVO board = super.toRecruit_Board();
		
		board.setRno(rno);
		
		return board;
	}
}
