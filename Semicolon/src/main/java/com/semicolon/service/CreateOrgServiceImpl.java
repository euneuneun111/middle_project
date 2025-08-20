package com.semicolon.service;

import com.semicolon.dao.CreateOrgDAO;
import com.semicolon.dto.CreateOrgDTO;

public class CreateOrgServiceImpl implements CreateOrgService {

    private CreateOrgDAO createOrgDAO;

    // XML에서 c:createOrgDAO-ref="createOrgDAO"로 주입될 생성자
    public CreateOrgServiceImpl(CreateOrgDAO createOrgDAO) {
        this.createOrgDAO = createOrgDAO;
    }

    @Override
    public void createOrganization(CreateOrgDTO organization) {
        createOrgDAO.createOrganization(organization);
    
    }
}