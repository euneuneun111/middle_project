package com.semicolon.service;
import com.semicolon.dto.OrgDetailDTO;
public interface OrgDetailService {
	String getOrgIdByUserId(String userId);
    OrgDetailDTO getOrgById(String orId);
    void modifyOrg(OrgDetailDTO org);
    void removeOrg(String orId);
}