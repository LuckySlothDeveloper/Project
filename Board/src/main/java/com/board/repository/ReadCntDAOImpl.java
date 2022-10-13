package com.board.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReadCntDAOImpl implements ReadCntDAO {

	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE = "com.board.readCnt";
	
	
	@Override
	public void insert(String ip, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ip", ip);
		map.put("bno", bno);
		
		sqlSession.insert(NAMESPACE+".insert", map);
	}

	@Override
	public String read(String ip, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ip", ip);
		map.put("bno", bno);
		
		return sqlSession.selectOne(NAMESPACE+".read", map);
	}
	
	
	
}
