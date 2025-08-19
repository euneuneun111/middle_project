package com.Semicolon.org.service;

import com.Semicolon.org.dao.MemberDAO;
import com.Semicolon.org.dto.MemberDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

// @Service 어노테이션 제거
public class MemberServiceImpl implements MemberService {

    // MemberDAO 객체를 주입받을 필드 선언
    private MemberDAO memberDAO;

    // @Autowired 어노테이션 제거. 대신 생성자를 통해 주입
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
    
    // @Transactional 어노테이션은 그대로 유지 가능
    
    @Override
    public MemberDTO getMemberById(int engId) {
        return memberDAO.getMemberById(engId);
    }
    
    @Override
    @Transactional
    public void deleteMember(int engId) {
        memberDAO.deleteMember(engId);
    }

    @Override
    @Transactional
    public int updateMemberRole(int engId, String newRole) {
        return memberDAO.updateMemberRole(engId, newRole);
    }
    
    @Override
    public List<MemberDTO> searchMembersByFilter(Map<String, Object> filter) {
        return memberDAO.searchMembersByFilter(filter);
    }
}