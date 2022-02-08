package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class BlogService {

		@Autowired
		private BlogRepository blogRepository;

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

		public BlogVo getBlog(String blogId) {
			return blogRepository.findBlog(blogId);
		}

		public List<CategoryVo> getCategoryList(String blogId) {
			return blogRepository.findCategory(blogId);
		}


		public List<PostVo> getPostList(String blogId, int categoryNo) {
			List<PostVo> postList = null;
			if (categoryNo == 0) {
				postList = blogRepository.findAllPost(blogId);
			} else {
				postList = blogRepository.findCategoryPostList(blogId, categoryNo);
			}
			return postList;
		}

		public PostVo getPost(String blogId, int categoryNo, int postNo) {
			PostVo postVo = null;
			if (categoryNo == 0) {
				postVo = blogRepository.findRecentPost(blogId);
			} else if (postNo == 0) {
				postVo = blogRepository.findRecentPost(blogId, categoryNo);
			} else {
				postVo = blogRepository.findPost(blogId, categoryNo, postNo);
			}

			return postVo;
		}

		public Object getPostList(String id, Long categoryNo) {
			// TODO Auto-generated method stub
			return null;
		}


}
