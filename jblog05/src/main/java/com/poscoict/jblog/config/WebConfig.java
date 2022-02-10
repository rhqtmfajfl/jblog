package com.poscoict.jblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poscoict.config.web.FileuploadConfig;
import com.poscoict.config.web.MessageConfig;
import com.poscoict.config.web.MvcConfig;
import com.poscoict.config.web.SecurityConfig;
import com.poscoict.jblog.security.BlogInterceptor;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.jblog.controller", "com.poscoict.jblog.exception"})
@Import({MvcConfig.class, SecurityConfig.class, MessageConfig.class, FileuploadConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter{
//	//	<!-- auto proxy --> 
//	//	<aop:aspectj-autoproxy />  -> @EnableAspectJAutoProxy
//
////interceptor 시 bean을 여러개 만들어서 하나하나 인터셉터를 해주면 된다.
//
	
   @Bean
   public HandlerInterceptor blogInterceptor() {
      return new BlogInterceptor();
   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry
      .addInterceptor(blogInterceptor())//			<bean class="com.poscoict.mysite.security.SiteInterceptor"/>
      .addPathPatterns("/**");//						<mvc:mapping path="/**" />
   }
   

		
}
