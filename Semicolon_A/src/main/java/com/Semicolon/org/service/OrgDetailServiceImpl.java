package com.Semicolon.org.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.Semicolon.org.dao.OrgDetailDAO;
import com.Semicolon.org.dto.OrgDetailDTO;

// @Service 어노테이션 제거
public class OrgDetailServiceImpl implements OrgDetailService {

    // @Autowired 어노테이션 제거
    private OrgDetailDAO orgDetailDAO;

    // 생성자를 통한 의존성 주입
    public OrgDetailServiceImpl(OrgDetailDAO orgDetailDAO) {
        this.orgDetailDAO = orgDetailDAO;
    }

    @Override
    public OrgDetailDTO getOrgDetailByOrId(String orId) {
        return orgDetailDAO.selectOrgDetailByOrId(orId);
    }

    @Override
    public List<OrgDetailDTO> getAllOrgs() {
        return orgDetailDAO.selectAllOrgs();
    }

    @Override
    @Transactional
    public void createNewOrg(OrgDetailDTO orgDetailDTO) {
        orgDetailDAO.insertNewOrg(orgDetailDTO);
    }

    @Override
    @Transactional
    public int updateOrg(OrgDetailDTO orgDetailDTO) {
        return orgDetailDAO.updateOrg(orgDetailDTO);
    }

    @Override
    @Transactional
    public int deleteOrg(String orId) {
        return orgDetailDAO.deleteOrg(orId);
    }
}