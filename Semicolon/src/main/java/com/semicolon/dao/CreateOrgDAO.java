package com.semicolon.dao;

import com.semicolon.dto.CreateOrgDTO;

public interface CreateOrgDAO {
    void createOrganization(CreateOrgDTO organization);
}
//CREATE SEQUENCE ORGANIZATION_SEQ
//START WITH 1
//INCREMENT BY 1
//NOCACHE;  시퀀스 DB에 추가