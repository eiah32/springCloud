package com.eiah;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @ClassName SpringBootStartApplication
 * @Package com.eiah 
 * @Description 修改启动类，继承SpringBootServletInitializer并重写configure方法
 * @auther eiah32
 * @datatime 2017年12月6日下午8:27:45
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 指向启动类
		return builder.sources(Eiah32Application.class);
	}
}
