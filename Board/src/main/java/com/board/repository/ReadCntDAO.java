package com.board.repository;

public interface ReadCntDAO {

	public void insert(String ip, int bno);
	
	public String read(String ip, int bno);
	
}
