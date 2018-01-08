<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>EIAH</title>
    <!-- 自定义样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>  
<body>  
	<div id="cc" class="container">
		<!-- 引入head.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/head.jsp" />
		<!-- 引入body.jsp -->
		<div class="b_container">
			<div class="userInfo">
				<div class="ui-title">
					<b>个人信息</b>
				</div>
				<div class="ui-image">
					<div class="img">
						<a href="/user/showHeadImage">
							<img src="${pageContext.request.contextPath}/images/user-default.png">
						</a>
					</div>
				</div>
				<div class="ui-content">
					<form action="updateUserInfo" method="post" onsubmit="return false">
						<div class="group">
							<div class="g-lable">用户名：</div>
							<div class="g-input">
								<input id="username" disabled="disabled" name="username" value="${user.username}">
							</div>
							<div id="username-tip" class="g-tip">
								<span class="tip-disable">用户名不能修改</span>
							</div>
						</div>
						<div class="group">
							<div class="g-lable">手机：</div>
							<div class="g-input">
								<input id="tel" name="tel" value="${user.tel}">
							</div>
							<div id="tel-tip" class="g-tip">
								<span class="tip-error"></span>
								<span class="tip-ok"></span>
							</div>
						</div>
						<div class="group">
							<div class="g-lable">性别：</div>
							<div class="g-gender">
								<input name="gender" value="1" type="radio" <c:if test="${user.gender == '1'}">checked="checked"</c:if>>男
								<input name="gender" value="0" type="radio" <c:if test="${user.gender == '0'}">checked="checked"</c:if>>女
								<input name="gender" value="" type="radio" <c:if test="${empty user.gender}">checked="checked"</c:if>>保密
							</div>
						</div>
						<div class="group">
							<div class="g-lable">生日：</div>
							<div class="g-input">
								<input id="birthday" name="birthday" type="date" value="${user.birthday}">
							</div>
							<div id="birthday-tip" class="g-tip">
								<span class="tip-error"></span>
								<span class="tip-ok"></span>
							</div>
						</div>
						<div class="group">
							<div class="g-lable">E-mail：</div>
							<div class="g-input">
								<input id="email" name="email" value="${user.email}">
							</div>
							<div id="email-tip" class="g-tip">
								<span class="tip-error"></span>
								<span class="tip-ok"></span>
							</div>
						</div>
						<div class="ui-save">
							<input id="save-submit" type="submit" value="保存">
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- 引入bottom.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/bottom.jsp" />
	</div>
</body>
</html>