package com.semicolon.dao;

import com.semicolon.dto.HeartVO;

public interface HeartDAO {
    int insertHeart(HeartVO heartVO);
    int deleteHeart(HeartVO heartVO);
    int countHeartByFno(int fno);
    boolean existsHeart(HeartVO heartVO);
}