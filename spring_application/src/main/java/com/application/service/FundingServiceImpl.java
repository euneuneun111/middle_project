package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.command.PageMaker;
import com.application.dao.FundingDAO;
import com.application.dto.FundingVO;

@Service
public class FundingServiceImpl implements FundingService {

    @Autowired
    private FundingDAO fundingDAO;

    @Override
    public List<FundingVO> getAllFundings() {
        return fundingDAO.getAllFundings();
    }

    @Override
    public List<FundingVO> getFundingsPaging(PageMaker pageMaker) {
        return fundingDAO.getFundingsPaging(pageMaker);
    }

    @Override
    public int getTotalCount(PageMaker pageMaker) {
        return fundingDAO.getTotalCount(pageMaker);
    }

    @Override
    public FundingVO getFundingById(int fno) {
        return fundingDAO.getFundingById(fno);
    }

    @Override
    public int insertFunding(FundingVO vo) {
        return fundingDAO.insertFunding(vo);
    }

    @Override
    public int updateFunding(FundingVO vo) {
        return fundingDAO.updateFunding(vo);
    }

    @Override
    public int deleteFunding(int fno) {
        return fundingDAO.deleteFunding(fno);
    }

    @Override
    public void increaseViewCnt(int fno) {
        fundingDAO.increaseViewCnt(fno);
    }

    @Override
    public void toggleLike(int fno, boolean like) {
        if (like) {
            fundingDAO.increaseLike(fno);
        } else {
        	fundingDAO.decreaseLike(fno);
        }
    }
}
