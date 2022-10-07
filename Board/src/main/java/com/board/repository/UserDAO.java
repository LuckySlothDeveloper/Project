package com.board.repository;

import com.board.domain.UserDTO;

public interface UserDAO {

	/* 아이디 존재 유무 */
	public int isId(String id);

	/* 회원가입 */
	public void signup(UserDTO userDTO);

	/* 로그인 */
	public UserDTO login(UserDTO userDTO);
}
