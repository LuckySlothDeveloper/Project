package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;

public interface BoardService {

	/* 게시판 목록 */
	public List<BoardDTO> list();

	/* 게시판 글쓰기 */
	public void insert(BoardDTO board);

}
