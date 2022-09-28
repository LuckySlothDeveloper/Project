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

<title>게시판 상세조회</title>

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
</style>
</head>
<body>
	<div id="header_wrap" class="container-fluid">
		<h1>게시판 | 상세조회</h1>
	</div>
	<!-- end header_wrap -->
	
	<div id="content_wrap" class="container pt-5">
		<div class="form-group">
			<label for="input_title">제목</label> 
			<input id="input_title" class="form-control" name="title" value='<c:out value="${board.title}" />' readonly>
		</div>
		
		<div class="form-group">
			<label for="input_writer">작성자</label> 
			<input id="input_writer" class="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly>
		</div>
		
		<div class="form-group">
			<label for="input_regDay">작성일</label> 
			<input id="input_regDay" class="form-control" name="regDay" value='<fmt:formatDate value="${board.regDay}" pattern="yyyy년MM월dd일" />' readonly>
		</div>
		
		<div class="form-group">
			<label for="input_updateDay">수정일</label> 
			<input id="input_updateDay" class="form-control" name="updateDay" value='<fmt:formatDate value="${board.updateDay}" pattern="yyyy년MM월dd일" />' readonly>
		</div>
		
		<div class="form-group">
			<label for="input_content">내용</label> 
			<textarea id="input_content" class="form-control" rows="10" name="content" style="resize: none;" readonly><c:out value="${board.content}" /></textarea>
		</div>
		
		<div id="button_wrap" class="text-center mt-5">
			<button id="update_btn" class="w-25 btn btn-lg btn-success">수정</button>
			<button id="list_btn" class="w-25 btn btn-lg btn-secondary">목록으로</button>
		</div>
		<!-- end button_wrap -->
	</div>
	<!-- end content_wrap -->
	
<script type="text/javascript">
$(document).ready(function() {
	$("#update_btn").on("click", function(e) {
		e.preventDefault();
		
		location.href = "/board/update/${board.bno}";
	});
	
	$("#list_btn").on("click", function(e) {
		e.preventDefault();
		
		location.href = "/board/list";
	});
});
</script>
</body>
</html>