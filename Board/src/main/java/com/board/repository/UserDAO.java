package com.board.repository;

import java.util.List;

import com.board.domain.BoardDTO;

public interface UserDAO {

	/* 게시판 목록 */
	public List<BoardDTO> list();
	
	/* 게시판 글쓰기 */
	public int insert(BoardDTO board);
}
