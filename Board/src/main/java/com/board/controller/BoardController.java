package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/* 게시판 목록 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		System.out.println("list 화면 진입");
		
		List<BoardDTO> list = boardService.list();
		
		model.addAttribute("list", list);
	}
	
	/* 게시판 글쓰기 화면 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		System.out.println("insert 화면 진입");
	}
	
	/* 게시판 글쓰기 */
	public String insert(BoardDTO board) {
		boardService.insert(board);
	}
}
