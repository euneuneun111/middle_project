<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>회의록 상세보기</title>

<body>
	<section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title">상세보기</h3>
					</div>
					<div class="form-group col-sm-12">
						<label for="title">회의 명</label> <input type="text"
							class="form-control" id="title" readonly disabled
							value="${ meeting.title }" />
					</div>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="meetingDate">회의 일자</label> <input type="text"
								class="form-control" id="meetingDate" readonly
								value='<fmt:formatDate
												value="${meeting.meetingDate}" pattern="yyyy-MM-dd" />' />
						</div>

						<div class="form-group col-md-6">
							<label for="organizer">주관자</label> <input type="text"
								class="form-control" id="writer" readonly
								value="${meeting.organizer}" />
						</div>

						<div class="form-group col-md-12">
							<label for="attend">참석자</label> <input type="text"
								class="form-control" id="attend" readonly
								value="${meeting.attend}" />
						</div>
					</div>

					<div class="form-group col-sm-12">
						<label for="overview">회의 개요</label>
						<div id="overview">${meeting.content }</div>
					</div>

					<div class="form-group col-sm-12">
						<label for="content">회의 내용</label>
						<div id="content">${meeting.content }</div>
					</div>


				</div>
<div class="card-tools">
							<div class="float-right">	
								<button type="button" class="btn btn-danger" id="removeBtn"
								onclick="remove();">삭 제</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-submit" id="modifyBtn" onclick="modify();" style="background: #9b99ff; color: #fff">수 정</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-default " id="cancelBtn" onclick="CloseWindow();" style="color: #9b99ff">취 소</button>
							</div>
						</div>


		</div>
	</section>

	<script>
		function modify() {
			location.href = "modify?id=${meeting.id}";
		}

		function remove() {
			let answer = confirm("정말 삭제하시겠습니까?");
			if(!answer) return;
			
			location.href = "remove?id=${meeting.id}";
		}
	</script>

</body>
