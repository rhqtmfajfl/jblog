package com.poscoict.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.AuthUser;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {
		
		@Autowired
		private BlogService blogService;
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private FileUploadService fileUploadService;
		
		
		@RequestMapping(value={"","/main"}, method=RequestMethod.GET)
		public String list(@AuthUser UserVo authUser, Model model, HttpSession session) {
			UserVo vo = new UserVo();
//			UserVo vo = (UserVo)session.getAttribute("authUser");
//			UserVo vo = (UserVo)request.getAttribute("authUser");
//			userService.getUser(null, null);
			
			String id = authUser.getId();
			String name = authUser.getName();
			
			vo.setId(id);
			vo.setName(name);
//			String id = authUser.getId();
//			String name = authUser.getName();
			
//			vo.setId(id);
//			vo.setName(name);
			
			System.out.println("여기서 vo 생성 : " +id);
			System.out.println("여기 위엣 vo 생성");

			session.setAttribute("authUser", vo);
//			Map<String, Object> map = blogService.getContentsList();
			
//			UserVo vo = (UserVo)request
			
			return "blog/blog-main";
		}
		
		@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
		public String basic(@PathVariable("id") String id, Model model) {
//			Map<String, Object> map = blogService.getContentsList();
			
			System.out.println("여기는 블로그의 basic으로 가는 곳");
			UserVo vo = userService.find(id);
			
//			String user_id = vo.getId();
			
//			BlogVo blog_vo = blogService.select(user_id);
//			
//			String blog_title = blog_vo.getTitle();
//			String blog_logo = blog_vo.getLogo();
//			String blog_user_id = blog_vo.getUser_id();
			
			
			model.addAttribute("user_id_name", vo);
			
			return "blog/blog-admin-basic";
		}
		
		@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
		public String write(
				@PathVariable("id") String id,
				@RequestParam(value="title", required=true, defaultValue="")String title,
				@RequestParam(value="logo-file") MultipartFile multipartFile) {
			
				BlogVo vo = new BlogVo();
				
				vo.setTitle(title);
				vo.setLogo(fileUploadService.restore(multipartFile));
				vo.setUser_id(id);
				
				
				blogService.insert_basic_page(vo);
			
			return "redirect:/{id}/admin/basic";
		}
		
		
		@RequestMapping(value="/admin/category", method=RequestMethod.GET)
		public String category(@PathVariable("id") String id, Model model) {
			
			System.out.println("여기는 블로그의 category로 가는 곳");
			UserVo vo = userService.find(id);
			model.addAttribute("user_id_name", vo);
			return "blog/blog-admin-category";
		}
		
		
		
		
		@RequestMapping(value="/admin/write", method=RequestMethod.GET)
		public String write(@PathVariable("id") String id, Model model) {
			
			System.out.println("여기는 블로그의 write로 가는 곳");
			UserVo vo = userService.find(id); //find로 id와 name 찾을 수 있다.
			model.addAttribute("user_id_name", vo);
			return "blog/blog-admin-write";
		}
		
	

		
		
		
}
