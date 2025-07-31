package com.application.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.dto.FundingOptionVO;

@Repository
public class FundingOptionDAOImpl implements FundingOptionDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "com.application.mapper.FundingOptionMapper";

    @Override
    public List<FundingOptionVO> getOptionsByFundingId(int fundingId) {
        return sqlSession.selectList(namespace + ".getOptionsByFundingId", fundingId);
    }

    @Override
    public int insertFundingOption(FundingOptionVO vo) {
        return sqlSession.insert(namespace + ".insertFundingOption", vo);
    }

    @Override
    public int deleteOptionsByFundingId(int fundingId) {
        return sqlSession.delete(namespace + ".deleteOptionsByFundingId", fundingId);
    }
}
