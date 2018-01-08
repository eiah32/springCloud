<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome to eiah</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
	<div class="container">
		<div class="info">
			<h1 class="title">Login Form</h1>
		</div>
	</div>
	<div class="form">
		<div class="thumbnail">
			<img src="${pageContext.request.contextPath}/images/user-default.png" />
		</div>
		<!-- 错误提示 -->
		<div class="error">
			<p>${message}</p>
		</div>
		<form class="register-form" method="post" action="/user/register">
			<input type="text" name="username" class="username" placeholder="name" onblur="checkIsExist()"/> 
			<input type="password" name = "password" placeholder="password" /> 
			<button type="submit">create</button>
			<p class="message">
				Already registered? <a href="#">Sign In</a>
			</p>
		</form>
		<form class="login-form" method="post" >
			<input type="text" name="username" placeholder="name" /> 
			<input type="password" name="password" placeholder="password" /> 
			<label class="rememberMe" >
				<input type="checkbox" name="rememberMe"> Remember me
			</label>
			<button type="submit">login</button>
			<p class="message">
				Not registered? <a id="btn" href="#">Create an account</a>
			</p>
		</form>
	</div>
</body>
</html>