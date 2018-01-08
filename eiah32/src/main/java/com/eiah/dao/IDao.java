package com.eiah.dao;

import java.io.Serializable;

public interface IDao<T> {
	public abstract T add(T t);
	
	public abstract void update(T t);
	
	/**
	 * 按照主键删除某条记录
	 * @param pk 主键类型要实现Serializable接口。比如自定义的联合主键
	 */
	public abstract void delete(Serializable pk);
	
	/**
	 * 作为主键的类型必须实现Serializable
	 * @param pk
	 * @return
	 */
	public abstract T selectOne(Serializable pk);
}
