// src/main/java/com/Semicolon/pms/service/IssueService.java

package com.Semicolon.pms.service;

import com.Semicolon.pms.dto.IssueDto;
import com.Semicolon.command.PageMaker;
import java.sql.SQLException;
import java.util.List;

public interface IssueService {

    // 특정 프로젝트의 이슈를 페이징 및 검색 조건에 따라 조회
    List<IssueDto> getIssueListByProjectId(String projectId, PageMaker pageMaker) throws SQLException;
    
    // 특정 프로젝트의 전체 이슈 수를 조회 (검색 조건 포함)
    int getTotalCountByProjectId(String projectId, PageMaker pageMaker) throws SQLException;
    
    // PageMaker에 있는 프로젝트 ID를 사용하여 전체 이슈 수를 조회하는 새로운 메서드
    int getTotalCount(PageMaker pageMaker) throws SQLException;
    
    // 새 이슈를 등록하는 메서드
    void createNewIssue(IssueDto issue) throws SQLException;
    
    // 이슈 상세 정보를 조회하는 메서드
    IssueDto getIssueById(String issueId) throws SQLException;
    
    // 이슈 수정 메소드
    void updateIssue(IssueDto issue) throws SQLException;
    
    // 이슈 삭제 메소드
    void deleteIssue(String issueId) throws SQLException;
}
