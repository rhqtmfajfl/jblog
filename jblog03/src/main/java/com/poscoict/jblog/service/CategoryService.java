package com.poscoict.jblog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public boolean insert_category(CategoryVo vo) {
		// TODO Auto-generated method stub
		
		categoryRepository.insert_category(vo);
		
		return true;
	}

	public CategoryVo select_category_no(String blog_user_id) {
		// TODO Auto-generated method stub
//		CategoryVo vo = new CategoryVo();
		CategoryVo category_info = categoryRepository.select_category_no(blog_user_id);
		
		
		return category_info;
	}
	
	public Map<String, Object> select_category_all() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("list", categoryRepository.select_category_all());
		
		return map;
		
	}

	public CategoryVo select_no_name(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.select_no_name(name);
	}
	
	


}
