/**
 * 
 */
 
 console.log("댓글 모듈.........");
 
 let replyService = (function(){
 	function displayTime(timeValue) {
 		let today = new Date();
 		
 		let gap = today.getTime() - timeValue;
 		
 		let dateObj = new Date(timeValue);
 		
 		let str = "";
 		
 		if(gap < (1000 * 60 * 60 * 24)) {
 			let hh = dateObj.getHours();
 			let mi = dateObj.getMinutes();
 			let ss = dateObj.getSeconds();
 			
 			return [ (hh > 9 ? '' : '0') + hh, "시", (mi > 9 ? '' : '0') + mi, "분", (ss > 9 ? '' : '0') + ss, '초' ].join('');
 		} else {
 			let yy = dateObj.getFullYear();
 			let mm = dateObj.getMonth() + 1;
 			let dd = dateObj.getDate();
 			
 			return [ yy, '년', (mm > 9 ? '' : '0') + mm, '월', (dd > 9 ? '' : '0') + dd, '일' ].join('');
 		}
 	};
 	
 	function insert(reply, callback, error) {
 		console.log("댓글 등록 처리");
 		
 		$.ajax({
 			type: "post",
 			url: "/replies/insert",
 			data: JSON.stringify(reply),
 			contentType: "application/json; charset=utf-8",
 			success: function(result, status, xhr) {
 				if(callback) {
 					callback(result);
 				}
 			},
 			error: function(xhr, status, er){
 				if(error) {
 					error(er);
 				}
 			}
 		
 		});
 	};
 	
 	function list(param, callback, error) {
 		let bno = param.bno;
 		let page = param.page || 1;
 		
 		$.getJSON("/replies/list/" + bno + "/" + page + ".json", function(data) {
 			if(callback) {
 				callback(data.replyCnt, data.list);
 				
 			}
 		}).fail(function(xhr, status, err) {
 			if(error) {
 				error();
 			}
 		});
 	}
 	
 	function get(rno, callback, error) {
 		$.get("/replies/read/" + rno + ".json", function(result) {
 			if(callback) {
 				callback(result);
 			}
 		}).fail(function(xhr, status, err){
 			if(error) {
 				error();
 			}
 		});
 	}
 	
 	function update(reply, callback, error) {
 		$.ajax({
 			type: "put",
 			url: "/replies/update/" + reply.rno,
 			data: JSON.stringify(reply),
 			contentType: "application/json; charset=utf-8",
 			success: function(result, status, xhr){
 				if(callback){
 					callback(result);
 				}
 			},
 			error: function(xhr, status, er) {
 				if(error){
 					error(er);
 				}
 			}
 		});
 	}
 	
 	function remove(rno, callback, error) {
 		$.ajax({
 			type: "delete",
 			url: "/replies/delete/" + rno,
 			success: function(result, status, xhr){
 				if(callback) {
 					callback(result);
 				}
 			},
 			error: function(xhr, status, er){
 				if(error) {
 					error(er);
 				}
 			}
 		});
 	}
 	
 	return {
				insert: insert,
				list: list,
				get: get,
				update: update,
				remove: remove,
				displayTime: displayTime
			};
 })();