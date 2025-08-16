package com.semicolon.dao;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.command.PageMaker;
import com.semicolon.dto.FundingVO;

public interface FundingDAO {

	
	List<FundingVO> selectSearchFundingList(PageMaker pageMaker)throws SQLException;
	int selectSearchFundingListCount(PageMaker pageMaker)throws SQLException;
	FundingVO selectFundingByFno(int fno)throws SQLException;
	int selectFundingSeqNext()throws SQLException;
	
	void insertFunding(FundingVO funding)throws SQLException;
	void updateFunding(FundingVO funding)throws SQLException;
	void increaseViewCnt(int fno)throws SQLException;
	void deleteFunding(int fno)throws SQLException;
	
    List<FundingVO> selectListOrderBy(String orderBy, PageMaker pageMaker) throws Exception;

}
