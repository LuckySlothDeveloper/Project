package com.board.repository;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	
	
	
}
