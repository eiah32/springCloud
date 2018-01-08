package com.eiah.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.eiah.domain.User;

@Mapper
public interface IUserMapper {
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	@Select("select * from tbl_user where id = #{id}")
	public abstract User getUserById(@Param("id") String id);
	
	/**
	 * 查询所有用户信息
	 * @param rows 
	 * @param page 
	 * @param id
	 * @return
	 */
	@Select("select * from userinfo")
	public abstract List<User> queryListUser(@Param("page") String page, @Param("rows") String rows);
	
	/**
	 * 添加用户信息
	 * @param model
	 * @return status
	 */
	@Insert("insert into userinfo (id, username, password) values (#{user.id}, #{user.username}, #{user.password})")
	public abstract Integer addUserInfo(@Param("user") User user);

	/**
	 * 添加用户登录信息
	 * @param model
	 * @return status
	 */
	@Insert("insert into userlogin (id, username, password, regist_date, last_login_date, status, salt) values (#{user.id}, #{user.username}, #{user.password}, #{user.registDate}, #{user.lastLoginDate}, #{user.status}, #{user.salt})")
	public abstract Integer addUserLogin(@Param("user") User user);
	
	/**
	 * 根据用户名检查用户是否存在
	 * @param username
	 * @return user
	 */
	@Select("select 1 from userinfo where username = #{user.username}")
	public abstract List<Integer> checkUserIsExist(String username);

	/**
	 * 根据用户名查找用户对象
	 * @param username
	 * @return user
	 * created by eiah on 2017-07-30
	 */
	@Select("select * from userlogin where username = #{user.username}")
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
	/*public abstract Integer updateUserLogin(User user);*/
	
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return user
	 * created by eiah on 2017-08-10
	 */
	@Select("select ui.id, ui.username, ui.`password`, ui.gender, ui.email, ui.tel, ui.birthday, ul.regist_date registDate, ul.last_login_date lastLoginDate, ul.`status`, ul.salt, ul.src_img_path srcImgPath, ul.cut_img_path cutImgPath from userinfo ui, userlogin ul where ui.id = ul.id and ui.id = #{id}")
	public abstract User findUserById(String id);

	/**
	 * 根据id更新用户密码
	 * @param sessionUser
	 * @return  statusCode
	 * created by eiah on 2017-08-20
	 */
	@Update("update userinfo set password = #{password} where id = #{id}")
	public abstract int updateUserInfoInPasswordById(User sessionUser);

	/**
	 * 根据id更新用户登录 密码
	 * @param sessionUser
	 * @return  statusCode
	 * created by eiah on 2017-08-20
	 */
	@Update("update userlogin set password = #{password} where id = #{id}")
	public abstract int updateUserLoginInPasswordById(User sessionUser);

	/**
	 * @Title updateUserImgPath
	 * @Description 更新用户头像(图片)路径 
	 * @param user
	 * @return int
	 * @auther eiah32
	 * @datatime 2017年11月29日下午9:31:55
	 */
	@Update("update userlogin set src_img_path = #{srcImgPath}, cut_img_path = #{cutImgPath} where id = #{id}")
	public abstract int updateUserImgPath(User user);

}
