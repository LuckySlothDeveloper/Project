package com.board.repository;

import java.util.List;

import com.board.domain.GalleryAttachDTO;

public interface GalleryAttachDAO {
	
	/* 첨부파일 등록 */
	public void insert(GalleryAttachDTO galleryAttachDTO);
	
	/* 첨부파일 삭제 */
	public void delete(String uuid);
	
	/* 갤러리 글번호로 첨부파일 찾기 */
	public List<GalleryAttachDTO> findByGbno(int gbno);
}
