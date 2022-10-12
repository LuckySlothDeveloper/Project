package com.board.domain;

import java.sql.Date;
import java.util.Objects;

public class UserDTO {

	//아이디
	private String userId;
	
	//비밀번호
	private String password;
	
	//이름
	private String userName;
	
	//관리자 여부(0:관리자 X, 1: 관리자 O)
	private int adminCk;
	
	//가입일
	private Date regDay;
	
	
	/* 디폴트 생성자 */
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	/* 사용자 생성자 */
	public UserDTO(String userId, String password, String userName, int adminCk, Date regDay) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.adminCk = adminCk;
		this.regDay = regDay;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAdminCk() {
		return adminCk;
	}

	public void setAdminCk(int adminCk) {
		this.adminCk = adminCk;
	}

	public Date getRegDay() {
		return regDay;
	}

	public void setRegDay(Date regDay) {
		this.regDay = regDay;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", password=" + password + ", userName=" + userName + ", adminCk="
				+ adminCk + ", regDay=" + regDay + "]";
	}
	
}
