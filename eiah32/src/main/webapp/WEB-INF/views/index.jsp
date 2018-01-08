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
		<!-- start -->
		<div class="b_container">
			<div class="b_guides">
				<div class="b_title">
					<span>Learn from cradle to grave</span>
				</div>
			</div>
			<div class="b_list">
				list
			</div>
		</div>
		<!-- start backToTop -->
		<a id="gotop" href="javascript:void(0)"><img src="${pageContext.request.contextPath}/images/top.png" /></a>
		<!-- end backToTop -->
		<!-- end -->
		<!-- 引入bottom.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/bottom.jsp" />
	</div>
</body>
</html>