package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {
		
		@Autowired
		private BlogService blogService;
	
		@RequestMapping(value="", method=RequestMethod.GET)
		public String list(Model model) {
//			Map<String, Object> map = blogService.getContentsList();
			
//			UserVo vo = (UserVo)request
			
			return "blog/blog-main";
		}
		
		@RequestMapping(value="/{id}/blog-admin-basic", method=RequestMethod.GET)
		public String admin_basic(Model model) {
//			Map<String, Object> map = blogService.getContentsList();
			
			
			return "blog/blog-admin-basic";
		}

		
		
		
}
