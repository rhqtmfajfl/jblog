package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

		@Autowired
		private SqlSession sqlSession;

		public boolean insert_basic_page(BlogVo vo) {
			// TODO Auto-generated method stub
			
			return 1==sqlSession.insert("blog.insert_basic_page", vo);
		}

		public BlogVo select(String user_id) {
			// TODO Auto-generated method stub
			return sqlSession.selectOne("blog.select", user_id);  // title, logo, user_id 출력된다.
		}

		public boolean update_blog(BlogVo vo) {
			// TODO Auto-generated method stub
			return 1 == sqlSession.update("blog.update_blog", vo);
		}
		
		

}
