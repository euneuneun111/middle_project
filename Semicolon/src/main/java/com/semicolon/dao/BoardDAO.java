package com.semicolon.dao;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.command.PageMaker;
import com.semicolon.dto.BoardVO;

public interface BoardDAO {

	List<BoardVO> selectSearchBoardList(PageMaker pageMaker) throws SQLException;

	int selectSearchBoardListCount(PageMaker pageMaker) throws SQLException;

	BoardVO selectBoardByFno(int fno) throws SQLException;

	void insertBoard(BoardVO board) throws SQLException;

	void updateBoard(BoardVO board) throws SQLException;

	void deleteBoard(int fno) throws SQLException;

	void increaseViewCnt(int fno) throws SQLException;

	int selectBoardSeqNext() throws SQLException;
}
