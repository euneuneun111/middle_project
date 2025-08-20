package com.semicolon.service;

import java.util.List;

import com.semicolon.dto.Member1DTO;

public interface Member1Service {
    
    List<Member1DTO> getMemberList(String orId);
    
    int updateMemberRole(String userId, String newRole);
    
    int removeMember(String userId, String orId);
}