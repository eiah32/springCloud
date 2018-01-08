<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1" />
	<title>EIAH</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}jquery-easyui-1.5.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}jquery-easyui-1.5.1/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}jquery-easyui-1.5.1/themes/color.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}jquery-easyui-1.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
    <!-- 自定义样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>  
<body>  
	<div id="cc" style="width: auto;height:400px; overflow-x：hidden;">
		<!-- 引入head.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/head.jsp" />
		
		<!-- 引入menu.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/menu.jsp" />
		
		<!-- 引入body.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/body.jsp" />

		<!-- 引入bottom.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/bottom.jsp" />
	</div>
</body>
</html>