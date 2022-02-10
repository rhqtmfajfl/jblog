package com.poscoict.jblog.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.poscoict.jblog.config.AppConfig;
import com.poscoict.jblog.config.WebConfig;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//AbstractAnnotationConfigDispatcherServletInitializer 여기서 context load listener설정을 하고 context param을 하는것이다.
	//이거 자체가 DIspatcher Servlet 작업을 해준다.
	
	
	//root application을 설정하는것
	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[]{AppConfig.class};  //generic 인데 배열을 하나 만든것  new int[5]{1,2,3,4,5} 와 같은 의미  contextparam에서 뒤에 Appconfig를 가지고 온다.
	}
	
	
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}

	//webConfig를 설정 하는것
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{WebConfig.class};
	}


	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		return new Filter[]{new CharacterEncodingFilter("utf-8", false)};
	}


//	@Override
//	protected void customizeRegistration(Dynamic registration) {
//
//		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");  //url에 매핑되는 핸들러가 없으면  throwException으로 404에러 발생
	// //NoHandlerFound Exception이 발생한ㄷ.
//	}
	
	

	

}
