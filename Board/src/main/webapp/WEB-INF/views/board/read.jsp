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
		
		<div id="button_wrap" class="text-center mt-5 mb-4">
			<button id="update_btn" class="w-25 btn btn-lg btn-success">수정</button>
			<button id="list_btn" class="w-25 btn btn-lg btn-secondary">목록으로</button>
		</div>
		<!-- end button_wrap -->
		
		<form id="moveForm" method="get">
			<input type="hidden" name="pageNum" value="${cri.pageNum}">
			<input type="hidden" name="amount" value="${cri.amount}">
			<input type="hidden" name="type" value="${cri.type}">
			<input type="hidden" name="keyword" value="${cri.keyword}">
		</form>
		
		<hr>
		
		<div id="reply_wrap">
			<div class="form-group">
				<label for="input_replyText">댓글 작성</label> 
				<textarea id="input_replyText" class="form-control" rows="5" name="replyText" style="resize: none;"></textarea>
			</div>
			
			<div class="form-group">
				<input type="hidden" id="input_replyer" class="form-control" name="replyer"> 
			</div>
			
			<div class="form-group text-center mb-5">
				<button id="reply_insert_btn" class="btn btn-success">댓글 등록</button>
			</div>
			
			<ul id="reply_ul" style="list-style: none;">
	
			</ul>
			
		</div>
		<!-- end reply_wrap -->
		
	</div>
	<!-- end content_wrap -->
	
<script type="text/javascript" src="/resources/js/reply.js"></script>	

<script type="text/javascript">
$(document).ready(function() {
	console.log("============");
	console.log("JS TEST");
	
	let bno = '<c:out value="${board.bno}"/>';
	
	let replyUL = $("#reply_ul");
	
	showList(1);
	
	/* 댓글 이벤트 처리 */
	function showList(page) {
		/* 댓글 리스트 */
		replyService.list({bno:bno, page:page || 1}, function(list) {
			let str = "";
			
			if(list == null || list.length == 0) {
				replyUL.html("");
				
				return;
			}
			
			for(let i = 0, len = list.length || 0; i < len; i++) {
				str += "<li class='border-bottom' data-rno='"+list[i].rno+"'>";
				
				str += "<div class='reply_header clearfix mb-4'>";
				str += "<strong class='primary-font float-left'>"+list[i].replyer+"</strong>";
				str += "<small class='float-right'>"+replyService.displayTime(list[i].updateDay)+"</small>";
				str += "</div>";
				
				str += "<div class='reply_body'>";
				str += "<p>"+list[i].replyText+"</p>";
				str += "</div>";
				
				str += "</li>";
			}
			
			replyUL.html(str);
		});	//end function
		
		/* 댓글 등록 */
		$("#reply_insert_btn").on("click", function() {
			
			let replyText = $("#input_replyText").val();
			let replyer = $("#input_replyer").val();
			
			if(!replyer) {
				replyer = "익명";
			}
			
			let reply = {
				replyText: replyText,
				replyer: replyer,
				bno: bno
			};
			
			replyService.insert(reply, function(result) {
				if(result === "insert-success") {
					alert("댓글이 등록되었습니다.");
				}
			});
			
			showList(1);
			
		});
		
		
		
	};	//end showList

	/* 댓글 삭제 */
	replyService.del(3, function(count) {
		console.log("count : " + count);
		
		if(count === "delete-success") {
			alert("삭제");
		}
	},function(err) {
		alert("ERROR......");
	});
	
	/* 댓글 수정 */
	replyService.update({
		rno: 2,
		bno: bno,
		replyText: "댓글 수정 테스트......",
		
	}, function(result) {
		alert("수정완료...");
	});
	
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	$("#update_btn").on("click", function(e) {
		e.preventDefault();
		
		$("#moveForm").attr("action", "/board/update/${board.bno}");
		$("#moveForm").submit();
	});
	
	$("#list_btn").on("click", function(e) {
		e.preventDefault();
		
		$("#moveForm").attr("action", "/board/list");
		$("#moveForm").submit();
	});
});
</script>
</body>
</html>