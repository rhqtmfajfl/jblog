package com.poscoict.jblog;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class BootInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(MySiteApplication.class);  //war로 building 할 수 있도록 한다. 
		//만들때는 여기서 ctrl f11하면되고 
		//
	}

}
