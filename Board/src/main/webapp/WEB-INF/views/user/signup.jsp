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

<title>회원가입</title>

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
		<h1>회원가입</h1>
	</div>
	<!-- end header_wrap -->
	
	<div id="content_wrap" class="container pt-5">
		<form id="signupForm" action="/user/signup" method="post">
			<div class="form-group">
				<label for="input_userId">아이디</label> 
				<input id="input_userId" class="form-control" name="userId">
				<small id="isId">시작은 영문으로만, 영문, 숫자로만 이루어진 5 ~ 12자 이하</small>
			</div>
			
			<div class="form-group">
				<label for="input_password">비밀번호</label> 
				<input type="password" id="input_password" class="form-control" name="password">
			</div>
			
			<div class="form-group">
				<label for="input_pwck">비밀번호 확인</label> 
				<input type="password" id="input_pwck" class="form-control">
				<small id="pwckCheck"></small>
			</div>
			
			<div class="form-group">
				<label for="input_userName">이름</label> 
				<input id="input_userName" class="form-control" name="userName">
			</div>
			
			<div id="button_wrap" class="text-center mt-5">
				<button id="submit_btn" type="submit" class="w-25 btn btn-lg btn-success">회원가입</button>
				<button id="cancel_btn" class="w-25 btn btn-lg btn-secondary">취소</button>
			</div>
			<!-- end button_wrap -->
		</form>
		<!-- end form -->
	</div>
	<!-- end content_wrap -->
	
<script type="text/javascript">
$(document).ready(function() {
	let idCheck = false;
	let pwCheck = false;
	
	/* 아이디 인풋 값이 변경될 때 */
	$("#input_userId").on("input keyup paste", function() {
		let id = $("#input_userId").val();
		
		/* 아이디 유효성 검사 */
		if(id.length >= 6) {
			$.ajax({
				type: "get",
				url: "/user/isId",
				data: {
					id: id
				},
				dataType: "text",
				success: function(result) {
					if(result) {
						$("#isId").html("사용 가능한 아이디 입니다.");
						$("#isId").css("color", "blue");
						idCheck = true;
					}else {
						$("#isId").html("사용 불가능한 아이디 입니다.");
						$("#isId").css("color", "red");
						idCheck = false;
					}
				}
			});	//end ajax	
		}else {
			$("#isId").html("");
		}
		
	});
	
	/* 비밀번호 확인 인풋 값이 변경 될 때 */
	$("#input_pwck").on("input keyup paste", function() {
		let pw = $("#input_password").val();
		let pwck = $("#input_pwck").val();
		
		
		
		//비밀번호 인풋과 비밀번호 확인 인풋 길이가 다를 때
		if(pw.length != pwck.length) {
			$("#pwckCheck").html("비밀번호와 비밀번호 확인의 길이가 다릅니다.");
			$("#pwckCheck").css("color", "red");
			pwCheck = false;
			
			/* 입력값 초기화 시 결과안내 문구 초기화 */
			if(pwck.length == 0) {
				$("#pwckCheck").html("");
			}
		}else if(pw.length == pwck.length) {	//비밀번호 인풋과 비밀번호 확인 인풋 길이가 같을 때
			if(pw == pwck) { //비밀번호와 비밀번호 확인의 값이 같을 때
				$("#pwckCheck").html("비밀번호와 비밀번호 확인의 값이 같습니다.");
				$("#pwckCheck").css("color", "blue");
				pwCheck = true;
			}else {
				//비밀번호와 비밀번호 확인의 값이 다를 때
				$("#pwckCheck").html("비밀번호와 비밀번호 확인의 값이 다릅니다.");
				$("#pwckCheck").css("color", "red");
				pwCheck = false;
			}
		}
	});
	
	/* 등록 버튼 클릭 */
	$("#submit_btn").on("click", function(e) {
		e.preventDefault();
		
		let id = $("#input_userId").val();
		let pw = $("#input_password").val();
		let pwck = $("#input_pwck").val();
		let name = $("#input_userName").val();
		
		/* 공백 검사 */
		if(!id) {
			alert("아이디를 입력해 주세요.");
			
			$("#input_userId").focus();
		}else {
			if(!pw) {
				alert("비밀번호를 입력해 주세요.");
				
				$("#input_password").focus();
			}else {
				if(!pwck) {
					alert("비밀번호 확인을 입력해 주세요.");
					
					$("#input_password").focus();
				}else {
					if(!name) {
						alert("이름을 입력해 주세요.");
						
						$("#input_userName").focus();
					}else {
						if(idCheck && pwCheck) {
							$("#signupForm").submit();
						}
					}
				}
			}
		}
	
	});
	
	$("#cancel_btn").on("click", function(e) {
		e.preventDefault();
		
		location.href = "/";
	});
});
</script>
</body>
</html>