package com.semicolon.command;

import com.semicolon.dto.BoardVO;

public class Recruit_BoardModifyCommand extends BoardRegistCommand{

	private int fno;

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
}
