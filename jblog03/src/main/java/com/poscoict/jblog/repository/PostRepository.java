package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert_post_info(PostVo vo) {

		return 1 == sqlSession.insert("post.insert_post_info", vo);
	}

	public PostVo select_category_no_from_post() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("post.select_category_no_from_post");
	}

	public int select_post_count(int category_no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("post.select_post_count", category_no);
	}
	
	
	
}
