<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
<title>Welcome!! Board Community</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">

<style type="text/css">
#form_wrap {
	margin: 0 auto;
}
</style>
</head>
<body style="background-color: #778899">
	<div class="container text-center mt-5">
		<div class="pt-5">
			<div id="content_wrap" class="mt-5">
				<h1 class="pt-5 text-white">게시판</h1>
				
				<h2 class="mt-5 text-white">방문해주셔서 감사합니다.</h2>
				
				<div id="form_wrap" class="w-50 text-center mt-5">
					<form action="/login" method="post">
						<div class="form-group">
							<label for="input_id" class="text-dark">아이디</label> 
							<input id="input_id" class="form-control text-center" name="userId">
						</div>
						
						<div class="form-group">
							<label for="input_pw" class="text-dark">비밀번호</label> 
							<input id="input_pw" type="password" class="form-control text-center" name="password">
						</div>
					</form>
				</div>
				
				<div id="button_wrap">
					<button id="admission_btn" class="btn btn-lg btn-success w-50 mb-2">게시판 입장</button>
					<button id="signup_btn" class="btn btn-lg btn-info w-50 mb-2">회원 가입</button> <br>
					<button id="findId_btn" class="btn btn-lg btn-link w-25 mb-2 text-white">아이디 찾기</button>
					<button id="findPw_btn" class="btn btn-lg btn-link w-25 mb-2 text-white">비밀번호 찾기</button>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#admission_btn").on("click", function(e) {
		e.preventDefault();
		
		location.href = "/board/list";
	});
});
</script>
</body>
</html>