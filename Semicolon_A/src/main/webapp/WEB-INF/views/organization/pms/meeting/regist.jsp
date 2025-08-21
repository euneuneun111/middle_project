<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="UTF-8">
<title>회의록 등록</title>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>

<body>
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1>등록하기</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="list.do"> <i
								class="fa fa-dashboard"></i>회의록
						</a></li>

					</ol>
				</div>
			</div>
		</div>
	</section>


	<div class="form-section" style="padding: 30px 100px">
		<form name="registForm" role="form" enctype="multipart/form-data">
			<table class="table table-bordered" style="width: 100%; background: #fff;">
				<tr>
					<th style="width: 120px; background: #e0e0e0; text-align: center; padding: 20px 16px;">회의일자</th>
					<td style="width: 200px; heigh: 10px;">
						<input type="date" class="form-control" name="meetingDate" value="${meeting.meetingDate}">
					</td>
					<th style="width: 80px; background: #e0e0e0; text-align: center; padding: 20px 16px;">주관자</th>
					<td>
						<input type="text" class="form-control" name="organizer" value="${loginUser.name}" readonly>
					</td>
				</tr>
				<tr>
					<th style="background: #e0e0e0; text-align: center;">참석자</th>
					<td colspan="3">
						<input type="text" class="form-control" name="attend" placeholder="참석자 명단" value="${loginUser.name}">
					</td>
				</tr>
				<tr>
					<th style="background: #e0e0e0; text-align: center;">회의명</th>
					<td colspan="3">
						<input type="text" class="form-control notNull" name="title" title="회의 명" placeholder="내용을 입력하세요.">
					</td>
				</tr>
				<tr>
					<th style="background: #e0e0e0; text-align: center;">회의개요</th>
					<td colspan="3">
						<textarea class="form-control notNull" name="overview" rows="5" title="회의 개요" placeholder="내용을 입력하세요."></textarea>
					</td>
				</tr>
				<tr>
					<th style="background: #e0e0e0; text-align: center;">회의내용</th>
					<td colspan="3">
						<textarea class="form-control notNull" name="content" rows="8" title="회의 내용" placeholder="내용을 입력하세요."></textarea>
					</td>
				</tr>
			</table>

		<div class="d-flex justify-content-end mt-3">
				<button type="button" class="btn mr-2"
					style="background: #9b99ff; color: #fff" onclick="regist_go();">저장</button>
				<button type="button" class="btn btn-default" onclick="CloseWindow();" style="color: #9b99ff">취소</button>
			</div>
		</form>
	</div>

	<script>
	function regist_go() {
		var form = document.forms.registForm;
		var inputNotNull = document.querySelectorAll(".notNull");

		for (var input of inputNotNull) {
			if (!input.value.trim()) {
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
