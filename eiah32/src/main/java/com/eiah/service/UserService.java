package com.eiah.service;

import java.io.IOException;
import java.util.List;

import com.eiah.domain.User;

/**
 * 用户业务类
 * created by eiah on 2017-06-23
 */
public interface UserService {
	/**
	 * 添加用户信息
	 * @param user
	 * @return statusCode
	 * @throws IOException
	 * created by eiah on 2017-06-23
	 */
	public abstract Integer addUserInfo(User user) throws IOException;
	
	/**
	 * 添加至用户登录信息
	 * @param user
	 * @return statusCode
	 * @throws IOException
	 * created by eiah on 2017-07-28
	 */
	public abstract Integer addUserLogin(User user) throws IOException;
	
	/**
	 * 查询所有用户
	 * @param rows 
	 * @param page 
	 * @return List<User>
	 * created by eiah on 2017-06-23
	 */
	public abstract List<User> queryListUser(String page, String rows) ;
	
	/**
	 * 根据用户名检查用户是否存在
	 * @param username
	 * @return user
	 * created by eiah on 2017-06-23
	 */
	public abstract List<Integer> checkUserIsExist(String username);

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return user
	 * created by eiah on 2017-07-30
	 */
	public abstract User findByUsername(String username);
	
	/**
	 * 根据用户名查找用户信息、角色及权限
	 * @param username
	 * @return List<User>
	 * created by eiah on 2017-07-31
	 */
	public abstract List<User> findUserAllInfoByUsername(String username);

	/**
	 * 更新用户信息
	 * @param user
	 * @return statusCode
	 * created by eiah on 2017-08-09
	 */
	public abstract Integer updateUserInfo(User user);

	/**
	 * 更新用户登录表信息
	 * @param user
	 * @return statusCode
	 * created by eiah on 2017-08-10
	 */
//	public abstract Integer updateUserLogin(User user);
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return user
	 * created by eiah on 2017-08-10
	 */
	public abstract User findUserById(String id);

	/**
	 * 根据id更新用户信息 密码
	 * @param sessionUser
	 * @return  statusCode
	 * created by eiah on 2017-08-20
	 */
	public abstract int updateUserInfoInPasswordById(User sessionUser);
	
	/**
	 * 根据id更新用户登录 密码
	 * @param sessionUser
	 * @return  statusCode
	 * created by eiah on 2017-08-20
	 */
	public abstract int updateUserLoginInPasswordById(User sessionUser);

	/**
	 * @Title updateUserImgPath
	 * @Description 更新用户头像(图片)路径 
	 * @param user
	 * @return int
	 * @auther eiah32
	 * @datatime 2017年11月29日下午9:31:55
	 */
	public abstract int updateUserImgPath(User user);
	
}
