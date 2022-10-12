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

<title>내정보 수정</title>

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
		<h1>내정보 | 수정</h1>
	</div>
	<!-- end header_wrap -->
	
	<div id="content_wrap" class="container pt-5">
		<form id="updateForm">
			<div class="form-group">
				<label for="input_id">아이디</label> 
				<input id="input_id" class="form-control" name="userId" value='<c:out value="${user.userId}" />' readonly>
			</div>
			
			<div class="form-group">
				<label for="input_name">이름</label> 
				<input id="input_name" class="form-control" name="userName" value='<c:out value="${user.userName}"/>'>
			</div>
			
			<div class="form-group">
				<label for="input_pw">비밀번호</label> 
				<input type="password" id="input_pw" class="form-control" name="password" value='<c:out value="${user.password}"/>'>
			</div>
			
		</form>
		
		<div class="form-group">
			<label for="input_day">가입일</label> 
			<input id="input_day" class="form-control" name="regDay" value='<fmt:formatDate value="${user.regDay}" pattern="yyyy년MM월dd일" />' readonly>
		</div>
		
		<div id="button_wrap" class="text-center mt-5 mb-4">
			<button id="update_btn" class="w-25 btn btn-lg btn-success">수정하기</button>
			<button id="cancel_btn" class="w-25 btn btn-lg btn-danger">취소</button>
		</div>
		<!-- end button_wrap -->
	</div>
	<!-- end content_wrap -->
	
<script type="text/javascript">
$(document).ready(function() {
	/* 내정보 수정 버튼 */
	$("#update_btn").on("click", function() {
		let form = $("#updateForm");
		
		form.attr("action", "/user/update")
			.attr("method", "post")
			.submit();
			
	});
	
	/* 취소 버튼 */
	$("#cancel_btn").on("click", function() {
		history.back();
	});
});
</script>
</body>
</html>