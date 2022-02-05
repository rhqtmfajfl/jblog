package com.poscoict.jblog.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.security.AuthUser;
import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.UserVo;



@Controller  //userController가 userservice를 사용한다. userserive는 sping-servlet.xml
@RequestMapping("/user") // from이나 이런 곳에서 절대 경로뒤에 /user 붙여 줄 수 있다.
public class UserController {  //userservice가 di 해준다.

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			
			return "user/join";
		}
		
		userService.join(userVo);
		
		return "redirect:/user/joinsuccess";
		
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinsuccess() {  //여기서 join을 join form을 보여 준면 된다.
		return "user/joinsuccess";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		System.out.println("여기서 login 페이지로 넘어 간다.");
		return "user/login";
	}
	
	@Auth
	@RequestMapping(value="/login/auth", method=RequestMethod.GET)
	public String login(HttpSession session,
			UserVo authUser,
			Model model) {
		System.out.println("여기서 login이 된다.");

//		UserVo authUser = userService.getUser(id, password);
//	
//		if(authUser==null) {
//			model.addAttribute("result", "fail");
//			model.addAttribute("id", id);
//			return "user/login"; // 포워딩으로 다시 로그인 화면 생성하게끔
//		}
		System.out.println(authUser.getName());
		System.out.println("여기서 id 를 찾는다. : " + authUser.getId());
		System.out.println("여기서 id 를 찾았다.. : " + authUser.getId());

		session.setAttribute("authUser",  authUser); // authUser에는 id와 name을 출력할 수 있다.
	
		
		return "redirect:/blog";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {  //여기서 join을 join form을 보여 준면 된다.
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	
}