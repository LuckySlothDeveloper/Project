package com.board.repository;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.Criteria;
import com.board.domain.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.board.user";

	
	/* 아이디 존재 유무 */
	@Override
	public int isId(String id) {
		return sqlSession.selectOne(NAMESPACE+".isId", id);
	}

	/* 회원가입 */
	@Override
	public void signup(UserDTO userDTO) {
		sqlSession.insert(NAMESPACE+".signup", userDTO);
	}

	/* 로그인 */
	@Override
	public UserDTO login(UserDTO userDTO) {
		return sqlSession.selectOne(NAMESPACE+".login", userDTO);
	}

	/* 유저 리스트 */
	@Override
	public List<UserDTO> list(Criteria cri) {
		return sqlSession.selectList(NAMESPACE+".list", cri);
	}

	/* 유저 총 수 */
	@Override
	public int getTotal(Criteria cri) {
		return sqlSession.selectOne(NAMESPACE+".getTotal", cri);
	}

	/* 유저 정보 자세히 보기 */
	@Override
	public UserDTO read(String userId) {
		return sqlSession.selectOne(NAMESPACE+".read", userId);
	}

	/* 유저 정보 업데이트 */
	@Override
	public void update(UserDTO userDTO) {
		sqlSession.update(NAMESPACE+".update", userDTO);
	}

	/* 유저 삭제 */
	@Override
	public int delete(String userId) {
		return sqlSession.delete(NAMESPACE+".delete", userId);
	}

	/* 아이디 찾기 */
	@Override
	public UserDTO findId(String userName) {
		return sqlSession.selectOne(NAMESPACE+".findId", userName);
		
	}

	/* 비밀번호 찾기 */
	@Override
	public UserDTO findPw(UserDTO userDTO) {
		return sqlSession.selectOne(NAMESPACE+".findPw", userDTO);
	}

	/* 일회용 비밀번호 DB 저장 */
	@Override
	public void setPwChange(UserDTO userDTO) {
		sqlSession.update(NAMESPACE+".setPwChange", userDTO);
		
	}

	
	
	
}
