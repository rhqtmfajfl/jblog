package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

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

		
		
		public BlogVo findBlog(String user_id) {
			// TODO Auto-generated method stub
			return sqlSession.selectOne("blog.findBlog", user_id);
		}

		public List<CategoryVo> findCategory(String blog_user_id) {
			return sqlSession.selectList("blog.findCategory", blog_user_id);
		}

		public List<PostVo> findAllPost(String blog_user_id) {
			return sqlSession.selectList("blog.findAllPost", blog_user_id);
		}

		public List<PostVo> findCategoryPostList(String blog_user_id, int category_no) {
			Map<String, Object> param = new HashMap<>();
			param.put("blog_user_id", blog_user_id);
			param.put("category_no", category_no);
			return sqlSession.selectList("blog.findCategoryPostList", param);
		}

		public PostVo findRecentPost(String blog_user_id) {
			return sqlSession.selectOne("blog.findRecentPost", blog_user_id);
		}

		public PostVo findRecentPost(String blog_user_id, int category_no) {
			Map<String, Object> param = new HashMap<>();
			param.put("blog_user_id", blog_user_id);
			param.put("category_no", category_no);
			return sqlSession.selectOne("blog.findCategoryRecentPost", param);
		}

		public PostVo findPost(String blog_user_id, int category_no, int postNo) {
			Map<String, Object> param = new HashMap<>();
			param.put("blog_user_id", blog_user_id);
			param.put("category_no", category_no);
			param.put("postNo", postNo);
			

			PostVo vo = sqlSession.selectOne("blog.findPost", param);
			
			System.out.println("=============================================");
			System.out.println(blog_user_id);
			System.out.println(category_no);
			System.out.println(postNo);
			System.out.println(vo);
			System.out.println("=============================================");
			return vo;
		}
		
		

}
