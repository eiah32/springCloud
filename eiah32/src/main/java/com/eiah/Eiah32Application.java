package com.eiah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置和启动引导
 */
// 开启组件扫描和自动配置
@EnableTransactionManagement // 启用事物管理
@SpringBootApplication
@RestController
public class Eiah32Application {
	// 负责启动引导应用程序
	public static void main(String[] args) {
		SpringApplication.run(Eiah32Application.class, args);
	}

}