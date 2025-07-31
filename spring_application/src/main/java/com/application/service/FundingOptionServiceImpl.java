package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dao.FundingOptionDAO;
import com.application.dto.FundingOptionVO;

@Service
public class FundingOptionServiceImpl implements FundingOptionService {

    private final FundingOptionDAO fundingOptionDAO;

    @Autowired
    public FundingOptionServiceImpl(FundingOptionDAO fundingOptionDAO) {
        this.fundingOptionDAO = fundingOptionDAO;
    }

    @Override
    public List<FundingOptionVO> getOptionsByFundingId(int fundingId) {
        return fundingOptionDAO.getOptionsByFundingId(fundingId);
    }

    @Override
    public int insertFundingOption(FundingOptionVO vo) {
        return fundingOptionDAO.insertFundingOption(vo);
    }

    @Override
    public int deleteOptionsByFundingId(int fundingId) {
        return fundingOptionDAO.deleteOptionsByFundingId(fundingId);
    }
}
