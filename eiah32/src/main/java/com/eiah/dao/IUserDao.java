package com.eiah.dao;

import java.util.List;

import com.eiah.domain.User;

public interface IUserDao {
	/**
	 * 分页查询
	 * @param startIndex
	 * @param size
	 * @return
	 */
	List<User> findPageRecords(int startIndex,int size);
}
