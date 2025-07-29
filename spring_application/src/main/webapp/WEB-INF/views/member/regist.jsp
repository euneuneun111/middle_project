<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>회원 등록</title>


<body>
	<!-- Content Wrapper. Contains page content -->

	<!-- /.content-wrapper -->

<form id="regist" method="post" action="">
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
						style="position: absolute; bottom: 0; left: 50%; transform: translateX(-50%); width: 80%;">
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
						<label for="id" class="col-sm-3" style="font-size: 0.9em;">
							<span style="color: red; font-weight: bold;">*</span>아이디
						</label>
						<div class="col-sm-9 input-group input-group-sm">
							<input name="id" onblur="validation(this.name);"
								onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, &#39;&#39;);"
								type="text" class="form-control" id="id"
								placeholder="13글자 영문자,숫자 조합"> <span
								class="input-group-append-sm">
								<button type="button" onclick="idCheck_go();"
									class="btn btn-info btn-sm btn-append">중복확인</button>
							</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
							<span style="color: red; font-weight: bold;">*</span>패스워드
						</label>
						<div class="col-sm-9 input-group-sm">
							<input class="form-control" name="pwd" type="password"
								class="form-control" id="pwd" placeholder="20글자 영문자,숫자,특수문자 조합"
								onblur="validation(this.name);" />
						</div>

					</div>
					<div class="form-group row">
						<label for="name" class="col-sm-3" style="font-size: 0.9em;">
							<span style="color: red; font-weight: bold;">*</span>이 름
						</label>
						<div class="col-sm-9 input-group-sm">
							<input class="form-control" name="name" type="text"
								class="form-control" id="name" placeholder="이름을 입력하세요"
								onblur="validation(this.name);" onkeyup="" />
						</div>

					</div>
					<div class="form-group row">
						<label for="authority" class="col-sm-3" style="font-size: 0.9em;">직책권한</label>
						<div class="col-sm-9 row">
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox" id="role1"
										name="authorities" value="01"> <label for="role1"
										class="custom-control-label">사용자</label>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox" id="role2"
										name="authorities" value="02"> <label for="role2"
										class="custom-control-label">운영자</label>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox" id="role3"
										name="authorities" value="03"> <label for="role3"
										class="custom-control-label">관리자</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="authority" class="col-sm-3" style="font-size: 0.9em;">회원관리권한</label>
						<div class="col-sm-9 row">
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox" id="user1"
										name="authorities" value="11"> <label for="user1"
										class="custom-control-label">등록</label>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox" id="user2"
										name="authorities" value="13"> <label for="user2"
										class="custom-control-label">수정</label>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox" id="user3"
										name="authorities" value="14"> <label for="user3"
										class="custom-control-label">삭제</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="authority" class="col-sm-3" style="font-size: 0.9em;">공지관리권한</label>
						<div class="col-sm-9 row">
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox"
										id="notice1" name="authorities" value="21"> <label
										for="notice1" class="custom-control-label">등록</label>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox"
										id="notice2" name="authorities" value="23"> <label
										for="notice2" class="custom-control-label">수정</label>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="custom-control custom-checkbox">
									<input class="custom-control-input" type="checkbox"
										id="notice3" name="authorities" value="24"> <label
										for="notice3" class="custom-control-label">삭제</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="email" class="col-sm-3" style="font-size: 0.9em;">이메일</label>
						<div class="col-sm-9 input-group-sm">
							<input name="email" type="email" class="form-control" id="email"
								placeholder="example@naver.com" onblur="validation(this.name);">
						</div>
					</div>
					<div class="form-group row">
						<label for="phone" class="col-sm-3 control-label">전화번호</label>
						<div class="col-sm-9">
							<div class="input-group-sm">
								<select style="width: 90px;" name="phone" id="phone"
									class="form-control float-left">
									<option value="">-선택-</option>
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="017">017</option>
									<option value="018">018</option>
								</select> <label class="float-left"
									style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label> <input
									style="width: 87px;" data-role="phone1"
									oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
									maxlength="4" onkeyup="phone1_max(this.value.length);"
									name="phone" type="text" class="form-control float-left" /> <label
									class="float-left" style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label>
								<input style="width: 87px;" maxlength="4" data-role="phone2"
									name="phone" type="text" class="form-control float-left"
									oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
									onfocus="phone2();" />
							</div>
						</div>


					</div>
					<div class="card-footer" style="background-color: #fff;">
						<div class="row">
							<div class="col-sm-6">
								<button type="button" class="btn btn-block"
									onclick="regist_go();"
									style="background-color: #9B99FF; color: #fff;">등&nbsp;&nbsp;록</button>

							</div>

							<div class="col-sm-6">

								<button type="button" class="btn btn-block"
									onclick="regist_go();"
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


	<script>
var checkID = "";
function idCheck_go(){
	//alert("click idCheck");
	
	let inputID = $('input[name="id"]');
	if(!inputID.val()){
		alert("아이디를 입력하세요");
		inputID.focus();
		return;			
	}
	
	if(!validation('id')) return;
	
	$.ajax({
		url:"idCheck?id="+inputID.val(),
		method:"get",
		success:function(data){
			if(data=="duplicated"){
				alert("이미 사용중인 아이디입니다.");
				inputID.focus();
				return;		
			}else{
				alert("사용 가능한 아이디입니다.");
				checkID = inputID.val();
				$('input[name="pwd"]').focus();
				return;
			}
		},
		error:function(error){
			alert("시스템장애로 인해 아이디 중복확인이 불가합니다.");
		}
		
	});
}


$('input[name="phone"]').focus(function(){		
	if(!$('select[name="phone"]').val()){
		alert("국번을 먼저 선택하세요.");
		$('select[name="phone"]').focus();
	}
});

function phone1_max(count){
	if(count==4) $('input[data-role="phone2"]').focus();
}

function phone2(){
	let phone1 = $('input[data-role="phone1"]');		
	if($('select[name="phone"]').val() &&  phone1.val().length < 4 ){
		alert("전화번호 앞자리 4개를 작성하세요.");
		phone1.focus();
	}
}
</script>

<Script>

function regist_go(){
	let form = document.getElementById("regist"); // ✅ 여기 수정
	for (let element of form.elements) {
		switch(element.name){
			case "id": case "pwd": case "email": case "phone": case "name": case "picture":
				if (!element.value) {
					alert(element.name + "은 필수입니다.");
					if (element.name == "picture") {
						element.click();
					} else {
						element.focus();
					}
					return;
				}

				if (element.name == "id" && element.value != checkID) {
					alert("아이디 중복확인은 필수입니다.");
					return;
				}
		}
	}

	form.action = "regist";
	form.method = "post";
	form.submit();
}
</Script>
	
</body>











