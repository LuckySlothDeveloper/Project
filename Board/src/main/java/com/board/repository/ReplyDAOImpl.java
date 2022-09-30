package com.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.ReplyCriteria;
import com.board.domain.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.board.reply";

	
	/* 댓글 등록 */
	@Override
	public int insert(ReplyDTO replyDTO) {
		return sqlSession.insert(NAMESPACE+".insert", replyDTO);
	}

	/* 댓글 삭제 */
	@Override
	public int delete(int rno) {
		return sqlSession.delete(NAMESPACE+".delete", rno);
	}

	/* 댓글 수정 */
	@Override
	public int update(ReplyDTO replyDTO) {
		return sqlSession.update(NAMESPACE+".update", replyDTO);
	}

	/* 댓글 리스트 */
	@Override
	public List<ReplyDTO> list(ReplyCriteria criteria) {
		return sqlSession.selectList(NAMESPACE+".list", criteria);
	}
	
}
