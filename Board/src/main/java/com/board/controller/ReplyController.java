package com.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;
import com.board.domain.ReplyPageDTO;
import com.board.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	/* 댓글 등록 */
	@PostMapping(value = "/insert", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> insert(@RequestBody ReplyDTO replyDTO) {
		System.out.println("댓글 등록 - replyDto : " + replyDTO);
		
		int result = replyService.insert(replyDTO);
		
		return result == 1 ? new ResponseEntity<String>("insert-success", HttpStatus.OK) : 
								new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
	
	/* 댓글 리스트 */
	@GetMapping(value = "/list/{bno}/{page}", produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE
	})
	public ResponseEntity<ReplyPageDTO> list(@PathVariable("page") int page, @PathVariable("bno") int bno) {
		System.out.println("댓글 리스트.........");
		
		Criteria cri = new Criteria(page, 5);
		
		return new ResponseEntity<ReplyPageDTO>(replyService.list(cri, bno), HttpStatus.OK);
	}
	
	/* 댓글 자세히 보기 */
	@GetMapping(value = "/read/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyDTO> get(@PathVariable("rno") int rno) {
		System.out.println("댓글 자세히 보기 - rno : " + rno);
		
		return new ResponseEntity<ReplyDTO>(replyService.get(rno), HttpStatus.OK);
	}
	
	/* 댓글 삭제 */
	@DeleteMapping(value = "/delete/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@PathVariable("rno") int rno) {
		System.out.println("댓글 삭제 - rno : " + rno);
		
		return replyService.delete(rno) == 1 ?
												new ResponseEntity<String>("delete-success", HttpStatus.OK) 
											:	new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/* 댓글 수정 */
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/update/{rno}", consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@RequestBody ReplyDTO replyDTO, @PathVariable("rno") int rno) {
		System.out.println("댓글 수정 - rno : " + rno);
		
		replyDTO.setRno(rno);
		
		return replyService.update(replyDTO) == 1 ?
													new ResponseEntity<String>("update-success", HttpStatus.OK)
												:	new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
