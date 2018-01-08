//package com.eiah.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
///**
// * @EnableRedisHttpSession注解会创建一个springSessionRepositoryFilter的bean对象去实现这个过滤器。
// * 过滤器负责代替HttpSession。即HttpSession不再发挥作用，而是通过过滤器使用redis直接操作Session。
// * created by eiah on 2017-08-20
// */
//@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
//public class RedisSessionConfig {
//
//	@Bean  
//    public RedisConnectionFactory connectionFactory() {  
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();  
//        connectionFactory.setPort(6379);  
//        connectionFactory.setHostName("127.0.0.1");
//        return connectionFactory;  
//    }
//}
