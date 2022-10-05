package com.board.repository;

import java.util.List;

import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;

public interface ReplyDAO {

	/* 댓글 등록 */
	public int insert(ReplyDTO replyDTO);
	
	/* 댓글 삭제 */
	public int delete(int rno);
	
	/* 댓글 수정 */
	public int update(ReplyDTO replyDTO);
	
	/* 댓글 리스트 */
	public List<ReplyDTO> list(Criteria cri, int bno);

	/* 댓글 개 수 */
	public int getCountByBno(int bno);
	
	/* 댓글 자세히 보기 */
	public ReplyDTO get(int rno);

	
}

