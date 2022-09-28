package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/* 게시판 목록 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Criteria cri, Model model) {
		System.out.println("list 화면 진입");
		
		List<BoardDTO> list = boardService.list(cri);
		
		model.addAttribute("list", list);
		
		PageDTO pageMaker = new PageDTO(cri, 123);
		
		model.addAttribute("pageMaker", pageMaker);
		
	}
	
	/* 게시판 글쓰기 화면 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		System.out.println("insert 화면 진입");
	}
	
	/* 게시판 글쓰기 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardDTO board, RedirectAttributes rttr) {
		System.out.println("insert.........");
		
		int result = boardService.insert(board);
		
		if(result == 1) {
			rttr.addFlashAttribute("success", "insert_success");
		}
		
		return "redirect:/board/list";
	}
	
	/* 게시판 게시글 상세조회(IP별 조회수 증가 구현) */
	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("bno") int bno, Model model, HttpServletRequest request) {
		System.out.println("read 화면 진입");
		
		String ip = request.getRemoteAddr();
		
		System.out.println("IP주소 : " + ip);
		
		BoardDTO board = boardService.read(bno, ip);
		
		model.addAttribute("board", board);
		
		return "/board/read";
	}
	
	/* 게시판 게시글 수정,삭제 화면 */
	@RequestMapping(value = "/update/{bno}", method = RequestMethod.GET)
	public String update(@PathVariable("bno") int bno, Model model) {
		System.out.println("update and delete 화면 진입");
		
		BoardDTO board = boardService.getUpdate(bno);
		
		model.addAttribute("board", board);
		
		return "/board/update";
	}
	
	/* 게시판 게시글 수정 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardDTO board, RedirectAttributes rttr) {
		System.out.println("update.........");
		
		int result = boardService.update(board);
		
		if(result == 1) {
			rttr.addFlashAttribute("success", "update_success");
		}
		
		return "redirect:/board/list";
	}
	
	/* 게시판 게시글 삭제 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(BoardDTO board, RedirectAttributes rttr) {
		System.out.println("delete.........");
		
		int result = boardService.delete(board);
		
		if(result == 1) {
			rttr.addFlashAttribute("success", "delete_success");
		}
		
		return "redirect:/board/list";
	}
}
