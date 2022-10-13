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

<title>MyInfo</title>

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
		<h1>내정보 | 상세조회</h1>
	</div>
	<!-- end header_wrap -->
	
	<div id="content_wrap" class="container pt-5">
		<div class="form-group">
			<label for="input_id">아이디</label> 
			<input id="input_id" class="form-control" name="userId" value='<c:out value="${user.userId}" />' readonly>
		</div>
		
		<div class="form-group">
			<label for="input_name">이름</label> 
			<input id="input_name" class="form-control" name="userName" value='<c:out value="${user.userName}"/>' readonly>
		</div>
		
		<div class="form-group">
			<label for="input_pw">비밀번호</label> 
			<input type="password" id="input_pw" class="form-control" name="password" value='<c:out value="${user.password}"/>' readonly>
		</div>
		
		<div class="form-group">
			<label for="input_day">가입일</label> 
			<input id="input_day" class="form-control" name="regDay" value='<fmt:formatDate value="${user.regDay}" pattern="yyyy년MM월dd일" />' readonly>
		</div>
		
		<div id="button_wrap" class="text-center mt-5 mb-4">
			<button id="update_btn" class="w-25 btn btn-lg btn-warning">내정보수정</button>
			<button id="list_btn" class="w-25 btn btn-lg btn-success">목록으로</button>
			<button id="delete_btn" class="w-25 btn btn-lg btn-danger">회원탈퇴</button>
		</div>
		<!-- end button_wrap -->
	</div>
	<!-- end content_wrap -->
	
<script type="text/javascript">
$(document).ready(function() {
	let update_success = "${update_success}";
	
	if(update_success === "update_success") {
		alert("회원정보를 수정하였습니다.");
	}
	
	/* 목록 버튼 */
	$("#list_btn").on("click", function() {
		location.href = "/board/list";
	});
	
	/* 내정보 수정 버튼 */
	$("#update_btn").on("click", function() {
		location.href = "/user/update";
	});
	
	/* 회원탈퇴 버튼 */
	$("#delete_btn").on("click", function() {
		let userId = $("#input_id").val();
		
		let question = confirm("회원탈퇴를 진행 하시겠습니까?");
		
		if(question) {
			$.ajax({
				type: "post",
				url: "/user/delete",
				data: {
					userId: userId
				},
				dataType: "text",
				success: function(result) {
					if(result === "success") {
						alert("회원탈퇴 하였습니다.");
						
						location.href = "/";
					}
				}
			});	//end ajax
		}
	})
});
</script>
</body>
</html>