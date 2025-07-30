<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>수정 하기</title>

<head></head>

<body>



	<c:if test="${empty member }">
		<script>
	alert("존재하지 않는 회원입니다.")
	history.go(-1);
</script>

	</c:if>


	<!-- Content Wrapper. Contains page content -->
	<div>
		<!-- Main content -->
		<form id="modify" method="post" action="">
			<div class="card card-solid"
				style="padding: 1rem 150px; display: flex; justify-content: space-evenly;">
				<div class="card-body">
					<div class="row">
						<div class="col-12 col-sm-4"
							style="position: relative; height: 450px; width: 400px; border: 1px solid #9B99FF; margin: 0 auto; border-radius: 5px;">
							<!-- 이미지 뷰어 -->
							<div id="pictureView"
								style="height: calc(100% - 50px); width: 100%;"></div>

							<!-- 하단 고정된 파일 선택 영역 -->
							<div class="mailbox-attachment-info"
								style="position: absolute; bottom: 0; left: 50%; transform: translateX(-50%); width: 80%; background-color: #fff;">
								<div class="input-group input-group-sm">
									<label for="inputFile"
										class="btn btn-warning btn-sm btn-flat input-group-addon"
										style="color: #fff; background-color: #9B99FF; border: none;">파일선택</label>
									<input id="inputFileName" class="form-control" type="text"
										name="tempPicture" disabled /> <input type="file"
										id="inputFile" name="picture" style="display: none;"
										onchange="picture_go();" />
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-6">

							<div class="form-group row">
								<div class="col-sm-12">&nbsp;</div>

							</div>
							<div class="form-group row">
								<div class="col-sm-9 input-group input-group-sm">
									<input name="id" onblur="validation(this.name);"
										onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');"
										type="text" class="form-control" id="id" placeholder="제목 입력"
										style="border: none; font-size: 1.17em;">
								</div>
							</div>

							<div class="form-group row" style="margin-bottom: 3px">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>목표 금액
								</label>
							</div>
							<div class="form-group row" style="margin-bottom: 3px">
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="name" type="text"
										class="form-control" id="name" placeholder="금액을 입력하세요"
										onblur="validation(this.name);" onkeyup="" />
								</div>

							</div>
							<div class="form-group row" style="margin-bottom: 3px">
								<label for="name" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>펀딩 기간 
							</div>
							<div class="form-group row d-flex align-items-center"
								style="margin-bottom: 3px;">

								<!-- 시작 날짜 -->
								<div class="col-sm-5 input-group-sm">
									<input class="form-control" name="start" type="text" id="start"
										placeholder="시작" onblur="validation(this.name);" />
								</div>

								<!-- ~ 기호 -->
								<div class="text-center"
									style="width: 50px; font-size: 25px; color: #222; user-select: none;">
									~</div>

								<!-- 마감 날짜 -->
								<div class="col-sm-5 input-group-sm">
									<input class="form-control" name="end" type="text" id="end"
										placeholder="마감" onblur="validation(this.name);" />
								</div>

							</div>

							<div class="form-group row">
								<div class="col-sm-12">&nbsp;</div>

							</div>


							<div class="form-group row">
								<div class="col-sm-12">&nbsp;</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-6">
									<select class="form-control" id="categorySelect"
										name="category">
										<option value="">-- 후원 옵션 추가 --</option>
									</select>
								</div>

								<div class="col-sm-6 d-flex gap-2">
									<input type="text" id="newCategory" class="form-control me-2"
										placeholder="내용 입력">
									<button type="button" class="btn btn-secondary"
										onclick="addCategory()"
										style="background-color: #9B99FF; color: #fff; white-space: nowrap; border: none;">
										추&nbsp;&nbsp;가</button>
								</div>
							</div>

							<div class="card-footer" style="background-color: #fff;">
								<div class="row justify-content-end">
									<div class="col-sm-3">
										<button type="button" class="btn btn-block"
											onclick="regist_go();"
											style="background-color: #9B99FF; color: #fff;">수&nbsp;&nbsp;정</button>
									</div>

									<div class="col-sm-3">
										<button type="button" class="btn btn-block"
											onclick="history.go(-1);"
											style="background-color: #fff; color: #9B99FF; border: 1px solid #9B99FF">취&nbsp;&nbsp;소</button>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="row mt-4">
						<nav class="w-100">
							<div class="nav nav-tabs" id="product-tab" role="tablist">
								<a class="nav-item nav-link active" id="product-desc-tab"
									data-toggle="tab" href="#product-desc" role="tab"
									aria-controls="product-desc" aria-selected="true">프로젝트 소개</a>

							</div>
						</nav>
						<div class="tab-content p-3" id="nav-tabContent">
							<textarea class="textarea" name="content" id="content" rows="3"
								cols="150" placeholder="문의내용을 작성하세요."
								style="border: 1px solid #9B99FF; border-radius: 5px;"></textarea>



						</div>
					</div>
					<!-- /.card-body -->
				</div>

			</div>
		</form>
		<!-- /.content-wrapper -->




		<script>
function modify_go(){
	let form = document.forms.modify;
	for(let element of form ){
		switch(element.name){
			case "pwd":case "email":case "phone":case "name":
			if(!element.value){
				alert(element.name+"은 필수입니다.");					
				if(element.name=="picture"){
					element.click();
				}else{
					element.focus();
				}					
				return;
			}
			
			if(element.name=="phone" && element.value ){
				let regExp = /[0-9]{10,11}$/g;
				if(!element.value.match(regExp)){
					alert("전화번호 형식이 올바르지 않습니다.");
					element.focus();
					return;
				}
			}	
		}
	}
	
	
	form.action="modify";
	form.method="post";
	form.submit();
}

</script>
		<script>
	MemberPictureBackground("<%=request.getContextPath()%>");
</script>
</body>
