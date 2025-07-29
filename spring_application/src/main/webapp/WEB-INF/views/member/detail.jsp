<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<title>회원 상세</title>



<body>
	<div>
		<section class="content-header"
			style="padding: 1rem 150px; display: flex; align-items: center; justify-content: space-between;">

			<!-- 왼쪽 메뉴 -->

			<!-- 중앙 검색바 -->
			<div class="d-flex justify-content-center flex-grow-1"
				style="margin: 0 30px;">
				<form class="form-inline w-100" style="max-width: 400px;">
					<div class="input-group input-group-sm w-100">
						<input class="form-control form-control-navbar" type="search"
							placeholder="Search" aria-label="Search">
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
		<section class="content register-page">
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
									onclick="location.href='modify?id=${member.id}">수정</button>

								<span class="nav-link px-4"
									style="color: #ced4da; user-select: none;">/</span>

								<button type="button" class="btn btn-outline-danger btn-sm"
									style="border: none;" onclick="deleteProject();">삭제</button>
							</div>

							<div class="form-group row">
								<label for="id" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>아이디
								</label>
								<div class="col-sm-9 input-group input-group-sm">
									<input name="id" onblur="validation(this.name);"
										onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, &#39;&#39;);"
										type="text" class="form-control" id="id"
										placeholder="13글자 영문자,숫자 조합"> <span
										class="input-group-append-sm"> </span>
								</div>
							</div>
							<div class="form-group row">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>패스워드
								</label>
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="pwd" type="password"
										class="form-control" id="pwd"
										placeholder="20글자 영문자,숫자,특수문자 조합"
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
								<label for="authority" class="col-sm-3"
									style="font-size: 0.9em;">직책권한</label>
								<div class="col-sm-9 row">
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="role1" name="authorities" value="01"> <label
												for="role1" class="custom-control-label">사용자</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="role2" name="authorities" value="02"> <label
												for="role2" class="custom-control-label">운영자</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="role3" name="authorities" value="03"> <label
												for="role3" class="custom-control-label">관리자</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="authority" class="col-sm-3"
									style="font-size: 0.9em;">회원관리권한</label>
								<div class="col-sm-9 row">
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="user1" name="authorities" value="11"> <label
												for="user1" class="custom-control-label">등록</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="user2" name="authorities" value="13"> <label
												for="user2" class="custom-control-label">수정</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="user3" name="authorities" value="14"> <label
												for="user3" class="custom-control-label">삭제</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="authority" class="col-sm-3"
									style="font-size: 0.9em;">공지관리권한</label>
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
									<input name="email" type="email" class="form-control"
										id="email" placeholder="example@naver.com"
										onblur="validation(this.name);">
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
											style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label>
										<input style="width: 87px;" data-role="phone1"
											oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
											maxlength="4" onkeyup="phone1_max(this.value.length);"
											name="phone" type="text" class="form-control float-left" />
										<label class="float-left"
											style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label>
										<input style="width: 87px;" maxlength="4" data-role="phone2"
											name="phone" type="text" class="form-control float-left"
											oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
											onfocus="phone2();" />
									</div>
								</div>


							</div>
							<div class="card-footer" style="background-color: #fff;">


								<div class="col-sm-12 d-flex justify-content-end">
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




</body>




