package com.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.board.board";

	
	/* 게시판 목록 */
	@Override
	public List<BoardDTO> list() {
		return sqlSession.selectList(NAMESPACE+".list");
	}

	/* 게시판 글쓰기 */
	@Override
	public void insert(BoardDTO board) {
		sqlSession.insert(NAMESPACE+".insert", board);
	}
	
}
