package com.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.GalleryAttachDTO;

@Repository
public class GalleryAttachDAOImpl implements GalleryAttachDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.board.galleryAttach";
	
	
	/* 첨부파일 등록 */
	@Override
	public void insert(GalleryAttachDTO galleryAttachDTO) {
		sqlSession.insert(NAMESPACE+".insert", galleryAttachDTO);
	}

	/* 첨부파일 삭제 */
	@Override
	public void delete(String uuid) {
		sqlSession.delete(NAMESPACE+".delete", uuid);
	}

	/* 갤러리 글번호로 첨부파일 찾기 */
	@Override
	public List<GalleryAttachDTO> findByGbno(int gbno) {
		return sqlSession.selectList(NAMESPACE+".findByGbno", gbno);
	}

}
