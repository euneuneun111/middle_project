package com.application.service;

import java.util.List;

import com.application.dto.FundingVO;

public interface FundingService {

    List<FundingVO> getAllFundings();

    FundingVO getFundingById(int fno);

    int insertFunding(FundingVO vo);

    int updateFunding(FundingVO vo);

    int deleteFunding(int fno);

    void increaseViewCnt(int fno);

    void increaseLike(int fno);
}