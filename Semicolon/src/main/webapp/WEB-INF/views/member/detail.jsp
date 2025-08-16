<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<title>상세 페이지</title>

<style>
/* .heart-button {
	border: none;
	background: none;
	color: red;
	font-size: 24px;
	cursor: pointer;
}  */
.heart-button {
	padding: 10px 20px;
	font-size: 16px;
	background-color: #f44336;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
}

.heart-count {
	margin-left: 10px;
	font-size: 18px;
}
</style>



<body>
	<div>
		<section
			class="content-header d-flex align-items-center justify-content-between"
			style="padding: 1rem 150px; position: relative;">

			<!-- 왼쪽 뒤로가기 버튼 -->
			<button onclick="history.go(-1);"
				style="position: absolute; top: 10px; left: 150px; background: none; border: none;">
				<i class="fa-solid fa-arrow-left"
					style="font-size: 24px; color: #9B99FF;"></i>
			</button>

			<!-- 중앙 검색바 -->
			<div class="mx-auto">
				<form class="form-inline" style="max-width: 400px;">
					<div class="input-group input-group-sm w-100">
						<input class="form-control" type="search" placeholder="Search">
						<div class="input-group-append">
							<button class="btn btn-navbar" type="submit"
								style="border: 1px solid #ced4da">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</section>

		<!-- Main content -->
		<section class="content register-page" style="background-color: #fff">
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
							<div class="col-sm-12 d-flex justify-content-end">
								<button type="button"
									class="btn btn-outline-secondary btn-sm custom-hover"
									style="border: none;"
									onclick="location.href='modify?id=${member.id}'">수정</button>


								<span class="nav-link px-4"
									style="color: #ced4da; user-select: none;">/</span>

								<button type="button" class="btn btn-outline-danger btn-sm"
									style="border: none;" onclick="deleteProject();">삭제</button>
							</div>

							<div class="form-group row" style="margin-bottom: 3px">
								<label for="id" class="col-sm-12" style="font-size: 0.9em;">
									<h3 class="input-group-append-sm">${member.id}</h3>
								</label>

							</div>
							<div class="form-group row" style="margin-bottom: 3px">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>누적 금액
								</label>
							</div>
							<div class="form-group row" style="margin-bottom: 3px">
								<div class="col-sm-9 input-group-sm">
									<span style="color: red; font-weight: bold;">${member.id}
									</span>
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

									<!-- 왼쪽: 하트 + 공유 -->
									<div>
										<button class="heart-button"
											style="border: none; background-color: #fff;" id="heartBtn">
											<i class="fa-regular fa-heart" style="color: red;"></i>
										</button>

										<span id="heartCount" class="heart-count">${member.heart}</span>

										<button class="heart-button"
											style="border: none; background-color: #fff; margin-left: 30px;"
											id="shareBtn">
											<i class="fa-solid fa-share-nodes" style="color: #333;"></i>
										</button>
									</div>

									<!-- 오른쪽: 후원하기 버튼 -->
									<button type="button" class="btn col-sm-6"
										onclick="regist_go();"
										style="background-color: #9B99FF; color: #fff; border: none;">
										후&nbsp;&nbsp;원&nbsp;&nbsp;하&nbsp;&nbsp;기</button>
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
								<div class="col-auto">
									<button type="button"
										class="btn btn-outline-primary btn-sm me-2"
										onclick="OpenWindow('inquiryForm','문의하기',700,800);">문의하기</button>

									<button type="button" class="btn btn-outline-danger btn-sm"
										onclick="reportProject();">신고하기</button>


								</div>
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
	MemberPictureBackground("<%=request.getContextPath()%>");
</script>

	<script>
function remove_go(){
	//alert("click remove btn");
	let answer = prompt("삭제할 회원의 아이디를 입력하세요.");
	if(answer!='${member.id}') {
		alert("아이디가 일치하지 않습니다.");
		return;
	}
	
	location.href="remove?id=${member.id}";
}

</script>

	<script>
  const heartBtn = document.getElementById('heartBtn');

  heartBtn.addEventListener('click', () => {
    const icon = heartBtn.querySelector('i');
    // 클래스가 빈 하트이면 -> 꽉 찬 하트로
    if (icon.classList.contains('fa-regular')) {
      icon.classList.remove('fa-regular');
      icon.classList.add('fa-solid');
    } else {
      // 꽉 찬 하트이면 -> 빈 하트로
      icon.classList.remove('fa-solid');
      icon.classList.add('fa-regular');
    }
  });
  
  
</script>

	<script>
    let count = 0;

    function increaseHeart() {
      count++;
      document.getElementById('heartCount').innerText = count;
    }
  </script>



</body>