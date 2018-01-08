package com.eiah.domain;

import java.io.Serializable;

/**
 * 处理异常返回信息实体类
 * created by eiah on 2017-08-20
 */
public class ReturnType implements Serializable{
	private static final long serialVersionUID = 1L;

	private int code;  
    private String data;  
    private String msg;
    
	public ReturnType() {
		super();
	}

	public ReturnType(int code, String data, String msg) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}
