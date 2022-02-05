package com.poscoict.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		UserVo authUser = userService.getUser(id, password);  //여기서 사용자 id와 pass 가져온다.
		if(authUser == null) {
			request.setAttribute("result", "fail");
			request.setAttribute("id", id);
			request
				.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
				.forward(request, response);
			return false;
		}
		
		// session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
//		String id2 = authUser.getId();
//		System.out.println(id2);
		
		response.sendRedirect(request.getContextPath() + "/" + id );
		
		return false;
	}
}