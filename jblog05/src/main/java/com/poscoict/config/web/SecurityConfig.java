package com.poscoict.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poscoict.jblog.security.AuthInterceptor;
import com.poscoict.jblog.security.AuthUserHandlerMethodArgumentResolver;
import com.poscoict.jblog.security.LoginInterceptor;
import com.poscoict.jblog.security.LogoutInterceptor;


@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {


	

	
	// Argument Resolver
		@Bean
		public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
			return new AuthUserHandlerMethodArgumentResolver();
		}
////	<!-- Message Converter, Handler Mapping, Validator 생성 및 설정 -->
////	<mvc:annotation-driven>
////	<mvc:argument-resolvers>
////		<bean class="com.poscoict.mysite.security.AuthUserHandlerMethodArgumentResolver" />
////	</mvc:argument-resolvers>	
		//</mvc:annotation-driven>  이부분 담당
		
		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
			argumentResolvers.add(handlerMethodArgumentResolver());
		}
		
		// Interceptors
		@Bean
		public HandlerInterceptor loginInterceptor() {
			return new LoginInterceptor();
		}

		@Bean
		public HandlerInterceptor logoutInterceptor() {
			return new LogoutInterceptor();
		}

		@Bean
		public HandlerInterceptor authInterceptor() {
			return new AuthInterceptor();
		}

		
		/*
//		 * 
//		 * <mvc:interceptor>
//		 * 
//		 * 	<mvc:mapping path="/**" />
//			<mvc:exclude-mapping path="/user/auth"/>
//			<mvc:exclude-mapping path="/user/logout"/>
//			<mvc:exclude-mapping path="/assets/**"/>
//			<bean class="com.poscoict.mysite.security.AuthInterceptor"/>
//		</mvc:interceptor>
//		 */
//			
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry
				.addInterceptor(loginInterceptor())
				.addPathPatterns("/user/auth");
			
			registry
				.addInterceptor(logoutInterceptor())
				.addPathPatterns("/user/logout");

			registry
				.addInterceptor(authInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/user/auth")
				.excludePathPatterns("/user/logout")
				.excludePathPatterns("/asset/**");
		}
	
	
}
