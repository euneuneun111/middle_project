<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.pagination .page-item.active .page-link {
	background-color: #9B99FF; /* 원하는 배경색 */
	color: #fff; /* 글자색 */
	border-color: #9B99FF; /* 테두리 색 */
}

.pagination .page-link {
	color: #9B99FF; /* 기본 글자색 */
}

.pagination .page-link:hover {
	background-color: #e9ecef; /* 호버 시 배경색 */
	color: #0056b3;
}
</style>


<!-- pagination -->
<nav aria-label="Navigation">
	<ul class="pagination justify-content-center m-0">
		<li class="page-item"><a class="page-link"
			href="javascript:search_list(1);"
			style="border-radius: 50px; margin: 5px"> <i
				class="fas fa-angle-double-left"></i>
		</a></li>
		<li class="page-item"><a class="page-link"
			href="javascript:search_list(${pageMaker.prev ? pageMaker.startPage-1 : pageMaker.page});"
			style="border-radius: 50px; margin: 5px"> <i
				class="fas fa-angle-left"></i>
		</a></li>

		<c:forEach var="pageNum" begin="${pageMaker.startPage }"
			end="${pageMaker.endPage }">
			<li class="page-item ${pageMaker.page == pageNum?'active':''}">
				<a class="page-link" href="javascript:search_list(${pageNum });"
				style="border-radius: 50px; margin: 5px"> ${pageNum } </a>
			</li>
		</c:forEach>

		<li class="page-item"><a class="page-link"
			href="javascript:search_list(${pageMaker.next ? pageMaker.endPage+1 : pageMaker.page});"
			style="border-radius: 50px; margin: 5px"> <i
				class="fas fa-angle-right"></i>
		</a></li>
		<li class="page-item"><a class="page-link"
			href="javascript:search_list(${pageMaker.realEndPage });"
			style="border-radius: 50px; margin: 5px"> <i
				class="fas fa-angle-double-right"></i>
		</a></li>
	</ul>
</nav>


<!-- 
<form id="jobForm" style="display:none;">	
	<input type='text' name="page" value="1" />
	<input type='text' name="perPageNum" value=""/>
	<input type='text' name="searchType" value="" />
	<input type='text' name="keyword" value="" />
</form>
<script>
function search_list(page){
	let perPageNum = document.querySelector('select[name="perPageNum"]').value;
	let searchType = document.querySelector('select[name="searchType"]').value;
	let keyword = document.querySelector('input[name="keyword"]').value;
	let startDate = document.querySelector('input[name="startDate"]').value;
	let endDate = document.querySelector('input[name="endDate"]').value;
	let tgMoney = document.querySelector('input[name="tgMoney"]').value;

	let form = document.querySelector("#jobForm");
	form.perPageNum.value = perPageNum;
	form.searchType.value = searchType;
	form.keyword.value = keyword;
	form.startDate.value = startDate;
	form.endDate.value = endDate;
	form.tgMoney.value = tgMoney;
	form.page.value = page;

	form.submit();
}

</script>

 -->



