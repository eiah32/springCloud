package com.eiah.controller;

import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.eiah.config.EncryptPassword;
import com.eiah.domain.User;
import com.eiah.service.UserService;
import com.eiah.util.DataTimeUtil;
import com.eiah.util.MyEnum;
import com.eiah.util.MyUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name = "encryptPassword")
	private EncryptPassword encryptPassword;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public String updatePassword (HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取表单提交参数
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String reNewPassword = request.getParameter("reNewPassword");
		
		// 判断表单提交参数是否为空
		if (oldPassword != null && !"".equals(oldPassword) && newPassword != null && !"".equals(newPassword) && reNewPassword != null && !"".equals(reNewPassword)) { 
			//判断两次输入密码是否一致并且新密码和就密码不能相同
			if (newPassword.equals(reNewPassword) && !oldPassword.equals(newPassword)) {
				User sessionUser = (User) session.getAttribute("user");
				String id = sessionUser.getId();
				String username = sessionUser.getUsername();
				// 加密且已存在密码
				String password  = sessionUser.getPassword();
				String salt = sessionUser.getSalt();
				// 判断session中段id、username、salt、password是否为空
				if (id != null && !"".equals(id) && username != null && !"".equals(username) && salt != null && !"".equals(salt) && password != null && !"".equals(password)) {
					// 加密旧密码
					sessionUser.setPassword(oldPassword);
					encryptPassword.updatePassword(sessionUser);
					// 判断旧密码与session中的密码是否一致
					if (password.equals(sessionUser.getPassword())) {
						// 加密新密码
						sessionUser.setPassword(newPassword);
						encryptPassword.updatePassword(sessionUser);
						// 更新密码
						int status = userService.updateUserInfoInPasswordById(sessionUser);
						System.out.println(status == 1);
						System.out.println("1".equals(status));
						// 修改成功
						if (status == 1) {
							// 查询用户信息
							User user = userService.findUserById(id);
							// 密码更新成功同步session中的信息
							if (sessionUser.getPassword().equals(user.getPassword())) {
								session.setAttribute("user", sessionUser);
							} else {
								session.removeAttribute("user");
							}
							return "redirect:/index";
						} else {
							// 修改失败
							return "redirect:/404";
						}
					} else {
						// 原密码与session中的密码不一致
						return "/user/updatePassword";
					}
				} else {
					// session中不存在此用户信息
					session.removeAttribute("user");
					return "redirect:/index";
				}
			} else {
				// 两次密码输入不一致或原密码与新密码相同
				return "/user/updatePassword";
			}
		} else {
			// 表单提交参数为空
			return "redirect:/user/updatePassword";
		}
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * created by eiah on 2017-08-09
	 */
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public String updateUserInfo(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		// 取出session中的id和username
		String sessionId = sessionUser.getId();
		// 判断session中的id和username字段是否为空
		if (sessionId == null || "".equals(sessionId)) {
			session.removeAttribute("user");
			return "redirect:index";
		}
		// 更新用户信息和用户登录对象
		user.setId(sessionId);
		Integer status = userService.updateUserInfo(user);
		// 更新用户信息成功
		if (status == 1) {
			// 根据id查询用户信息
			User userInfo = userService.findUserById(sessionId);
			if (userInfo != null) {
				session.setAttribute("user", userInfo);
				return "index";
			} else {
				session.removeAttribute("user");
				return "redirect:/user/login";
			}
		} else {
			return "user/userInfo";
		}
	}

	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return 1:可用;-1:不可用;0:空;
	 * @throws FileNotFoundException 
	 * created by eiah on 2017-07-18
	 */
	@ResponseBody
	@RequestMapping(value = "/checkIsExist", produces = "application/json")
	public JSONObject checkUserIsExist(String username) {
		JSONObject jsonObject = new JSONObject();
		if (!"".equals(username)) {
			List<Integer> userList = userService.checkUserIsExist(username);
			if (userList == null || userList.size() == 0) {
				jsonObject.put("tip", "1");
			} else {
				jsonObject.put("tip", "-1");
			}
		} else {
			jsonObject.put("tip", "0");
		}
		return jsonObject;
	}
	
	/**
	 * 处理用户注册
	 * @param user
	 * @param redirectAttributes
	 * @return
	 * created by eiah on 2017-07-05
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegisteration(User user, RedirectAttributes redirectAttributes) {
		Integer statusCode = 0;
		try {
			JSONObject jsonObject= checkUserIsExist(user.getUsername());
			// 1:可用
			if ("1".equals(jsonObject.get("tip"))) {
				// 初始化用户信息
				user.setId(MyUtil.getPK());
				user.setRegistDate(DataTimeUtil.parseTime(DataTimeUtil.getCurrentDateTime()));
				user.setLastLoginDate(DataTimeUtil.parseTime(DataTimeUtil.getCurrentDateTime()));
				user.setStatus(MyEnum.USERSTATUS_ENABLE.getIndex());
				// 加密密码及盐
				encryptPassword.encryptPassword(user);
				statusCode = userService.addUserInfo(user);
			} else {
				redirectAttributes.addFlashAttribute("message", "用户名已存在！");
				return "redirect:/index";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (statusCode == 1) {
			redirectAttributes.addFlashAttribute("message", "注册成功！");
			System.out.println("注册成功");
			return "redirect:/index";
		} else {
			return "redirect:registForm";
		}

	}

	/**
	 * 处理用户登录
	 * @param user
	 * @param rememberMe
	 * @param redirectAttributes
	 * @return
	 * created by eiah on 2017-07-05
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, User user, String rememberMe, RedirectAttributes redirectAttributes) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = null;
		try {
			token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			if (Boolean.parseBoolean(rememberMe))
				token.setRememberMe(true);
				
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			redirectAttributes.addFlashAttribute("message", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		} catch (LockedAccountException lae) {
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}

		// TODO 权限判断
		// 判断用户角色权限
		boolean hasRole = currentUser.hasRole("admin");
		System.out.println(hasRole);
		
		// TODO 更新用户最后一次登录时间
		
		
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			return "redirect:/";
		} else {
			request.getSession().removeAttribute("user");
			token.clear();
			return "redirect:/user/login";
		}
		
	}

	/**
	 * 显示登录表单
	 * @return String
	 * created by eiah on 2017-07-05
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm() {
		return "loginForm";
	}
	
	/**
	 * 显示个人信息表单
	 * @return String
	 * created by eiah on 2017-08-07
	 */
	@RequestMapping(value = "/showUserInfo", method = RequestMethod.GET)
	public String showUserInfo() {
		return "user/userInfo";
	}
	
	/**
	 * 显示修改密码表单
	 * @return String
	 * created by eiah on 2017-08-07
	 */
	@RequestMapping(value = "/showUpdatePassword", method = RequestMethod.GET)
	public String showUpdatePassword() {
		return "user/updatePassword";
	}
	
	/**
	 * 展示头像
	 * @return String
	 * created by eiah on 2017-08-20
	 */
	@RequestMapping(value = "/showHeadImage", method = RequestMethod.GET)
	public String showHeadImage() {
		return "user/headImage";
	}
//		// 缩放图片
//		zoomImage("D:/t1/ftwx.jpg", "D:/t1/eee.jpg", imgWidth, imgHeight);
}
