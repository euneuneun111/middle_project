package com.application.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.application.dto.FundingVO;

public class FundingDAOImpl implements FundingDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public FundingDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<FundingVO> getAllFundings() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("funding.getAllFundings");
        }
    }

    @Override
    public FundingVO getFundingById(int fno) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("funding.getFundingById", fno);
        }
    }

    @Override
    public int insertFunding(FundingVO vo) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.insert("funding.insertFunding", vo);
            session.commit();
            return result;
        }
    }

    @Override
    public int updateFunding(FundingVO vo) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.update("funding.updateFunding", vo);
            session.commit();
            return result;
        }
    }

    @Override
    public int deleteFunding(int fno) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.delete("funding.deleteFunding", fno);
            session.commit();
            return result;
        }
    }

    @Override
    public void increaseViewCnt(int fno) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("funding.increaseViewCnt", fno);
            session.commit();
        }
    }

    @Override
    public void increaseLike(int fno) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("funding.increaseLike", fno);
            session.commit();
        }
    }
}
