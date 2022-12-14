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

<title>아이디 찾기</title>

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
		<h1>아이디 찾기</h1>
	</div>
	<!-- end header_wrap -->
	
	<div id="content_wrap" class="container pt-5">
			<div class="form-group">
				<label for="input_userName">이름</label> 
				<input id="input_userName" class="form-control" name="userName">
			</div>
			
			<div id="button_wrap" class="text-center mt-5">
				<button id="find_btn" class="w-25 btn btn-lg btn-success">아이디 찾기</button>
				<button id="cancel_btn" class="w-25 btn btn-lg btn-secondary">취소</button>
			</div>
			<!-- end button_wrap -->
		<!-- end form -->
	</div>
	<!-- end content_wrap -->
<script type="text/javascript">
$(document).ready(function() {
	
	/* 아이디 찾기 */
	$("#find_btn").on("click", function() {
		let userName = $("#input_userName").val();
		
		$.ajax({
			type: "post",
			url: "/user/findId",
			data: {
				userName: userName
			},
			dataType: "text",
			success: function(data) {
				if(data) {
					alert("찾으신 아이디는 " + data + " 입니다.");
					
					location.href = "/user/findPw";
				}else {
					alert("존재하지 않는 회원입니다.");
				}
			}
		});	//end ajax
	})
	
	/* 취소 버튼 */
	$("#cancel_btn").on("click", function() {
		history.back();
	});
});
</script>
</body>
</html>