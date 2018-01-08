package com.eiah.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eiah.domain.ReturnType;

@ControllerAdvice
public class ExceptionHandle {
	/* 
	 * 表明这个handler只处理什么类型的异常 
     * */  
    @ExceptionHandler(value = Exception.class)  
    // 返回值为json或者其他对象  
    @ResponseBody  
    public ReturnType handle(Exception e) { 
        return new ReturnType(-1, e.getMessage(), null);  
    } 
}
