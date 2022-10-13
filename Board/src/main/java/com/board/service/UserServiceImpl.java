package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.Criteria;
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

	/* 유저 리스트 */
	@Override
	public List<UserDTO> list(Criteria cri) {
		return userDAO.list(cri);
	}

	/* 유저 총 수 */
	@Override
	public int getTotal(Criteria cri) {
		return userDAO.getTotal(cri);
	}

	/* 유저 정보 자세히 보기 */
	@Override
	public UserDTO read(String userId) {
		return userDAO.read(userId);
	}

	/* 유저 정보 업데이트 */
	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
	}

	/* 유저 삭제 */
	@Override
	public int delete(String userId) {
		return userDAO.delete(userId);
	}

	/* 아이디 찾기 */
	@Override
	public UserDTO findId(String userName) {
		return userDAO.findId(userName);
	}

	/* 비밀번호 찾기 */
	@Override
	public UserDTO findPw(UserDTO userDTO) {
		return userDAO.findPw(userDTO);
	}

	/* 일회용 비밀번호 DB 저장 */
	@Override
	public void setPwChange(UserDTO userDTO) {
		userDAO.setPwChange(userDTO);
		
	} 
	
}
