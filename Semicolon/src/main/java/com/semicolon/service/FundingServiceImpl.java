package com.semicolon.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.semicolon.command.PageMaker;
import com.semicolon.dao.AttachDAO;
import com.semicolon.dao.FundingDAO;
import com.semicolon.dao.HeartDAO;
import com.semicolon.dto.AttachVO;
import com.semicolon.dto.FundingVO;
import com.semicolon.dto.HeartVO;

public class FundingServiceImpl implements FundingService {
	private FundingDAO fundingDAO;
	private AttachDAO attachDAO;
	private HeartDAO heartDAO;

	public FundingServiceImpl(FundingDAO fundingDAO, AttachDAO attachDAO, HeartDAO heartDAO) {
		this.fundingDAO = fundingDAO;
		this.attachDAO = attachDAO;
		this.heartDAO = heartDAO;
	}

	@Override
	public List<FundingVO> searchList(PageMaker pageMaker) throws SQLException {
		List<FundingVO> fundingList = fundingDAO.selectSearchFundingList(pageMaker);

		if (fundingList != null)
			for (FundingVO funding : fundingList) {
				int fno = funding.getFno();
				List<AttachVO> attachList = attachDAO.selectAttachByFno(fno);
				funding.setAttachList(attachList);
			}

		int listTotalCount = fundingDAO.selectSearchFundingListCount(pageMaker);
		pageMaker.setTotalCount(listTotalCount);

		return fundingList;
	}

	@Override
	public int regist(FundingVO funding) throws SQLException {
		int fno = fundingDAO.selectFundingSeqNext();
		funding.setFno(fno);
		fundingDAO.insertFunding(funding);

		List<AttachVO> attachList = funding.getAttachList();
		if (attachList != null)
			for (AttachVO attach : attachList) {
				attach.setFno(fno);
				attach.setAttacher(funding.getWriter());
				attachDAO.insertAttach(attach);
			}

		return fno;

	}

	@Override
	public FundingVO detail(int fno) throws SQLException {
		fundingDAO.increaseViewCnt(fno);
		FundingVO funding = fundingDAO.selectFundingByFno(fno);

		List<AttachVO> attachList = attachDAO.selectAttachByFno(fno);
		funding.setAttachList(attachList);

		return funding;
	}

	@Override
	public FundingVO getFunding(int fno) throws SQLException {
		FundingVO funding = fundingDAO.selectFundingByFno(fno);

		List<AttachVO> attachList = attachDAO.selectAttachByFno(fno);
		funding.setAttachList(attachList);

		return funding;
	}

	@Override
	public int modify(FundingVO funding) throws SQLException {
		int fno = funding.getFno();
		fundingDAO.updateFunding(funding);

		List<AttachVO> attachList = funding.getAttachList();
		if (attachList != null)
			for (AttachVO attach : attachList) {
				attach.setFno(fno);
				attach.setAttacher(funding.getWriter());
				attachDAO.insertAttach(attach);
			}

		return fno;
	}

	@Override
	public void remove(int fno) throws SQLException {
		attachDAO.deletAllAttach(fno);
		fundingDAO.deleteFunding(fno);
	}

	@Override
	public FundingVO getFno(int fno) throws SQLException {
		FundingVO funding = fundingDAO.selectFundingByFno(fno);

		return funding;
	}
	@Override
	public List<FundingVO> listByLatest(PageMaker pageMaker) throws Exception {
	    List<FundingVO> fundingList = fundingDAO.selectSearchFundingList(pageMaker);

	    if(fundingList != null) {
	        for(FundingVO funding : fundingList) {
	            int fno = funding.getFno();
	            // AttachList 세팅
	            List<AttachVO> attachList = attachDAO.selectAttachByFno(fno);
	            funding.setAttachList(attachList);
	        }
	    }

	    int listTotalCount = fundingDAO.selectSearchFundingListCount(pageMaker);
	    pageMaker.setTotalCount(listTotalCount);

	    return fundingList;
	}
	
	@Override
	public List<FundingVO> listByPopular(PageMaker pageMaker) throws Exception {
	    List<FundingVO> fundingList = fundingDAO.selectListOrderBy("viewcnt DESC", pageMaker);

	    if(fundingList != null) {
	        for(FundingVO funding : fundingList) {
	            int fno = funding.getFno();
	            List<AttachVO> attachList = attachDAO.selectAttachByFno(fno);
	            funding.setAttachList(attachList);
	        }
	    }

	    int listTotalCount = fundingDAO.selectSearchFundingListCount(pageMaker);
	    pageMaker.setTotalCount(listTotalCount);

	    return fundingList;
	}
	
	 @Override
	    public boolean toggleHeart(HeartVO heartVO) {
	        boolean liked = heartDAO.existsHeart(heartVO);
	        if (liked) {
	            heartDAO.deleteHeart(heartVO);
	            return false;
	        } else {
	            heartDAO.insertHeart(heartVO);
	            return true;
	        }
	    }

	    @Override
	    public int getHeartCount(int fno) {
	        return heartDAO.countHeartByFno(fno);
	    }

	    @Override
	    public boolean isHeartedByUser(int fno, String id) {
	        HeartVO heartVO = new HeartVO();
	        heartVO.setFno(fno);
	        heartVO.setId(id);
	        return heartDAO.existsHeart(heartVO);
	    }
}
