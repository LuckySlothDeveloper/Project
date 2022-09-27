package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	
	/* 게시판 목록 */
	@Override
	public List<BoardDTO> list() {
		return boardDAO.list();
	}

	/* 게시판 글쓰기 */
	@Override
	public void insert(BoardDTO board) {
		boardDAO.insert(board);
	}
	
}
