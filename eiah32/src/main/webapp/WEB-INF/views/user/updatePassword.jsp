<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<div class="userInfo-pwd">
				<div class="ui-title">
					<b>修改密码</b>
				</div>
				<div class="ui-content-pwd">
					<form action="updatePassword" method="post">
						<div class="group">
							<div class="g-lable">原密码：</div>
							<div class="g-input">
								<input name="oldPassword" type="password">
							</div>
						</div>
						<div class="group">
							<div class="g-lable">新密码：</div>
							<div class="g-input">
								<input name="newPassword" type="password">
							</div>
						</div>
						<div class="group">
							<div class="g-lable">确认密码：</div>
							<div class="g-input">
								<input name="reNewPassword" type="password">
							</div>
						</div>
						<div class="ui-save">
							<input type="submit" value="修改">
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