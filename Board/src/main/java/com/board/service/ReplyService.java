package com.board.service;

import java.util.List;

import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;
import com.board.domain.ReplyPageDTO;

public interface ReplyService {

	/* 댓글 등록 */
	public int insert(ReplyDTO replyDTO);
	
	/* 댓글 수정 */
	public int update(ReplyDTO replyDTO);
	
	/* 댓글 삭제 */
	public int delete(int rno);
	
	/* 댓글 리스트 */
	public ReplyPageDTO list(Criteria cri, int bno);

	/* 댓글 자세히 보기 */
	public ReplyDTO get(int rno);
	
}
