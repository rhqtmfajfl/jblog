package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class BlogService {

		@Autowired
		private BlogRepository blogRepository;

		@Autowired
		private PostRepository postRepository;
		
		public boolean insert_basic_page(BlogVo vo) {
			// TODO Auto-generated method stub

			return blogRepository.insert_basic_page(vo);
		}

		public BlogVo select(String user_id) {
			// TODO Auto-generated method stub
			return blogRepository.select(user_id);
		}

		public boolean update_blog(BlogVo vo) {
			// TODO Auto-generated method stub
			
			return blogRepository.update_blog(vo);
		}
		
		
		

		public BlogVo getBlog(String blog_user_id) {
			return blogRepository.findBlog(blog_user_id);
		}

		public List<CategoryVo> getCategoryList(String blog_user_id) {
			return blogRepository.findCategory(blog_user_id);
		}


		public List<PostVo> getPostList(String blog_user_id, int category_no) {
			List<PostVo> postList = null;
			if (category_no == 0) {
				postList = blogRepository.findAllPost(blog_user_id);
			} else {
				postList = blogRepository.findCategoryPostList(blog_user_id, category_no);
			}
			return postList;
		}

		public PostVo getPost(String blog_user_id, int category_no, int postNo) {
			PostVo postVo = null;
			if (category_no == 0) {
				postVo = blogRepository.findRecentPost(blog_user_id);
			} else if (postNo == 0) {
				postVo = blogRepository.findRecentPost(blog_user_id, category_no);
			} else {
				postVo = postRepository.findPost(blog_user_id, category_no, postNo);
			}
			
			
			System.out.println("====================================");
			System.out.println("-Service-");
			System.out.println(postVo);
			System.out.println("====================================");
				
			return postVo;
		}

		public Object getPostList(String id, Long category_no) {
			// TODO Auto-generated method stub
			return null;
		}


}
