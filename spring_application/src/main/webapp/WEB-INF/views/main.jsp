<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Linked</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.header {
	display: flex;
	justify-content: space-between;
	align-items: flex-start;
	padding: 5px 35px;
	background-color: #9B99FF;
}

.wrapper .header-left .logo {
	width: 200px;
	height: 75px;
	object-fit: cover;
}

.header-right {
	display: flex;
	flex-direction: column;
	align-items: flex-end;
}

.header-right-top {
	display: flex;
	gap: 10px;
	margin-bottom: 10px;
	align-items: center;
}

.login-btn {
	padding: 6px 12px;
	cursor: pointer;
	display: flex;
	align-items: center;
	gap: 6px;
}

.login-btn .user-icon {
	width: 16px;
	height: 16px;
}

.menu-list {
	display: flex;
	list-style: none;
	padding-left: 0;
	margin: 0;
	margin-top: 10px; /* 기존 .menu margin-top 대체 */
}

.menu {
	margin: 0 10px;
}

.nav-link {
	text-decoration: none;
	color: white;
}

.menu a {
	margin-left: 10px;
	margin-right: 10px;
	text-decoration: none;
	color: white;
}

.header-right a {
	margin-left: 20px;
	margin-right: 10px;
	text-decoration: none;
	color: white;
}
</style>
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<div class="header">
			<div class="header-left">
				<img
					src="${pageContext.request.contextPath}/resources/images/logo.png"
					alt="로고" class="logo">
			</div>

			<div class="header-right">



				<!-- 상단 로그인/마이페이지 영역 -->
				<div class="header-right-top">

					<!-- Notifications Dropdown Menu 알림-->

					<li class="nav-item dropdown" style="list-style-type: none;  ">
						<i class="far fa-bell" style="cursor: pointer;" ></i> 
						<span class="badge badge-warning navbar-badge" style=" user-select: none; cursor: pointer;">1</span>
						<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
							<span class="dropdown-header">15 Notifications</span>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> 
							<i class="fas fa-envelope mr-2"></i> 4 new messages 
							<span class="float-right text-muted text-sm">3 mins</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> 
							<i class="fas fa-users mr-2"></i> 8 friend requests 
							<span class="float-right text-muted text-sm">12 hours</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> 
							<i class="fas fa-file mr-2"></i> 3 new reports 
							<span class="float-right text-muted text-sm">2 days</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item dropdown-footer">See All
								Notifications</a>
						</div>
					</li>
			
					<!-- /.navbar -->


					<div class="login-btn"
						onclick="location.href='<%=request.getContextPath()%>/commons/logout';">
						<a><strong><em>Logout</em></strong></a>
					</div>
					<a href="/me/mypage.jsp"><strong><em>My Page</em></strong></a>
				</div>

				<!-- 메뉴 리스트 -->
				<ul class="menu-list">
					<c:forEach items="${menuList}" var="menu">
						<li class="menu"><a
							href="javascript:subMenu_go('${menu.mcode}');go_page('<%=request.getContextPath()%>${menu.murl}', '${menu.mcode}');"
							class="nav-link"> ${menu.mname} </a></li>
					</c:forEach>
				</ul>

			</div>
		</div>
		<iframe name="ifr" frameborder="0" style="width: 100%; height: 85vh;"></iframe>

	</div>

	<!-- content wrapper -->
	<footer class="footer"
		style="background-color: #9B99FF; padding: 20px 0; text-align: center; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-size: 14px; color: #fff; border-top: 1px solid #ddd;">
		<div class="footer-content" style="max-width: 1200px; margin: 0 auto;">
			<p style="margin: 5px 0;">© Team : Semicolon;</p>
			<p style="margin: 5px 0;">
				<a style="color: #fff; text-decoration: none; margin-right: 15px;">PL
					: 이민희</a> <a
					style="color: #fff; text-decoration: none; margin-right: 15px;">김재연,
					김지훈, 노은광, 손유정, 이진규</a> </br> <a style="color: #fff; text-decoration: none;">DW
					ACADEMY</a>
			</p>
		</div>
	</footer>


	<!-- Navbar -->


	<!-- Main Footer -->

	<!-- ./wrapper -->

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
	<!-- handlebars -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.8/handlebars.min.js"></script>
	<script type="text/x-handlebars-template" id="subMenu-list-template">
{{#each .}}	
<li class="nav-item subMenu" >
	<a href="javascript:go_page('<%=request.getContextPath() %>{{murl}}','{{mcode }}');"	class="nav-link">
		<i class="{{micon}}"></i>
		<p>{{mname}}</p>
	</a>
</li>
{{/each}}
</script>
	<script>
var sub_func= Handlebars.compile($("#subMenu-list-template").html());
function subMenu_go(mcode){
	//alert("subMenu : "+mcode);
	
	if(mcode=="M000000") {
		$('.subMenuList').html("");
		return;
	}
	
	$.ajax({
		url:"menu/subMenu?mcode="+mcode,
		method:"get",
		success:function(data){
			$('.subMenuList').html(sub_func(data));
		},
		error:function(error){
			alert("서버장애가 발생했습니다.");
		}
	});
	
}
</script>

	<script>
function go_page(url,mcode){
	
	var renewURL = location.href;
	
	//현재 주소 중 .do 뒤 부분이 있다면 날려버린다.
	renewURL = renewURL.substring(0, renewURL.indexOf("index")+5);
	
	
	if (mcode != 'M000000') {
	    renewURL += "?mcode="+mcode;
	}
   
    //페이지를 리로드하지 않고 페이지 주소만 변경할 때 사용
	history.pushState(mcode, null, renewURL);
	
	$('iframe[name="ifr"]').attr("src",url);	
}
</script>

	<c:if test="${not empty menu }">
		<script>
		go_page('<%=request.getContextPath()%>
			${menu.murl}',
					'${menu.mcode}');
			subMenu_go('${menu.mcode}'.substring(0, 3) + "0000");
		</script>
	</c:if>

</body>
</html>