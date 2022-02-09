package com.poscoict.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc  //Mvc 를 설정 하게 해준다. mvc:annotation-driven 과 같은 것
public class MvcConfig extends WebMvcConfigurerAdapter {  //WebMvcConfigurerAdapter여기에 들어있다.	<!-- 서블릿 컨테이너(tomcat)의 DefaultServlet 위임(delegate) Handler -->
// WebMvcConfigurerAdapter durldp 

	
	//view Resolver 설정		
	@Bean
		public ViewResolver viewResolver() {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class); //		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView" /> 이부분
			viewResolver.setPrefix("/WEB-INF/views/");//		<property name="prefix" value="/WEB-INF/views/"/>
			viewResolver.setSuffix(".jsp");//		<property name="suffix" value=".jsp"/>
			
			return viewResolver;
		}
		
		// Message Converter
		
//		//DefaultServlet 위임(delegate) Handler
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
	
	
}
