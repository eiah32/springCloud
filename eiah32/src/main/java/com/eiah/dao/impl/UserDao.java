package com.eiah.dao.impl;

import org.springframework.stereotype.Repository;

import com.eiah.dao.IUser;

@Repository
public class UserDao implements IUser{
//	public User findOne(long id) {
//		return JdbcOperations.queryForObject(
//				SELECT_USER_BY_ID, (rs, rowNum) -&gt; {
//					return new User(
//							rs.getLong("id"),
//							rs.getString("username"),
//							rs.getString("password")
//							),
//							id
//				});
//	}
//	
//	private static final String INSERT_USER =
//			"insert into user '" +
//					"(username, password) '" +
//					"values" +
//					" (:username, :password)";
//	public void addUser(User user) {
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("username", user.getUsername());
//		paramMap.put("password", user.getPassword());
//		
//		JdbcOperations.update(INSERT_USER, paramMap);
//	}
}
