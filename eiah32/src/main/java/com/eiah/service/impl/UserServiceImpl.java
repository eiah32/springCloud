package com.eiah.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eiah.domain.User;
import com.eiah.mapper.IUserMapper;
import com.eiah.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private SqlSession sqlSession;

	@Transactional
	@Override
	public Integer addUserInfo(User user) throws IOException {
		// 会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
		sqlSession.getMapper(IUserMapper.class).addUserInfo(user);
		return addUserLogin(user);
	}
	
	@Override
	public Integer addUserLogin(User user) throws IOException {
		return sqlSession.getMapper(IUserMapper.class).addUserLogin(user);
	}
	
	@Override
	public List<User> queryListUser(String page, String rows) {
		try {
			// 会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
			return userMapper.queryListUser(page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Integer> checkUserIsExist(String username) {
		return sqlSession.getMapper(IUserMapper.class).checkUserIsExist(username);
		
	}

	@Override
	public User findByUsername(String username) {
		return sqlSession.getMapper(IUserMapper.class).findByUsername(username);
	}

	@Override
	public List<User> findUserAllInfoByUsername(String username) {
		return sqlSession.getMapper(IUserMapper.class).findUserAllInfoByUsername(username);
	}

	@Override
	public Integer updateUserInfo(User user) {
		return sqlSession.getMapper(IUserMapper.class).updateUserInfo(user);
	}

	@Override
	public User findUserById(String id) {
		return sqlSession.getMapper(IUserMapper.class).findUserById(id);
	}

	@Transactional
	@Override
	public int updateUserInfoInPasswordById(User sessionUser) {
		if (1 == sqlSession.getMapper(IUserMapper.class).updateUserInfoInPasswordById(sessionUser))
			return updateUserLoginInPasswordById(sessionUser);
		else
			return 0;
	}

	@Override
	public int updateUserLoginInPasswordById(User sessionUser) {
		return sqlSession.getMapper(IUserMapper.class).updateUserLoginInPasswordById(sessionUser);
	}

	public int updateUserImgPath(User user) {
		return sqlSession.getMapper(IUserMapper.class).updateUserImgPath(user);
	}

}
