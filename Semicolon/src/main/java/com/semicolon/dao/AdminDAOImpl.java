package com.semicolon.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.semicolon.command.PageMaker;
import com.semicolon.dto.AdminVO;

public class AdminDAOImpl implements AdminDAO {
	
	private SqlSession session;

	public AdminDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<AdminVO> selectAdminList(PageMaker pageMaker) throws SQLException {
		int offset = pageMaker.getStartRow()-1;
		int limit = pageMaker.getPerPageNum();
		
		RowBounds rows = new RowBounds(offset,limit);
		return session.selectList("Admin-Mapper.selectAdminList", pageMaker,rows);
	
	}
	
	@Override
	public int selectAdminListCount(PageMaker pageMaker) throws SQLException {
		return session.selectOne("Admin-Mapper.selectAdminListCount",pageMaker);
	}

	@Override
	public AdminVO selectAdminById(int id) throws SQLException {
		return session.selectOne("Admin-Mapper.selectAdminById", id);
		
	}
	
	@Override
	public void insertAdmin(AdminVO admin) throws SQLException {
        session.insert("Admin-Mapper.insertAdmin", admin);
    }


	@Override
	public void deleteAdmin(int id) throws SQLException {
		session.delete("Admin-Mapper.deleteAdmin", id);
		
	}



}