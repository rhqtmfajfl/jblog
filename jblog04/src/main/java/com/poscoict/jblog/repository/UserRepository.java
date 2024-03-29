package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert(UserVo userVo) {
		// TODO Auto-generated method stub
		return 1==sqlSession.insert("user.insert", userVo);
	}

	public UserVo findByIdAndPassword(String id, String password) {
		Map<String, String> map =new HashMap<>();
		map.put("id",id);
		map.put("password", password);
		
		
		return sqlSession.selectOne("user.findByIdAndPassword", map);
	}

	public UserVo findByIdAndName(String id) {
		// TODO Auto-generated method stub
		
		
		return sqlSession.selectOne("user.findByIdAndName", id);
	}
	
	
}
