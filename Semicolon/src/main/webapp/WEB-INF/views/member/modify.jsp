<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
	



	</section>
		<!-- Main content -->
		<section class="content register-page" style="background-color:#fff">
			<div class="card card-solid"
				style="padding: 0 150px; display: flex; justify-content: space-evenly;">
				<div class="card-body" style="padding: 0px">
					<div class="row">
						<div class="col-12 col-sm-4"
							style="position: relative; height: 450px; width: 400px; border: 1px solid #9B99FF; margin: auto auto; border-radius: 5px;">
							<!-- 이미지 뷰어 -->
							<div id="pictureView"
								style="height: calc(100% - 50px); width: 100%;"></div>

							<!-- 하단 고정된 파일 선택 영역 -->

						</div>
						<div class="col-12 col-sm-6">
							</br>
							<div class="form-group row" style="margin-bottom: 3px">
								<label for="id" class="col-sm-12" style="font-size: 0.9em;">
									<h3 class="input-group-append-sm">제목</h3>
								</label>

							</div>
							<div class="form-group row" style="margin-bottom: 3px">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>누적 금액
								</label>
							</div>
							<div class="form-group row" style="margin-bottom: 3px">
								<div class="col-sm-9 input-group-sm">
									<span style="color: red; font-weight: bold;">${member.id} </span>
								</div>

							</div>
							</br>
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

							<div class="form-group row" style="margin-bottom: 3px">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>목표 금액
								</label>
							</div>

							<div class="form-group row" style="margin-bottom: 3px">
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="name" type="text"
										class="form-control" id="name" placeholder="목표금액을 입력하세요"
										onblur="validation(this.name);" onkeyup="" />
								</div>
							</div>
							<br> </br>
							<div class="form-group row">
								<div class="col-sm-6">
									<select class="form-control" id="categorySelect"
										name="category">
										<option value="">-- 후원 옵션 --</option>
									</select>
								</div>
							</div>



							<div class="card-footer" style="background-color: #fff;">
								<div
									class="col-sm-12 d-flex justify-content-between align-items-center">


									<!-- 왼쪽: 수정하기 버튼 -->
									<button type="button" class="btn col-sm-3"
										onclick="regist_go();"
										style="background-color: #9B99FF; color: #fff; border: none;">
										수&nbsp;&nbsp;정&nbsp;&nbsp;하&nbsp;&nbsp;기</button>
												
									<!-- 왼쪽: 수정하기 버튼 -->
									<button type="button" class="btn col-sm-3"
										onclick="history.go(-1);"
										style="background-color: #fff; border: 1px solid; color:#9B99FF;">
										취&nbsp;&nbsp;소&nbsp;&nbsp;하&nbsp;&nbsp;기</button>
								</div> 
							</div>

						</div>
					</div>
					<div class="row mt-4">
						<nav class="w-100">
							<div class="row mt-4 align-items-center justify-content-between">
								<!-- 왼쪽: 탭 -->
								<div class="col d-flex">
									<div class="nav nav-tabs" id="product-tab" role="tablist">
										<a class="nav-item nav-link active" id="product-desc-tab"
											data-toggle="tab" href="#product-desc" role="tab"
											aria-controls="product-desc" aria-selected="true">프로젝트 소개</a>
									</div>
								</div>

								<!-- 오른쪽: 버튼 -->
					
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

		</section>

		<!-- /.content -->
	</div>
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
    
    
    
    