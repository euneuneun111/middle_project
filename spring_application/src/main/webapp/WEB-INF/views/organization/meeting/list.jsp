<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="UTF-8">
<%-- 공통 CSS 파일 연결 --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/sidebar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/issuelist.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<style>
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
			<div class="card-header border-0">
				<h1 class="card-title">Meeting</h1>
				<div
					class="card-tools d-flex justify-content-center align-items-center"
					onclick="OpenWindow('regist','회의록 등록', 1000,800);"
					style="background-color: #9b99ff; width: 120px; height: 30px; border-radius: 20px">
					<a class="btn btn-tool btn-sm text-white"> <i
						class="fas fa-plus"
						style="color: #fff; text-size: 6px; margin-right: 10px"></i>회의록 추가
					</a>
				</div>
			</div>
			<div class="card-body table-responsive p-0">


				<table class="table table-striped table-valign-middle">
					<thead>
						<tr>
							<th class="text-center" style="width: 10%">number</th>
							<th class="text-center" style="width: 40%">Title</th>
							<th class="text-center" style="width: 20%">writer</th>
							<th class="text-center" style="width: 30%">Date</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty meetingList}">
							<tr>
								<td colspan="5" class="text-center">해당내용이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty meetingList}">
							<c:forEach items="${meetingList}" var="meeting">
								<tr style="cursor: pointer;"
									onclick="OpenWindow('detail?id=${meeting.id}')">
									<td class="text-center" style="width: 10%">${meeting.id}</td>
									<td class="text-center" style="width: 40%">${meeting.title}</td>
									<td class="text-center" style="width: 20%">${meeting.organizer}</td>
									<td class="text-center" style="width: 30%"><fmt:formatDate
											value="${meeting.meetingDate}" pattern="yyyy-MM-dd" /></td>


								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>

				<br>

				<div style="display:${!empty meetingList ? 'visible':'none'};">
					<%@ include file="/WEB-INF/views/module/pagination.jsp"%>
				</div>
			</div>



		</div>




	</div>
</body>

