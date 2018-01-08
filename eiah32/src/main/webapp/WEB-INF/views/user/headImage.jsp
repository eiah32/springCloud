<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>EIAH</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Jcrop.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <!-- 注意js加载顺序 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Jcrop.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>  
	<div id="cc" class="container">
		<!-- 引入head.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/head.jsp" />
		<!-- 引入body.jsp -->
		<div class="b_container">
			<div class="imageInfo">
				<div class="ui-title">
					<b>更换头像</b>
				</div>
				<form id="imgForm" action="/uploadImg/uploadImage" method="post" enctype="multipart/form-data">
					<div class="saveImage">
						<input type="button" value="保存头像" class="saveImg">
						<input type="button" value="重新选择" class="reChoose">
						<input type="file" name="imgFile" id="uploadImg">
					</div>
					<div class="ui-headImage">
						<div class="original-image">
							<div class="mark"></div>
							<img id="myHeadImage" src="${pageContext.request.contextPath }/images/headImgs/${user.srcImgPath}"/>
							<input type="hidden" id="x" name="x" />
		                    <input type="hidden" id="y" name="y" />
		                    <input type="hidden" id="w" name="w" />
		                    <input type="hidden" id="h" name="h" />
						</div>
						<div class="cut-image">
		                    <span id="preview_box" class="crop_preview">
		                        <img id="crop_preview" src="${pageContext.request.contextPath}/images/headImgs/${user.cutImgPath }" />
		                    </span>
	                    </div>
						<div class="tuijianImage">
							推荐头像${pageContext.request.contextPath }/images/headImgs/${user.srcImgPath}
							<img alt="" src="${pageContext.request.contextPath }/images/headImgs/${user.srcImgPath}">
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 引入bottom.jsp -->
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/bottom.jsp" />
	</div>
</body>
</html>