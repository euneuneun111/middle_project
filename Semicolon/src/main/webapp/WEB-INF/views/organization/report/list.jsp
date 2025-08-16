<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
String today = sdf.format(new java.util.Date());
%>

<html>
<head>
<meta charset="UTF-8">
<%-- 공통 CSS 파일 연결 --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css">
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/sidebar.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<style>
/* 추가적으로 issueList.jsp에만 적용될 스타일이 있다면 여기에 작성 */
/*
        .temporary-main-header는 issueList.jsp에서 제거하기로 했으니
        CSS에서도 이 부분을 삭제하는 것을 고려할 수 있습니다.
        만약 남겨둘 예정이라면 여기에 위치시키세요.
        */
.temporary-main-header {
	display: flex;
	justify-content: flex-end;
	padding-bottom: 20px;
	margin-bottom: 20px;
}

.temporary-main-header .nav-item {
	font-size: 16px;
	font-weight: bold;
	margin-left: 30px;
	color: #555;
	text-decoration: none;
}

.temporary-main-header .nav-item:hover {
	color: #6A3CD7;
}
</style>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/WEB-INF/views/commons/sidebar.jsp" />

		<div class="card col-sm-9" style="padding: 0 30px; margin: 30px auto;">

			<!-- 카드 헤더 -->
			<div class="card-header border-0 d-flex align-items-center">
				<!-- 왼쪽: 제목 -->
				<h1 class="card-title mb-0 mr-3">업무 보고</h1>

				<!-- 가운데: 담당자 + 날짜 -->
				<div
					class="d-flex align-items-center justify-content-center flex-grow-1">
					<i class="fas fa-user mr-1"></i> <select id="manager"
						name="manager" class="form-control form-control-sm mr-3"
						style="width: 150px; margin-right: 6px;">
						<option value="">-- 담당자 선택 --</option>
						<c:forEach items="${managerList}" var="manager">
							<option value="${manager.id}">${manager.name}</option>
						</c:forEach>
					</select> <i class="fas fa-calendar-alt mr-1" style="margin-right: 6px;"></i>
					<input type="date" id="reportDate" name="reportDate"
						class="form-control form-control-sm" style="width: 160px;"
						value="<%=today%>">
				</div>

				<!-- 오른쪽: 추가 버튼 -->
				<div
					class="card-tools d-flex justify-content-center align-items-center ml-auto"
					style="background-color: #9b99ff; width: 100px; height: 30px; border-radius: 20px; cursor: pointer;"
					onclick="OpenWindow('regist', '업무 보고', 1000,600);">
					<a class="btn btn-tool btn-sm text-white"> <i
						class="fas fa-plus mr-1"></i>추가
					</a>
				</div>
			</div>


			<!-- 카드 바디 -->
			<div class="card-body table-responsive p-0">
				<table class="table table-striped table-valign-middle">
					<thead>
						<tr>
							<th class="text-center" style="width: 10%;">번호</th>
							<th class="text-center" style="width: 30%;">업무 제목</th>
							<th class="text-center" style="width: 15%;">보고자</th>
							<th class="text-center" style="width: 20%;">날짜</th>
							<th class="text-center" style="width: 15%;">관리자 확인</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty reportList}">
								<tr>
									<td colspan="5" class="text-center">해당 내용이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${reportList}" var="report">
									<tr style="cursor: pointer;"
										onclick="location.href='detail?rno=${report.rno}';">
										<td class="text-center">${report.rno}</td>
										<td class="text-left">${report.title}</td>
										<td class="text-center">${report.writer}</td>
										<td class="text-center"><fmt:formatDate
												value="${report.regDate}" pattern="yyyy-MM-dd" /></td>
										<td class="text-center">${report.check}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			
			<div style="padding: 1rem;">
		<%@ include file="/WEB-INF/views/module/reportpagination.jsp"%>
	</div>
			

		</div>
	</div>

</body>

</html>