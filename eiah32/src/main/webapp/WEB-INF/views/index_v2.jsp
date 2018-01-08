<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EIAH</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.1/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.5.1/jquery.easyui.min.js"></script>  
    <!-- 自定义样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
    <script type="text/javascript">
	    $(function(){
			$('#cc').layout();
			setHeight();
		});
	    
	    function setHeight(){
			var c = $('#cc');
			var p = c.layout('panel','center');	// get the center panel
			var oldHeight = p.panel('panel').outerHeight();
			p.panel('resize', {height:'auto'});
			var newHeight = p.panel('panel').outerHeight();
			c.layout('resize',{
				height: (c.height() + newHeight - oldHeight)
			});
		}
    </script>
</head>  
<body>  
<div id="cc" style="width:100%;height:700px;" >
		<div data-options="region:'north'" style="height: 40px;background-color:#95B8E7;">  
            <span style="margin-left:0.5%;">  
                <font size="3" style="line-height: 2.0em;" color="white" >  
                    <b>EIAH </b>  
                </font>  
            </span>  
              
            <span style="float:right;margin-top:10px;margin-right:20px;">  
                <font size="3" color="white">欢迎 :Admin</font>      
                <a href="/loginOut.do" > <font size="3" color="red">退出</font> </a>  
            </span>  
        </div> 
		<div data-options="region:'south'" style="height:50px;"></div>
		<div data-options="region:'west',split:true" title="菜单栏" style="width: 150px;">  
            <!-- <div class="easyui-accordion"  data-options="fit:true,border:false"></div> -->  
      		<div class="easyui-accordion" style="/* width:500px;height:300px; */">
				<div data-options="iconCls:'icon-search',collapsed:false,collapsible:false" style="padding:10px;">
					<input name="search" class="easyui-searchbox" prompt="请输入要查询的名称" style="width:100%;height:25px;">
				</div>
				<div title="用户管理" style="padding:10px" data-options="selected true">
					<a href="#">添加用户</a><br/>
					<a href="#">查询所有用户</a>
				</div>
				<div title="Title2" style="padding:10px">
					<p>Content2</p>
				</div>
			</div>
        </div>
		<div data-options="region:'center'" style="padding:20px">
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.</p>
			<p>Panel Content.12</p>
		</div>
	</div>
</body>
</html>