package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.UserDTO;
import com.board.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	
	/* 아이디 존재 유무 */
	@Override
	public int isId(String id) {
		return userDAO.isId(id);
	}
	
	/* 회원가입 */
	@Override
	public void signup(UserDTO userDTO) {
		userDAO.signup(userDTO);
	}

	/* 로그인 처리 */
	@Override
	public UserDTO login(UserDTO userDTO) {
		return userDAO.login(userDTO);
	} 
	
}
