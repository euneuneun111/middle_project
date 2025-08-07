<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>회원 등록</title>


<body>
	<!-- Content Wrapper. Contains page content -->

	<!-- /.content-wrapper -->

	<form id="regist" method="post" action="regist"
		enctype="multipart/form-data">
		<input type="hidden" name="writer" value="${loginUser.id}" />
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
							<div class="col-sm-12">&nbsp;</div>

						</div>
						<div class="form-group row">
							<div class="col-sm-9 input-group input-group-sm">
								<input name="title" onblur="validation(this.name);" type="title"
									class="form-control" id="title" placeholder="제목 입력"
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
								<input class="form-control" name="tgmoney" type="text"
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
								<input class="form-control" name="startDate" type="date"
									id="start" placeholder="시작" onblur="validation(this.name);" />
							</div>

							<!-- ~ 기호 -->
							<div class="text-center"
								style="width: 50px; font-size: 25px; color: #222; user-select: none;">
								~</div>

							<!-- 마감 날짜 -->
							<div class="col-sm-5 input-group-sm">
								<input class="form-control" name="endDate" type="date" id="end"
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
							<div
								class="col-sm-12 d-flex justify-content-between align-items-center">


								<!-- 왼쪽: 수정하기 버튼 -->
								<button type="button" class="btn col-sm-3"
									onclick="regist_go();"
									style="background-color: #9B99FF; color: #fff; border: none;">
									등&nbsp;&nbsp;록&nbsp;&nbsp;하&nbsp;&nbsp;기</button>

								<!-- 왼쪽: 수정하기 버튼 -->
								<button type="button" class="btn col-sm-3"
									onclick="history.go(-1);"
									style="background-color: #fff; border: 1px solid; color: #9B99FF;">
									취&nbsp;&nbsp;소&nbsp;&nbsp;하&nbsp;&nbsp;기</button>
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
							cols="150" placeholder="내용을 작성하세요."
							style="border: 1px solid #9B99FF; border-radius: 5px;">${pds.content}</textarea>

					</div>
				</div>
				<!-- /.card-body -->
			</div>

		</div>
	</form>


	<script>
	// 후원 옵션 추가 기능
	  function addCategory() {
	    const categorySelect = document.getElementById('categorySelect');
	    const newCategoryInput = document.getElementById('newCategory');
	    const newValue = newCategoryInput.value.trim();

	    if (newValue !== '') {
	      const option = document.createElement('option');
	      option.value = newValue;
	      option.textContent = newValue;
	      categorySelect.appendChild(option);
	      newCategoryInput.value = '';
	    } else {
	      alert('옵션 내용을 입력해주세요.');
	    }
	  }

	  // 유효성 검사 예시 함수
	  function validation(fieldName) {
  const field = document.getElementsByName(fieldName)[0];
  const value = field?.value?.trim();

  if (!value) {
    field.focus();
    return false;
  }

  // 숫자 필드일 경우 숫자 검증 (예: 금액)
  if (fieldName === 'tgmoney' && isNaN(value)) {
    alert("금액은 숫자로 입력해주세요.");
    field.focus();
    return false;
  }

  return true;
}

	  // 등록 버튼 눌렀을 때의 동작
	function regist_go() {
    let form = document.forms.regist;
    
    for (let element of form.elements) {
        switch (element.name) {
            case "title":
            case "tgmoney":
            case "writer":
            case "startDate":
            case "endDate":
                if (!element.value) {
                    alert(element.name + "은 필수입니다.");
                    element.focus();
                    return;
                }
                break;
            case "picture":
                if (!element.files || element.files.length === 0) {
                    alert("파일을 선택해주세요.");
                    element.click();
                    return;
                }
                break;
        }
    }

    form.action = "regist";
    form.method = "post";
    form.submit();
}
	</script>

</body>