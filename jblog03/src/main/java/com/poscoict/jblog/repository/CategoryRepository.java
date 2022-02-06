package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	public boolean insert_category(CategoryVo vo) {
		// TODO Auto-generated method stub
		

		return 1 == sqlSession.insert("category.insert_category", vo);
	}

}
