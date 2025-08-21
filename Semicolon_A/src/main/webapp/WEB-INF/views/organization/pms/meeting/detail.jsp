<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<title>회의록 상세보기</title>

<body>
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1>상세보기</h1>
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
	
	<div class="card-header"></div>
					<!--end card-header  -->
					<div class="card-body">

	<div class="form-section" style="padding: 30px 100px">
		<form name="registForm" role="form" enctype="multipart/form-data">
			<table class="table table-bordered" style="width: 100%; background: #fff;">
				<tr>
					<th style="width: 120px; background: #e0e0e0; text-align: center; padding: 20px 16px;">회의일자</th>
					<td style="width: 200px; font-size: 18px; vertical-align: middle; padding: 10px 8px;">
						<fmt:formatDate value="${meeting.meetingDate}" pattern="yyyy-MM-dd"/>
					</td>
					<th style="width: 80px; background: #e0e0e0; text-align: center; padding: 20px 16px;">주관자</th>
					<td>
						<input type="text" class="form-control" name="organizer" value="${loginUser.name}" readonly>
					</td>
				</tr>
				<tr>
					<th style="background: #e0e0e0; text-align: center; padding: 20px 16px;">참석자</th>
					<td colspan="3">
						<input type="text" class="form-control" name="attend" placeholder="참석자 명단" value="${loginUser.name}" readonly>
					</td>
				</tr>
				<tr>
					<th style="background: #e0e0e0; text-align: center; padding: 20px 16px;">회의명</th>
					<td colspan="3">
						<input type="text" class="form-control notNull" name="title" title="회의 명" value="${meeting.title}" readonly>
					</td>
				</tr>
				<tr>
					<th style="background: #e0e0e0; text-align: center; vertical-align:middle;" >회의개요</th>
					<td colspan="3">
						<textarea class="form-control notNull" name="overview" rows="5" title="회의 개요"  readonly>${meeting.overview}</textarea>
					</td>
				</tr>
				<tr>
					<th style="background: #e0e0e0; text-align: center; vertical-align:middle;">회의내용</th>
					<td colspan="3">
						<textarea class="form-control notNull" name="content" rows="8" title="회의 내용" readonly>${meeting.content}</textarea>
					</td>
				</tr>
			</table>

			<div class="card-tools" style="width: 99%; text-align: right;">
				<div class="float-right">
					<button type="button" class="btn btn-danger" id="removeBtn"
						onclick="remove();">삭 제</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-submit" id="modifyBtn"
						onclick="modify();" style="background: #9b99ff; color: #fff">수
						정</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-default " id="cancelBtn"
						onclick="CloseWindow();" style="color: #9b99ff">취 소</button>
				</div>
			</div>
		</form>
	</div>

	<script>
		function modify() {
			location.href = "modify?id=${meeting.id}";
		}

		function remove() {
			let answer = confirm("정말 삭제하시겠습니까?");
			if (!answer)
				return;

			location.href = "remove?id=${meeting.id}";
		}
	</script>

	<script>
	function toggleApproval(meetingId) {
    fetch('updateApprovalStatus', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: meetingId })
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            let btn = document.getElementById('approvalBtn');
            if (data.newStatus === 'Y') {
                btn.textContent = '승인';
                btn.classList.remove('btn-danger');
                btn.classList.add('btn-success');
            } else {
                btn.textContent = '미승인';
                btn.classList.remove('btn-success');
                btn.classList.add('btn-danger');
            }
        } else {
            alert('변경 실패');
        }
    })
    .catch(err => console.error(err));
}
	</script>

</body>
