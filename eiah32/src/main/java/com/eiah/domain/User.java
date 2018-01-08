package com.eiah.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.eiah.util.GroupA;

/**
 * 用户实体类
 * created by eiah on 2017-07-01
 */
@Component
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "{id.empty}", groups = GroupA.class)
	private String id;

	@NotEmpty(message = "{username.empty}", groups = GroupA.class)
	@Size(min = 6, max = 16, message = "{username.size}")
	private String username;

	@NotEmpty(message = "{password.empty}", groups = GroupA.class)
	@Size(min = 6, max = 16, message = "{password.size}")
	private String password;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	private Integer gender;
	private String email;
	private String tel;
	private String headImage;
	private LocalDateTime registDate;
	private LocalDateTime lastLoginDate;
	// 用户状态1:启用;2:禁用
	private Integer status;
	// 加密密码的盐
	private String salt;
	// 源图片路径
	private String srcImgPath;
	// 剪贴后图片路径
	private String cutImgPath;
	
	private Role role;

	public User() {
		super();
	}

	public User(String id, String username, String password, LocalDate birthday, Integer gender, String email,
			String tel, String headImage, LocalDateTime registDate, LocalDateTime lastLoginDate, Integer status,
			String salt, String srcImgPath, String cutImgPath) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.tel = tel;
		this.headImage = headImage;
		this.registDate = registDate;
		this.lastLoginDate = lastLoginDate;
		this.status = status;
		this.salt = salt;
		this.srcImgPath = srcImgPath;
		this.cutImgPath = cutImgPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public LocalDateTime getRegistDate() {
		return registDate;
	}

	public void setRegistDate(LocalDateTime localDateTime) {
		this.registDate = localDateTime;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getSrcImgPath() {
		return srcImgPath;
	}

	public void setSrcImgPath(String srcImgPath) {
		this.srcImgPath = srcImgPath;
	}

	public String getCutImgPath() {
		return cutImgPath;
	}

	public void setCutImgPath(String cutImgPath) {
		this.cutImgPath = cutImgPath;
	}

	public String getCredentialsSalt() {
		return this.username + this.salt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", birthday=" + birthday
				+ ", gender=" + gender + ", email=" + email + ", tel=" + tel + ", headImage=" + headImage
				+ ", registDate=" + registDate + ", lastLoginDate=" + lastLoginDate + ", status=" + status + ", salt="
				+ salt + ", srcImgPath=" + srcImgPath + ", cutImgPath=" + cutImgPath + ", role=" + role + "]";
	}
	
}
