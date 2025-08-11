<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<title>신고</title>
<body>
	<div>

		<section class="content register-page">
			<div class="register-box">
				<form role="form" class="form-horizontal" method="post"
					onsubmit="return false;">
					<div class="register-card-header"
						style="background-color: #9B99FF; display: flex;">
						<img
							src="${pageContext.request.contextPath}/resources/images/logo.png"
							alt="로고" class="logo"
							style="object-fit: contain; width: 120px; height: 75px;">
						<h1 class="text-center"
							style="color: #fff; font-size: 25px; margin: auto auto;">신고하기</h1>
					</div>
					<div class="register-card-body">

						<div class="mailbox-attachments clearfix col-md-12"
							style="text-align: center;"></div>

						<label for="email">이메일</label></br>
						<h4 id="email">${loginUser.email}</h4> </br>
						<label for="target">신고 대상</label> </br> 
						<input type="text" id="target"
							name="target" value="${member.id}"/> <br></br> 
							<label for="reason">신고
							사유</label> <select id="reason" name="reason" required>
							<option value="">선택하세요</option>
							<option value="스팸 또는 광고">스팸 또는 광고</option>
							<option value="저작권 침해">저작권 침해</option>
							<option value="기타">기타</option>
						</select>


						<div class="form-group col-sm-12">
							<div id="content">${board.content }</div>
						</div>
						
						<div class="form-group">
							<label for="content">상세 내용</label>

							<div class="input-group input-group-sm">
								<label for="inputFile"
									class="btn btn-warning btn-sm btn-flat input-group-addon"
									style="color: #fff; background-color: #9B99FF; border: none;">파일선택</label>
								<input id="inputFileName" class="form-control" type="text"
									name="tempPicture" disabled /> <input type="file"
									id="inputFile" name="picture" style="display: none;"
									onchange="picture_go();" />
							</div>


							<textarea class="textarea" name="content" id="content" rows="10"
								cols="40" placeholder="상세내용을 작성하세요."></textarea>
						</div>



						<div class="row">
							<div class="col-sm-9 text-right">
								<button type="submit" class="btn btn-primary"
									style="color: #fff; background-color: #9B99FF; border: none;"
									id="registBtn" onclick="regist_go();">제 출</button>
							</div>


							<div class="col-sm-3 text-center">
								<button type="button"
									style="color: #9B99FF; background-color: #ffffFF; border: 1px solid #9B99FF;"
									id="listBtn" onclick="CloseWindow();"
									class="btn btn-primary pull-right">취 소</button>
							</div>

							<hr />

						</div>
				</form>
			</div>

		</section>

		<!-- /.content -->
	</div>
	<!-- /.content-wrapper  여기까지 수정-->

</body>
