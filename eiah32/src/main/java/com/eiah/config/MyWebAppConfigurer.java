package com.eiah.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 映射webapp
 * created by eiah on 2017-08-27
 */
@Configuration
@Order(10)
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
	@Value("${tmpImgDirectory}")
	private String tmpImgDirectory;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/tmpImage/**").addResourceLocations("file:" + tmpImgDirectory);
	}
}
