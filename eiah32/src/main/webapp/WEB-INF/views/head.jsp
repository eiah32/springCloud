<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="h_container">
		<div class="h_d1">
	            <div class="logo">
	            	<p>EIAH</p>
	            </div>
	            <div class="menu">
					<ul class="nav">
						<li><a href="#">关于我们</a></li>
						<li><a href="#">About</a></li>
						<li><a href="#">About</a></li>
						<li><a href="#">About</a></li>
						<li><a href="/">首页</a></li>
					</ul>
				</div>
	            <div class="image">
					<img src="${pageContext.request.contextPath}/images/user-default.png" />
					<div class="nav-info">
						<ul class="nav">
							<li><a href="/user/showUserInfo">个人信息</a></li>
							<li><a href="/user/showUpdatePassword">修改密码</a></li>
							<li><a href="/user/logout">安全退出</a></li>
						</ul>
					</div>
				</div>
		</div>
	</div>
</body>
</html>