<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Jquery CDN -->
<script src="https://code.jquery.com/jquery-3.6.1.js"
	integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
	
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"
	integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+"
	crossorigin="anonymous"></script>

<title>게시판 목록</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
	
<style type="text/css">
#header_wrap {
	background-color: #F0FFFF;
	height: 100px;
}

#header_wrap h1 {
	line-height: 100px; 
	vertical-align: middle;
}

#pageMaker_wrap{
	margin: 0 auto;
}
</style>
</head>
<body>
	<div id="header_wrap" class="container-fluid">
		<h1>게시판 | 목록</h1>
	</div>
	<!-- end header_wrap -->
	
	<div id="content_wrap" class="container pt-5">
		<div id="count_wrap" class="clearfix">
			<p class="float-right">총 (<span class="text-bold" style="color: red;"><c:out value="${total}"/></span>)개의 글이 있습니다.</p>
		</div>
		<table class="table">
			<thead class="text-center" style="background-color: #F0FFFF;">
				<tr>
					<th scope="col">글번호</th>
					<th scope="col">작성자</th>
					<th scope="col">글제목</th>
					<th scope="col">조회수</th>
					<th scope="col">작성일</th>
					<th scope="col">수정일</th>
				</tr>
			</thead>
			
			<tbody class="text-center">
				<c:forEach items="${list}" var="board">
					<tr>
						<td><c:out value="${board.bno}"/></td>
						<td><c:out value="${board.writer}"/></td>
						<td><a class="move" href="${board.bno}"><c:out value="${board.title}"/></a></td>
						<td><c:out value="${board.readCnt}"/></td>
						<td><fmt:formatDate value="${board.regDay}" pattern="yyyy년MM월dd일"/></td>
						<td><fmt:formatDate value="${board.updateDay}" pattern="yyyy년MM월dd일"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div id="button_wrap" class="clearfix">
			<button id="insert_btn" class="btn btn-lg btn-primary float-right">글쓰기</button>
		</div>
		<!-- end button_wrap -->
		
		<div id="pageMaker_wrap" class="">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center pagination-lg">
					<c:if test="${pageMaker.prev}">
						<li class="page-item"><a class="page-link" href="${pageMaker.startPage - 1}">Previous</a></li>
					</c:if>
					
					<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
						<li class="page-item ${pageMaker.cri.pageNum == num ? "active" : ""}">
							<a class="page-link" href="${num}">${num}</a>
						</li>					
					</c:forEach>

					<c:if test="${pageMaker.next}">
						<li class="page-item"><a class="page-link" href="${pageMaker.endPage + 1}">Next</a></li>
					</c:if>
				</ul>
			</nav>
			<!-- end page -->
		</div>
		<!-- end pageMaker_wrap -->
		
		<form id="action_form" action="/board/list" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
			<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
		</form>
	</div>
	<!-- end content_wrap -->
	
<script type="text/javascript">
$(document).ready(function() {
	let result = "";
	
	result = "${success}";
	
	let actionForm = $("#action_form");
	
	/* 안내문구 출력 */
	if(result) {
		if(result == "insert_success") {
			alert("게시글이 등록 되었습니다.");
		}else if(result == "update_success") {
			alert("게시글이 수정 되었습니다.");
		}else if(result == "delete_success") {
			alert("게시글이 삭제 되었습니다.");
		}
	}
	
	/* 글쓰기 버튼 이벤트 */
	$("#insert_btn").on("click", function(e) {
		e.preventDefault();
		
		location.href = "/board/insert";
	});
	
	/* 페이징 버튼 이벤트 */
	$(".page-item a").on("click", function(e) {
		e.preventDefault();
		
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});
	
	$(".move").on("click", function(e) {
		e.preventDefault();
		
		actionForm.attr("action", "/board/read/"+$(this).attr('href'));
		actionForm.submit();
	});

});
</script>
</body>
</html>