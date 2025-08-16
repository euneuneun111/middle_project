
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="sidebar-wrapper">
	
	<nav class="sidebar-nav">
        <ul class="menu-list">
            <li class="menu-item">
                <a href="#" class="menu-link">
                    <i class="fas fa-tasks icon"></i> TASK
                </a>
            </li>
            <li class="menu-item active"> <%-- 현재 페이지(이슈) 활성화 --%>
                <a href="${pageContext.request.contextPath}/main/issuelist" class="menu-link">
                    <i class="fas fa-clipboard-list icon"></i> ISSUE
                </a>
            </li>
            <li class="menu-item">
                <a href="#" class="menu-link">
                    <i class="fas fa-project-diagram icon"></i> GANTT
                </a>
            </li>
            <li class="menu-item">
                <a href="#" class="menu-link">
                    <i class="fas fa-calendar-alt icon"></i> CALENDAR
                </a>
            </li>
            <li class="menu-item">
                <a href="#" class="menu-link">
                    <i class="fas fa-users icon"></i> MEETING
                </a>
            </li>
            <li class="menu-item">
                <a href="#" class="menu-link">
                    <i class="fas fa-chart-line icon"></i> BUDGET & PROGRESS
                </a>
            </li>
            <li class="menu-item">
                <a href="#" class="menu-link">
                    <i class="fas fa-file-alt icon"></i> REPORT
                </a>
            </li>
            <li class="menu-item">
                <a href="#" class="menu-link">
                    <i class="fas fa-share-alt icon"></i> SHARE
                </a>
            </li>
        </ul>
    </nav>
</div>
