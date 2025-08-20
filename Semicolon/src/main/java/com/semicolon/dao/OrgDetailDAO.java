package com.semicolon.dao;
import com.semicolon.dto.OrgDetailDTO;
public interface OrgDetailDAO {
	String selectOrgIdByUserId(String userId);
    OrgDetailDTO selectOrgById(String orId);
    void updateOrg(OrgDetailDTO org);
    void deleteOrg(String orId);
}