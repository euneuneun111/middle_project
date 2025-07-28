<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="com.application.dto.MemberVO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>회원 목록</title>
<style>
.person-info {
	display: block;
	width: 100%;
	height: 200px;
	background-color: #ccc;
}

.row {
	display: flex;
	flex-wrap: wrap;
	margin: -0.5rem;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.alert-info {
	padding: 1rem;
	background-color: #d1ecf1;
	color: #0c5460;
	border-radius: 4px;
}
</style>

<script>
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
  let winleft = (screen.width - WinWidth) / 2;
  let wintop = (screen.height - WinHeight) / 2;
  var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth
            +",height="+ WinHeight +", top="+ wintop +", left=" 
            + winleft +", resizable=yes, status=yes"  );
  win.focus(); 
}

// 회원 사진 없을 때 백그라운드 이미지 설정용
function MemberPictureBackground(contextPath){
  let elements = document.querySelectorAll('.person-info');
  for(let element of elements){
    let id = element.getAttribute("data-id");   
    element.style.backgroundImage = "url('"+contextPath+"/member/getPicture?id="+id+"')";
    element.style.backgroundPosition = "center";
    element.style.backgroundRepeat = "no-repeat";
    element.style.backgroundSize = "cover";
  }
}

window.onload = function(){
  MemberPictureBackground("<%=request.getContextPath()%>");
};
</script>

</head>
<body>

	<section class="content-header"
		style="padding: 1rem; display: flex; align-items: center; justify-content: space-between; padding: 12px 150px;">
		<div
			class="container-fluid d-flex justify-content-start align-items-center">
			<ul class="navbar-nav flex-row" style="font-weight: bold;">
				<li class="nav-item"><a href="#" class="nav-link px-4"
					style="color: #9B99FF;">최신</a></li>
				<li class="nav-item"><span href="" class="nav-link px-4"
					style="color: #ced4da; user-select: none;">/</span></li>
				<li class="nav-item"><a href="#" class="nav-link px-4"
					style="color: #9B99FF;">인기</a></li>
			</ul>

		</div>

		<div class="d-flex justify-content-center flex-grow-1"
			style="margin: 0 30px;">
			<form class="form-inline w-100" style="max-width: 400px;">
				<div class="input-group input-group-sm w-100">
					<input class="form-control form-control-navbar" type="search"
						placeholder="Search" aria-label="Search">
					<div class="input-group-append">
						<button class="btn btn-navbar" type="submit"
							style="border: 1px solid #ced4da">
							<i class="fas fa-search"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-2 mb-2">
			<a href="regist" class="btn btn-block btn-secondary btn-lg"
				style="background-color: #9B99FF; border: none; display: inline-block; text-align: center;">
				Project Add </a>
		</div>



	</section>

	<section class="content" style="padding: 0 150px;">
		<div class="row justify-content-center">

			<c:if test="${empty memberList}">
				<div class="col-12">
					<div class="alert alert-info text-center">해당 데이터가 존재하지 않습니다.</div>
				</div>
			</c:if>

			<c:forEach var="member" items="${memberList}">
				<fmt:formatDate pattern="yyyy-MM-dd" var="regDate"
					value="${member.regDate}" />

				<div class="col-md-4 mb-3">
					<div class="card h-100"
						onclick="OpenWindow('detail?id=${member.id}','회원정보',700,800)"
						style="cursor: pointer;">

						<c:choose>
							<c:when test="${not empty member.picture}">
								<div class="person-info mb-2" data-id="${member.id}"
									style="border: 1px solid #fff; object-fit: cover; height: 150px; background-color: #f0f0f0;"></div>
							</c:when>
							<c:otherwise>
								<div class="person-info mb-2 text-center text-muted"
									style="height: 150px; background-color: #f9f9f9; display: flex; align-items: center; justify-content: center;">
									No Image</div>
							</c:otherwise>
						</c:choose>

						<div class="card-body">
							<h5 class="card-title">${member.name}
								<small style="color: #666;">(${member.id})</small>
							</h5>
							<p class="card-text" style="font-size: 0.9rem;">
								<strong>이메일:</strong> ${member.email}<br /> <strong>전화번호:</strong>
								${member.phone}<br /> <strong>가입일:</strong> ${regDate}
							</p>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</section>

	<!-- 페이징 모듈 포함 -->
	<div style="padding: 1rem;">
		<%@ include file="/WEB-INF/views/module/pagination.jsp"%>
	</div>

	<script>
	MemberPictureBackground("<%=request.getContextPath()%>");

</script>

</body>
</html>
