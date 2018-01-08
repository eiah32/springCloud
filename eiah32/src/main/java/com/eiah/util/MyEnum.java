package com.eiah.util;

public enum MyEnum {
	// 用户状态_启用
	USERSTATUS_DISABLE("禁用", 0), 
	// 用户状态_禁用
	USERSTATUS_ENABLE("启用", 1);

	private String name;
	private Integer index;

	private MyEnum(String name, Integer index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
