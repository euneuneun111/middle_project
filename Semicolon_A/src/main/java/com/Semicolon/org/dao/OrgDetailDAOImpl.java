package com.Semicolon.org.dao;

import com.Semicolon.org.dto.OrgDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;
import java.util.List;

public class OrgDetailDAOImpl implements OrgDetailDAO {
    
    private SqlSessionTemplate sqlSession;
    
    // XML에서 c:session-ref="sqlSession"으로 주입될 생성자
    public OrgDetailDAOImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public OrgDetailDTO selectOrgDetailByOrId(String orId) {
        return sqlSession.selectOne("com.Semicolon.org.dao.OrgDetailDAO.selectOrgDetailByOrId", orId);
    }

    @Override
    public List<OrgDetailDTO> selectAllOrgs() {
        return sqlSession.selectList("com.Semicolon.org.dao.OrgDetailDAO.selectAllOrgs");
    }

    @Override
    public void insertNewOrg(OrgDetailDTO orgDetailDTO) {
        sqlSession.insert("com.Semicolon.org.dao.OrgDetailDAO.insertNewOrg", orgDetailDTO);
    }

    @Override
    public int updateOrg(OrgDetailDTO orgDetailDTO) {
        return sqlSession.update("com.Semicolon.org.dao.OrgDetailDAO.updateOrg", orgDetailDTO);
    }

    @Override
    public int deleteOrg(String orId) {
        return sqlSession.delete("com.Semicolon.org.dao.OrgDetailDAO.deleteOrg", orId);
    }
}