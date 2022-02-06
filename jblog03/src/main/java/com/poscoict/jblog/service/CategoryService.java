package com.poscoict.jblog.service;

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


}
