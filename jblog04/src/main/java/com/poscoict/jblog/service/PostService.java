package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public boolean insert_post_info(PostVo vo) {
		// TODO Auto-generated method stub
		
		return postRepository.insert_post_info(vo);
	}

	public PostVo select_category_no_from_post() {
		// TODO Auto-generated method stub
		return postRepository.select_category_no_from_post();
	}

	public int select_post_count(int category_no) {
		// TODO Auto-generated method stub
		return postRepository.select_post_count(category_no);
	}
	
	

}
