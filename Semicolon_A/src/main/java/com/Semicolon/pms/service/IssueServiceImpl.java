package com.Semicolon.pms.service;

import java.sql.SQLException;
import java.util.List;

import com.Semicolon.command.PageMaker;
import com.Semicolon.pms.dao.IssueDAO;
import com.Semicolon.pms.dao.ReplyDAO;
import com.Semicolon.pms.dto.IssueDto;
import com.Semicolon.pms.dto.ReplyDto;

public class IssueServiceImpl implements IssueService {
    
    private IssueDAO issueDAO;
    private ReplyDAO replyDAO;

    // 생성자를 통한 의존성 주입
    public IssueServiceImpl(IssueDAO issueDAO, ReplyDAO replyDAO) {
        this.issueDAO = issueDAO;
        this.replyDAO = replyDAO;
    }

    @Override
    public List<IssueDto> getIssueListByProjectId(String projectId, PageMaker pageMaker) throws SQLException {
        pageMaker.setProjectId(projectId);
        return issueDAO.getIssueListByProjectId(pageMaker);
    }
    
    @Override
    public int getTotalCountByProjectId(String projectId, PageMaker pageMaker) throws SQLException {
        pageMaker.setProjectId(projectId);
        return issueDAO.getTotalCountByProjectId(pageMaker);
    }
    
    @Override
    public int getTotalCount(PageMaker pageMaker) throws SQLException {
        return issueDAO.getTotalCountByProjectId(pageMaker);
    }
    
    @Override
    public void createNewIssue(IssueDto issue) throws SQLException {
        issueDAO.insertNewIssue(issue);
    }
    
    @Override
    public IssueDto getIssueById(String issueId) throws SQLException {
        IssueDto issue = issueDAO.getIssueById(issueId);
        if (issue != null) {
            List<ReplyDto> replies = replyDAO.getRepliesByIssueId(issueId);
            issue.setComments(replies);
        }
        return issue;
    }
    
    @Override
    public void updateIssue(IssueDto issue) throws SQLException {
        issueDAO.updateIssue(issue);
    }
    
    @Override
    public void deleteIssue(String issueId) throws SQLException {
        replyDAO.deleteRepliesByIssueId(issueId);
        issueDAO.deleteIssue(issueId);
    }
}