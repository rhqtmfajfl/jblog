package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;

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
}
