package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.repository.BoardDAO;
import com.board.repository.ReadCntDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	private ReadCntDAO readCntDAO;
	
	/* 게시판 목록 */
	@Override
	public List<BoardDTO> list(Criteria cri) {
		return boardDAO.list(cri);
	}

	/* 게시판 글쓰기 */
	@Override
	public int insert(BoardDTO board) {
		return boardDAO.insert(board);
	}

	/* 게시판 게시글 상세조회 */
	@Transactional
	@Override
	public BoardDTO read(int bno, String ip) {
		String readIP = readCntDAO.read(ip, bno);
		
		if(readIP == null) {
			readCntDAO.insert(ip, bno);
			boardDAO.increase(bno);
		}
		
		return boardDAO.read(bno);
	}
	
	/* 글번호로 게시글 정보 획득 */
	@Override
	public BoardDTO getUpdate(int bno) {
		return boardDAO.getUpdate(bno);
	}

	/* 게시판 게시글 수정 */
	@Override
	public int update(BoardDTO board) {
		return boardDAO.update(board);
	}

	/* 게시판 게시글 삭제 */
	@Override
	public int delete(BoardDTO board) {
		return boardDAO.delete(board);
	}

	/* 게시판 총 개수 가져오기 */
	@Override
	public int getTotal(Criteria cri) {
		return boardDAO.getTotal(cri);
	}

	/* 게시판 댓글 수 */
	@Override
	public void replyCnt(int bno) {
		boardDAO.replyCnt(bno);
	}

}
