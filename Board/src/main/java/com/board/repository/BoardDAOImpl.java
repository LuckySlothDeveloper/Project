package com.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.board.board";

	
	/* 게시판 목록 */
	@Override
	public List<BoardDTO> list(Criteria cri) {
		return sqlSession.selectList(NAMESPACE+".list", cri);
	}

	/* 게시판 글쓰기 */
	@Override
	public int insert(BoardDTO board) {
		return sqlSession.insert(NAMESPACE+".insert", board);
	}

	/* 게시판 게시글 상세조회 */
	@Override
	public BoardDTO read(int bno) {
		return sqlSession.selectOne(NAMESPACE+".read", bno);
	}

	/* 조회수 증가 */
	@Override
	public void increase(int bno) {
		sqlSession.update(NAMESPACE+".increase", bno);
	}

	/* 글번호로 게시글 정보 획득 */
	@Override
	public BoardDTO getUpdate(int bno) {
		return sqlSession.selectOne(NAMESPACE+".getUpdate", bno);
	}

	/* 게시판 게시글 수정 */
	@Override
	public int update(BoardDTO board) {
		return sqlSession.update(NAMESPACE+".update", board);
	}

	/* 게시판 게시글 삭제 */
	@Override
	public int delete(BoardDTO board) {
		return sqlSession.delete(NAMESPACE+".delete", board);		
	}

	/* 게시판 총 개수 가져오기 */
	@Override
	public int getTotal(Criteria cri) {
		return sqlSession.selectOne(NAMESPACE+".getTotal", cri);
	}

	/* 게시판 댓글 수 */
	@Override
	public void replyCnt(int bno) {
		sqlSession.update(NAMESPACE+".replyCnt", bno);
	}
	
}
