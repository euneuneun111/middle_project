package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.dao.FundingDAO;
import com.application.dto.FundingVO;

@Service
public class FundingServiceImpl implements FundingService {

    private final FundingDAO fundingDAO;

    @Autowired
    public FundingServiceImpl(FundingDAO fundingDAO) {
        this.fundingDAO = fundingDAO;
    }

    @Override
    public List<FundingVO> getAllFundings() {
        return fundingDAO.getAllFundings();
    }

    @Override
    public FundingVO getFundingById(int fno) {
        return fundingDAO.getFundingById(fno);
    }

    @Override
    @Transactional
    public int insertFunding(FundingVO vo) {
        return fundingDAO.insertFunding(vo);
    }

    @Override
    @Transactional
    public int updateFunding(FundingVO vo) {
        return fundingDAO.updateFunding(vo);
    }

    @Override
    @Transactional
    public int deleteFunding(int fno) {
        return fundingDAO.deleteFunding(fno);
    }

    @Override
    @Transactional
    public void increaseViewCnt(int fno) {
        fundingDAO.increaseViewCnt(fno);
    }

    @Override
    @Transactional
    public void increaseLike(int fno) {
        fundingDAO.increaseLike(fno);
    }
}
