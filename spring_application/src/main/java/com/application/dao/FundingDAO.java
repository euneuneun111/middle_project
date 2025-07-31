package com.application.dao;

import java.util.List;

import com.application.command.PageMaker;
import com.application.dto.FundingVO;

public interface FundingDAO {

    // 전체 목록 조회
    List<FundingVO> getAllFundings();

    // 페이징 목록 조회
    List<FundingVO> getFundingsPaging(PageMaker pageMaker); // 🔹 추가

    // 전체 개수 (검색 조건 포함)
    int getTotalCount(PageMaker pageMaker); // 🔹 추가

    // 단건 조회
    FundingVO getFundingById(int fno);

    // 등록
    int insertFunding(FundingVO vo);

    // 수정
    int updateFunding(FundingVO vo);

    // 삭제
    int deleteFunding(int fno);

    // 조회수 증가
    void increaseViewCnt(int fno);

    // 좋아요 증가
    void increaseLike(int fno);
    
    void decreaseLike(int fno);

}
