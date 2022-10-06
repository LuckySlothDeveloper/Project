package com.board.repository;

import java.util.List;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;

public interface BoardDAO {

	/* 게시판 목록 */
	public List<BoardDTO> list(Criteria cri);

	/* 게시판 글쓰기 */
	public int insert(BoardDTO board);

	/* 게시판 게시글 상세조회 */
	public BoardDTO read(int bno);

	/* 조회수 증가 */
	public void increase(int bno);

	/* 글번호로 게시글 정보 획득 */
	public BoardDTO getUpdate(int bno);

	/* 게시판 게시글 수정 */
	public int update(BoardDTO board);

	/* 게시판 게시글 삭제 */
	public int delete(BoardDTO board);

	/* 게시판 총 개수 가져오기 */
	public int getTotal(Criteria cri);
	
	/* 게시판 댓글 수 */
	public void replyCnt(int bno);

}
