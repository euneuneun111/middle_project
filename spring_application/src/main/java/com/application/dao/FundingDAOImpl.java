package com.application.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.command.PageMaker;
import com.application.dto.FundingVO;

@Repository
public class FundingDAOImpl implements FundingDAO {

    private final SqlSession session;

    @Autowired
    public FundingDAOImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public List<FundingVO> getAllFundings() {
        return session.selectList("Funding-Mapper.getAllFundings");
    }

    @Override
    public List<FundingVO> getFundingsPaging(PageMaker pageMaker) {
        int offset = pageMaker.getStartRow() - 1;
        int limit = pageMaker.getPerPageNum();
        RowBounds rowBounds = new RowBounds(offset, limit);

        return session.selectList("Funding-Mapper.getFundingsPaging", pageMaker, rowBounds);
    }

    @Override
    public int getTotalCount(PageMaker pageMaker) {
        return session.selectOne("Funding-Mapper.getTotalCount", pageMaker);
    }

    @Override
    public FundingVO getFundingById(int fno) {
        return session.selectOne("Funding-Mapper.getFundingById", fno);
    }

    @Override
    public int insertFunding(FundingVO vo) {
        return session.insert("Funding-Mapper.insertFunding", vo);
    }

    @Override
    public int updateFunding(FundingVO vo) {
        return session.update("Funding-Mapper.updateFunding", vo);
    }

    @Override
    public int deleteFunding(int fno) {
        return session.delete("Funding-Mapper.deleteFunding", fno);
    }

    @Override
    public void increaseViewCnt(int fno) {
        session.update("Funding-Mapper.increaseViewCnt", fno);
    }

    @Override
    public void increaseLike(int fno) {
        session.update("Funding-Mapper.increaseLike", fno);
    }
}
