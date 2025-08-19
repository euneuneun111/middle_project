package com.Semicolon.org.dao;

import com.Semicolon.org.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import java.util.List;
import java.util.Map;

// MemberDAO 인터페이스를 구현하는 클래스
public class MemberDAOImpl implements MemberDAO {

    // SqlSessionTemplate을 주입받아 MyBatis 쿼리를 실행합니다.
    private SqlSessionTemplate sqlSession;

    // 생성자를 통해 SqlSessionTemplate을 주입받기 위한 생성자
    public MemberDAOImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    // MemberDAO 인터페이스의 메서드들을 구현합니다.
    @Override
    public MemberDTO getMemberById(int engId) {
        // 매퍼 네임스페이스와 쿼리 ID를 사용하여 쿼리 실행
        return sqlSession.selectOne("com.Semicolon.org.dao.MemberDAO.getMemberById", engId);
    }

    @Override
    public void deleteMember(int engId) {
        sqlSession.delete("com.Semicolon.org.dao.MemberDAO.deleteMember", engId);
    }

    @Override
    public int updateMemberRole(int engId, String newRole) {
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("engId", engId);
        params.put("newRole", newRole);
        return sqlSession.update("com.Semicolon.org.dao.MemberDAO.updateMemberRole", params);
    }

    @Override
    public List<MemberDTO> searchMembersByFilter(Map<String, Object> filter) {
        return sqlSession.selectList("com.Semicolon.org.dao.MemberDAO.searchMembersByFilter", filter);
    }
}