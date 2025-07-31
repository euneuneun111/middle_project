package com.application.service;

import java.util.List;

import com.application.command.PageMaker;
import com.application.dto.FundingVO;

public interface FundingService {

    List<FundingVO> getAllFundings();
    
    List<FundingVO> getFundingsPaging(PageMaker pageMaker);

    int getTotalCount(PageMaker pageMaker);

    FundingVO getFundingById(int fno);

    int insertFunding(FundingVO vo);

    int updateFunding(FundingVO vo);

    int deleteFunding(int fno);

    void increaseViewCnt(int fno);

    void toggleLike(int fno, boolean like); // true = 좋아요 추가, false = 좋아요 취소
}