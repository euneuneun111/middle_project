<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<html>
<head>
<meta charset="UTF-8">
<title>회의록 등록</title>
<script src="https://kit.fontawesome.com/a076d05399.js"
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="form-section" style="padding: 30px 100px">
		<form name="registForm" role="form" enctype="multipart/form-data">
			<div class="form-row">

				<div class="form-group col-md-6">
					<label>회의 일자</label> <input type="date" class="form-control"
						name="meetingDate" value="${meeting.meetingDate}">
				</div>
				<div class="form-group col-md-6">
					<label>주관자</label> <input type="text" class="form-control"
						name="organizer" value="${loginUser.name}" readonly>
				</div>

				<div class="form-group col-md-12">
					<label>참석자</label> <input type="text" class="form-control"
						name="attend" placeholder="참석자 명단"
						value="${loginUser.name}"> <small
						class="form-text text-muted"></small>
				</div>

			</div>



			<div class="form-group">
				<label>회의 명</label> <input type="text" class="form-control notNull"
					name="title" title="회의 명" placeholder="내용을 입력하세요.">
			</div>

			<div class="form-group">
				<label>회의 개요</label>
				<textarea class="form-control notNull" name="overview" rows="8"
					name="title" title="회의 개요" placeholder="내용을 입력하세요."></textarea>
			</div>

			<div class="form-group">
				<label>회의 내용</label>
				<textarea class="form-control notNull" name="content" rows="8"
					title="회의 내용" placeholder="내용을 입력하세요."></textarea>
			</div>



			<div class="d-flex justify-content-end">
				<button type="button" class="btn btn-submit mr-2"
					style="background: #9b99ff; color: #fff" onclick="regist_go();">저장</button>
				<button type="button" class="btn btn-default "
					onclick="CloseWindow();" style="color: #9b99ff">취소</button>
			</div>
		</form>
	</div>

	<script>
	function regist_go() {
		var form = document.forms.registForm;
		var inputNotNull = document.querySelectorAll(".notNull");

		for (var input of inputNotNull) {
			if (!input.value) {
				alert(input.getAttribute("title") + "은(는) 필수입니다.");
				input.focus();
				return;
			}
		}

		form.action = "regist.do";
		form.method = "post";
		form.submit();
	}
</script>


</body>