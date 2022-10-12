package com.board.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.Criteria;
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
	public List<ReplyDTO> list(Criteria cri, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cri", cri);
		map.put("bno", bno);
		
		return sqlSession.selectList(NAMESPACE+".list", map);
	}

	/* 댓글 자세히 보기 */
	@Override
	public ReplyDTO get(int rno) {
		return sqlSession.selectOne(NAMESPACE+".get", rno);
	}

	/* 댓글 개 수 */
	@Override
	public int getCountByBno(int bno) {
		return sqlSession.selectOne(NAMESPACE+".getCountByBno", bno);
	}
	
}
