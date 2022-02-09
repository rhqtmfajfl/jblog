package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

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

	public PostVo findPost(String blog_user_id, int category_no, int postNo) {
		Map<String, Object> map = new HashMap<>();
		
		
		map.put("blog_user_id", blog_user_id);
		map.put("category_no",category_no );
		map.put("no", postNo);
		
	
		
		
		PostVo vo  = sqlSession.selectOne("post.findPost", map);
		
		System.out.println("============================================");
		

		System.out.println("test map : " + map);
		
		System.out.println(blog_user_id);
		System.out.println(category_no);
		System.out.println(postNo);
		System.out.println(vo);
		
		System.out.println("============================================");
		
		return vo;
	}
	

	
}
