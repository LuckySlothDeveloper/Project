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

#reply_list {
	list-style: none;
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
			<button id="reply_btn" class="w-25 btn btn-lg btn-info" data-toggle="modal" data-target="#replyModal">댓글등록</button>
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
			<div id="replyModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="replyModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="replyModalLabel">댓글</h4>
							<button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						</div>
						<!-- end modal-header -->
						
						<div class="modal-body">
							<div class="form-group">
								<input type="hidden" name="replyer">
							</div>
							
							<div class="form-group">
								<label>댓글 내용</label> <br>
								<textarea class="form-control" rows="10" name="replyText" style="resize: none;"></textarea>
							</div>
						</div>
						<!-- end modal-body -->
						
						<div class="modal-footer">
							<button id="modal_insert_btn" class="btn btn-success">등록</button>
							<button id="modal_update_btn" class="btn btn-warning">수정</button>
							<button id="modal_delete_btn" class="btn btn-danger">삭제</button>
							<button id="modal_close_btn" class="btn btn-default" data-dismiss="modal">닫기</button>
						</div>
						<!-- end modal-footer -->
					</div>
					<!-- end modal-content -->
				</div> 
				<!-- end modal-dialog -->
			</div>
			<!-- end replyModal  -->
		</div>
		<!-- end reply_wrap -->
		
		<div id="reply_list_wrap">
			<ul id="reply_list" class="clearfix">
			
			</ul>
		</div>
		<!-- end reply_list_wrap -->
		
		<div id="reply_paging_wrap" class="clearfix">
		
		</div>
		<!-- end reply_paging_wrap -->
		
	</div>
	<!-- end content_wrap -->
	
<script type="text/javascript" src="/resources/js/reply.js"></script>	

<script type="text/javascript">
$(document).ready(function() {
	let bno = '<c:out value="${board.bno}" />';
	let replyUL = $("#reply_list");
	
	let modal = $("#replyModal");
	let modalReplyer = modal.find("input[name='replyer']");
	
	let pageNum = 1;
	let replyPage = $("#reply_paging_wrap");
	
	if(!modalReplyer.val()) {
		modalReplyer.val("익명");
	}
	
	let modalReplyText = modal.find("textarea[name='replyText']");
	
	let modalUpdateBtn = $("#modal_update_btn");
	let modalDeleteBtn = $("#modal_delete_btn");
	let modalInsertBtn = $("#modal_insert_btn");
	
	showList(1);
	
	/* 댓글 페이징 */
	function showReplyPage(replyCnt) {
		let endNum = Math.ceil(pageNum / 10.0) * 10;
		let startNum = endNum - 9;
		
		let prev = startNum != 1;
		let next = false;
		
		if(endNum * 10 >= replyCnt) {
			endNum = Math.ceil(replyCnt / 10.0);
		}
		
		if(endNum * 10 < replyCnt) {
			next = true;
		}
		
		let pStr = "<ul class='pagination float-right'>";
		
		if(prev) {	//이전 버튼의 값이 true이면
			pStr += "<li class='page-item'>";
			pStr += "<a class='page-link' href='"+(startNum - 1)+"'>이전</a>";
			pStr += "</li>";
		}
		
		for(let i = startNum; i<= endNum; i++) {
			let active = pageNum == i? "active" : "";
			
			pStr += "<li class='page-item "+active+" '>";
			pStr += "<a class='page-link' href='"+ i +"'>"+ i +"</a>";
			pStr += "</li>";
		}
		
		if(next) {	//다음 버튼의 값이 true이면
			pStr += "<li class='page-item'>";
			pStr += "<a class='page-link' href='"+(endNum + 1)+"'>다음</a>";
			pStr += "</li>";
		}
		
		pStr += "</ul>";
		pStr += "</div>";
		
		$("#reply_paging_wrap").html(pStr);
		
		$("#reply_paging_wrap").on("click", "li a", function(e) {
			e.preventDefault();
			
			let targetPageNum = $(this).attr("href");
			
			pageNum = targetPageNum;
			
			showList(pageNum);
		});
	}
	
	/* 댓글 리스트 */
	function showList(page) {
		console.log("show List : " + page);
		
	 	replyService.list({bno: bno, page: page || 1}, function(replyCnt, list) {
	 		
	 		console.log("replyCnt : " + replyCnt);
	 		console.log("list : " + list);
	 		console.log(list);
	 		
	 		if(page == -1) {
	 			pageNum = Math.ceil(replyCnt/10.0);
	 			
	 			showList(pageNum);
	 			
	 			return;
	 		}
	 		
			let str = "";
			
			//만약 등록된 댓글이 없다면
			if(list == null || list.length == 0) {
				return;
			}
			
			for(let i = 0, len = list.length || 0; i < len; i++) {
				str += "<li class='clearfix border-bottom' data-rno='"+list[i].rno+"'>";
				str += "<div>";
				str += "<div class='header'>"
				str += "<strong>"+list[i].replyer+"</strong>";
				str += "<small class='float-right'>"+replyService.displayTime(list[i].updateDay)+"</small>";
				str += "</div>";
				str += "<p>"+list[i].replyText+"</p>";
				str += "</div>";
				str += "</li>";
			}
			
			replyUL.html(str);
			
			showReplyPage(replyCnt);
			
		})	//end list
	}	//end showList
	
	/* 닫기 버튼 클릭 시 input값 지우기 */
	$("#modal_close_btn").on("click", function() {
		modalReplyer.val("");
		modalReplyText.val("");
	});

	/* 댓글 등록 modal창 표시 */
	$("#reply_btn").on("click", function() {
		modal.find("button[id != modal_close_btn]").hide();
		
		$(".close").show();
		modalInsertBtn.show();
	});
	
	/* 댓글 등록 */
	modalInsertBtn.on("click", function() {
		
		let reply = {
				replyer: modalReplyer.val(),
				replyText: modalReplyText.val(),
				bno: bno
		};
		
		replyService.insert(reply, function(result) {
			
			if(result == "insert-success") {
				alert("댓글이 등록 되었습니다.");	
			}
			
			modalReplyer.val("");
			modalReplyText.val("");
			modal.modal("hide");
			
			showList(-1);
		});
			
	});
	
	/* 댓글 자세히 보기 */
	$("#reply_list").on("click", "li", function() {
		let rno = $(this).data("rno");
		
		replyService.get(rno, function(reply) {
			modalReplyer.val(reply.replyer);
			modalReplyText.val(reply.replyText);
			modal.data("rno", reply.rno);

			$("#replyModal").modal("show");
			
			modal.find("button[id != modal_close_btn]").hide();
			
			modalUpdateBtn.show();
			modalDeleteBtn.show();
		})
	});
	
	/* 댓글 수정 */
	modalUpdateBtn.on("click", function() {
		let reply = {rno: modal.data("rno"), replyText: modalReplyText.val()};
		
		replyService.update(reply, function(result) {
			if(result === "update-success"){
				alert("댓글 수정 완료!!");	
			}
			
			modal.modal("hide");
			
			showList(1);
		});
	});
	
	/* 댓글 삭제 */
	modalDeleteBtn.on("click", function() {
		let rno = modal.data("rno");
		
		replyService.remove(rno, function(result) {
			if(result === "delete-success") {
				alert("삭제 완료!!!");
			}
			
			modal.modal("hide");
			
			showList(1);
		});
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