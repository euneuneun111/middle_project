package com.semicolon.dao;

import java.sql.SQLException;
import java.util.List;

import com.semicolon.command.PageMaker;
import com.semicolon.dto.MemberVO;

public interface MemberDAO {


	List<MemberVO> selectMemberList(PageMaker pageMaker)throws SQLException;
	int selectMemberListCount(PageMaker pageMaker)throws SQLException;
	
	MemberVO selectMemberById(String id)throws SQLException;
	void insertMember(MemberVO member)throws SQLException;
	void updateMember(MemberVO member)throws SQLException;
	void deleteMember(String id)throws SQLException;	
	

	List<String> selectAuthoritiesById(String id)throws SQLException;
	void insertAuthorities(String id, String authority)throws SQLException;
	void deleteAllAuthorityById(String id)throws SQLException;
}




