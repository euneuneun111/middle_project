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
								<select class="form-control" id="categorySelect" name="category">
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
								<div class="col-sm-3 d-flex justify-content-center">
									<button type="button" class="btn btn-block"
										onclick="regist_go();"
										style="background-color: #9B99FF; color: #fff; width: 100%;">등&nbsp;&nbsp;록</button>
								</div>

								<div class="col-sm-3 d-flex justify-content-center">
									<button type="button" class="btn btn-block"
										onclick="history.go(-1);"
										style="background-color: #fff; color: #9B99FF; border: 1px solid #9B99FF; width: 100%;">취&nbsp;&nbsp;소</button>
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

	<script>
  function addCategory() {
    const newCategory = document.getElementById("newCategory").value.trim();
    const select = document.getElementById("categorySelect");

    if (newCategory === "") {
      alert("추가할 카테고리를 입력하세요.");
      return;
    }

    // 이미 존재하는지 확인
    for (let i = 0; i < select.options.length; i++) {
      if (select.options[i].text === newCategory) {
        alert("이미 존재하는 항목입니다.");
        return;
      }
    }

    const newOption = document.createElement("option");
    newOption.value = newCategory.toLowerCase();
    newOption.text = newCategory;

    select.appendChild(newOption);
    select.value = newOption.value; // 새 항목을 자동 선택
    document.getElementById("newCategory").value = ""; // 입력창 초기화
  }
</script>

</body>
