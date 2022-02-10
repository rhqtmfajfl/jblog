package com.poscoict.jblog.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.security.AuthUser;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;

@Controller
//@RequestMapping("/{id}")
@RequestMapping("/{id:(?!assets|images).*}")  //: 로하면 뒤에 패턴을 찾을 수 있게 해준다. : .* 로 할 수 있다.(?!assets).*) assets라는 것이 있는 것  
public class BlogController {
		
		@Autowired
		private BlogService blogService;
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private FileUploadService fileUploadService;
		
		@Autowired
		private CategoryService categoryService;
		
		@Autowired
		private PostService postService;
		
		
//		@ResponseBody
//		@RequestMapping({"","/{pathNo1}","/{pathNo1}/{pathNo2}"})
//		public String index(@PathVariable("id") String id, 
//				@PathVariable("pathNo1")Optional<Long> pathNo1,   //Optional은 null에대한 처리이다.
//				@PathVariable("pathNo2")Optional<Long> pathNo2) {
//			
//			
//			Long categoryNo = 0L;
//			Long postNo = 0L;
//			
//			
//			
//			if(pathNo2.isPresent()) {
//				categoryNo = pathNo1.get();
//				postNo = pathNo2.get(); 
//			}else if(pathNo1.isPresent()) {
//				categoryNo = pathNo1.get();
//			}
//			//디폴트 카테고리 no를 찾는다.
//			// 위에서 postNo 를 0L로 설정 한다.
//			System.out.println("id :" + id);
//			System.out.println("categoryNo :" + categoryNo);  //여기서 categoryNo를 서비스로 넘겨준다.
//			System.out.println("PostNo : " + postNo);
//			
//			return "BlogController.index";
//		}
//		
////	@ResponseBody
//		@RequestMapping("/{categoryNo}/{postNo}")
//		public String index1(@PathVariable("id") String id, 
//				@PathVariable("cateogryNo")Long categoryNo) {
//			
//			
//			return null;
//		}
//		
//		@RequestMapping("/{categoryNo}/{postNo}")
//		public String index2(@PathVariable("id") String id, 
//				@PathVariable("cateogryNo")Long categoryNo,
//				@PathVariable("postNo")Long postNo) {
//			
//			
//			return null;
//		}
		
		@RequestMapping(value={ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
		public String list(@AuthUser UserVo authUser, 
				@PathVariable("id") String id,
				@PathVariable("pathNo1") Optional<Integer> pathNo1,
				@PathVariable("pathNo2") Optional<Integer> pathNo2,
				Model model, HttpSession session) {
			
			int categoryNo = 0;
			int postNo = 0;
			
			if (pathNo2.isPresent()) {
				categoryNo = pathNo1.get();
				postNo = pathNo2.get();
			} else if (pathNo1.isPresent()) {
				categoryNo = pathNo1.get();
			}
			

			model.addAttribute("categoryList", blogService.getCategoryList(id)); // 얘도 이동해야할듯

			model.addAttribute("postList", blogService.getPostList(id, categoryNo));
			model.addAttribute("post", blogService.getPost(id, categoryNo, postNo));
			//여기서 getPost로 인해서
			BlogVo blog_vo = blogService.select(id);

			

			session.setAttribute("id", id);

			model.addAttribute("blog_vo", blog_vo);
			
			
			return "blog/blog-main";
		}
		
		@Auth
		@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
		public String basic(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model, HttpSession session) {
			
			System.out.println("여기는 블로그의 basic으로 가는 곳");

			BlogVo blog_vo = blogService.select(authUser.getId());

			model.addAttribute("blog_vo", blog_vo);
			
			return "blog/blog-admin-basic";
		}

		@Auth
		@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
		public String write(
				@PathVariable("id") String id,
				@RequestParam(value="title", required=true, defaultValue="")String title,
				@RequestParam(value="logo-file") MultipartFile multipartFile) {
			
				BlogVo vo = new BlogVo();
				
				vo.setTitle(title);
				vo.setLogo(fileUploadService.restore(multipartFile));
				vo.setUser_id(id);
				
				if(blogService.select(id)==null) {
					blogService.insert_basic_page(vo);
				}
				
				blogService.update_blog(vo);
				
			
			return "redirect:/{id}/admin/basic";
		}
		

		@Auth
		@RequestMapping(value="/admin/category", method=RequestMethod.GET)
		public String category(@AuthUser UserVo authUser,@PathVariable("id") String id, Model model) {
			
			System.out.println("여기는 블로그의 category로 가는 곳");

			Map<String, Object> map = categoryService.select_category_all(id);
			
//			System.out.println("지금 map :" +((List<CategoryVo>)map.get("list")).get(0).getPost_count());
//			System.out.println("지금 map :" +map);
			
			BlogVo blog_vo = blogService.select(authUser.getId());

			model.addAttribute("blog_vo", blog_vo);
			
			model.addAttribute("category_list", map);
			
			return "blog/blog-admin-category";
		}

		@Auth
		@RequestMapping(value="/admin/category", method=RequestMethod.POST)
		public String category(@PathVariable("id") String id, 
				@RequestParam(value="name", required=true, defaultValue="") String name,
				@RequestParam(value="desc", required=true, defaultValue="")String desc,
				Model model) {
			
			CategoryVo vo =new CategoryVo();
			
			vo.setBlog_user_id(id);
			vo.setName(name);
			vo.setDescription(desc);
			
			categoryService.insert_category(vo);
			
			
			
			return "redirect:/{id}/admin/category";
			
		}
		
		

		@Auth
		@RequestMapping(value="/admin/write", method=RequestMethod.GET)
		public String write(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model) {
			
			System.out.println("여기는 블로그의 write로 가는 곳");

			//여기서 카테고리 name을 넘겨 줄수 있어야 한다. 그래야 
			//카테고리 명을 선택 할 수 있다.
			
			BlogVo blog_vo = blogService.select(authUser.getId());

			model.addAttribute("blog_vo", blog_vo);
			
			Map<String, Object> map = categoryService.select_category_all(id);
			model.addAttribute("category_list", map);
			
			
			return "blog/blog-admin-write";
		}
		

		@Auth
		@RequestMapping(value="/admin/write", method=RequestMethod.POST)
		public String write(@PathVariable("id") String id, 
				@RequestParam(value="title", required=true, defaultValue="")String title,
				@RequestParam(value="content", required=true, defaultValue="")String content,
				@RequestParam(value="category", required=true, defaultValue="")int no,
				Model model) {
				
			//여기서 select로 category의 no를 가지고 와야 한다. name category에서의 value 값을 가져오면 된다.
			

			PostVo vo = new PostVo();
			
			vo.setTitle(title);
			vo.setContents(content);
			vo.setCategory_no(no);
			
			postService.insert_post_info(vo);
			
			return "redirect:/{id}/admin/write";
		}

		@Auth
		@RequestMapping(value="/delete/category/{no}", method=RequestMethod.GET)
		public String delete(@AuthUser UserVo authUser, 
				@PathVariable("id")String id,
				@PathVariable("no")int no,
				Model model) {
			
				//no 개수가 여기로 넘어 온다.
			//그리고 no가 0인것을 삭제 한다.
			
			
			categoryService.delete_category(no);
			
			
			return "blog/blog-admin-category";
		}
		
}
