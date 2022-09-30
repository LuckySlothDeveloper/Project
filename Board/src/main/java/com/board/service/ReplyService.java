package com.board.service;

import java.util.List;

import com.board.domain.ReplyCriteria;
import com.board.domain.ReplyDTO;

public interface ReplyService {

	/* 댓글 등록 */
	public int insert(ReplyDTO replyDTO);
	
	/* 댓글 수정 */
	public int update(ReplyDTO replyDTO);
	
	/* 댓글 삭제 */
	public int delete(int rno);
	
	/* 댓글 리스트 */
	public List<ReplyDTO> list(ReplyCriteria criteria);
	
}
