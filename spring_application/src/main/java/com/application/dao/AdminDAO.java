package com.application.dao;

import java.sql.SQLException;
import java.util.List;

import com.application.command.PageMaker;
import com.application.dto.AdminVO;

public interface AdminDAO {
    
    // 전체 목록 조회
    List<AdminVO> selectAdminList(PageMaker pageMaker) throws SQLException;
    
    // 상세 조회
    AdminVO selectAdminById(int id) throws SQLException;
    
    void deleteAdmin(int id) throws SQLException;
}
