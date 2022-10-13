package com.board.repository;

import java.util.List;

import com.board.domain.Criteria;
import com.board.domain.UserDTO;

public interface UserDAO {

	/* 아이디 존재 유무 */
	public int isId(String id);

	/* 회원가입 */
	public void signup(UserDTO userDTO);

	/* 로그인 */
	public UserDTO login(UserDTO userDTO);

	/* 유저 리스트 */
	public List<UserDTO> list(Criteria cri);

	/* 유저 총 수 */
	public int getTotal(Criteria cri);

	/* 유저 정보 자세히 보기 */
	public UserDTO read(String userId);

	/* 유저 정보 업데이트 */
	public void update(UserDTO userDTO);

	/* 유저 삭제 */
	public int delete(String userId);

	/* 아이디 찾기 */
	public UserDTO findId(String userName);

	/* 비밀번호 찾기 */
	public UserDTO findPw(UserDTO userDTO);

	/* 일회용 비밀번호 DB 저장 */
	public void setPwChange(UserDTO userDTO);
}
