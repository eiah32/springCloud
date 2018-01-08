<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.1/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.5.1/jquery.easyui.min.js"></script>  
    <!-- 自定义样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css">
    <script type="text/javascript" src="js/js.js"></script>
	<title>EIAH</title>
</head>
<body class = "easyui-layout">
	<div class="easyui-tabs" data-options="tabWidth:32,tabHeight:32,plain:true" style="width:100%;height:100%;background-color: #373737; ">
		<div id = "bgE" title="<span class='tt-inner bgE'><img src='images/E.png'/></span>" >
		</div>
		<div id = "bgI" title="<span class='tt-inner'><img src='images/I.png'/></span>" >
		</div>
		<div id = "bgA" title="<span class='tt-inner'><img src='images/A.png'/></span>" >
		</div>
		<div id = "bgH" title="<span class='tt-inner'><img src='images/H.png'/></span>" >
			<div id="bodyH">
				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/head.jsp" />
	</div>
</body>
</html>