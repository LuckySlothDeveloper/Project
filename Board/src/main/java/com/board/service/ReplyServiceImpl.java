package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;
import com.board.domain.ReplyPageDTO;
import com.board.repository.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;

	
	/* 댓글 등록 */
	@Override
	public int insert(ReplyDTO replyDTO) {
		
		return replyDAO.insert(replyDTO);
	}

	/* 댓글 수정 */
	@Override
	public int update(ReplyDTO replyDTO) {

		return replyDAO.update(replyDTO);
	}

	/* 댓글 삭제 */
	@Override
	public int delete(int rno) {
		
		return replyDAO.delete(rno);
	}

	/* 댓글 리스트 */
	@Override
	public ReplyPageDTO list(Criteria cri, int bno) {
		
		return new ReplyPageDTO(replyDAO.getCountByBno(bno), replyDAO.list(cri, bno));
	}

	/* 댓글 자세히 보기 */
	@Override
	public ReplyDTO get(int rno) {
	
		return replyDAO.get(rno);
	}
	
	
	
}
