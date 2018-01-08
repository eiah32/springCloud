// 加载
$(function() {
	// 登录注册切换
	switchLoginRegist();
	// 初始化布局页面高度
	initLayout();
	// 点击head.jsp头像显示个人信息
	showUserInfo();
	// 校验个人信息修改
	checkUserInfo();
	// 根据url加载图片
	var jcrop_api;
	uploadImgChange();
	// 重新选择图片
	reChooseImg();
	// 保存头像
	saveHeadImg();
});
// 保存头像
function saveHeadImg() {
	$(".saveImg").click(function() {
		if (typeof jcrop_api != 'undefined'){
			$("#imgForm").attr("action", "/uploadImg/uploadHeadImage");
//			$("#imgForm").submit();
			$('#imgForm').ajaxSubmit({
				success: function(data) {
					alert("服务器相应结果1" + data.state);
					alert("服务器相应结果2" + data.msg);
				}
			});
		}
//		$.ajaxFileUpload({
//			url : '/uploadImg/uploadHeadImage',
//			secureuri: false,
//            fileElementId: 'myHeadImage', //文件上传空间的id属性
//            dataType: 'JSON',
//            type: 'post',
//            data:{x:jcrop_api.x,y:jcrop_api.y,w:jcrop_api.w,h:jcrop_api.h/*,pw:boundx,ph:boundy*/},
//            success: function (data){  //服务器成功响应处理函数
//            	alert(data.results);
//                if(data.result){
//                    alert('成功');
//                }else{
//                    alert('失败');
//                }
//                window.location.reload();//刷新当前页面
//            }
//		});
	});
}

// 重新选择图片
function reChooseImg() {
	$('.reChoose').click(function() {
		$('#uploadImg').click();
	});
}

// 上传图片预览
function uploadImgChange() {
	$('#uploadImg').change(function(){
		$('#imgForm').ajaxSubmit({
			success: function(data) {
				// 文件上传成功，图片格式正确，且缩放至特定比例
				if (data.status == "success") {
					// 更新图片名称
					$('#myHeadImage').attr('src', '/tmpImage/' + data.message);
					$('#crop_preview').attr('src', '/tmpImage/' + data.message);
					// 销毁对象
					if (typeof jcrop_api != 'undefined')   
		                jcrop_api.destroy();
					// 加载图片
					$('#myHeadImage').Jcrop({
		        		setSelect: [120,60,170,170],
		        		boxWidth: 400, // 画布宽
		        		boxHeight: 300,
		        		onChange:showPreview,
		        		onSelect:showPreview,
		        		aspectRatio:1 // 选框宽高比 width/height
		        	}, function () {
		        		jcrop_api = this;
//		        		var bounds = jcrop_api.getBounds(); 
//		                boundx = bounds[0]; 
//		                boundy = bounds[1]; 
	                });
				} else {
					location.reload();
				}
			}
		});
	});
}

//简单的事件处理程序，响应自onChange,onSelect事件，按照上面的Jcrop调用
function showPreview(coords){
		jQuery('#myHeadImage').attr("src", jQuery('#crop_preview').attr("src"));
	$("#x").val(coords.x);
    $("#y").val(coords.y);
    $("#w").val(coords.w);
    $("#h").val(coords.h);
	if(parseInt(coords.w) > 0){
		//计算预览区域图片缩放的比例，通过计算显示区域的宽度(与高度)与剪裁的宽度(与高度)之比得到
		var rx = $("#preview_box").width() / coords.w; 
		var ry = $("#preview_box").height() / coords.h;
		//通过比例值控制图片的样式与显示
		$("#crop_preview").css({
			width:Math.round(rx * $("#myHeadImage").width()) + "px",	//预览图片宽度为计算比例值与原图片宽度的乘积
			height:Math.round(rx * $("#myHeadImage").height()) + "px",	//预览图片高度为计算比例值与原图片高度的乘积
			marginLeft:"-" + Math.round(rx * coords.x) + "px",
			marginTop:"-" + Math.round(ry * coords.y) + "px"
		});
	}
}
//校验个人信息修改
function checkUserInfo() {
	// 初始加载性别  
	var gender = $(".g-gender input[name='gender']:checked").val();
	
	// 手机
	var tel = null;
	$('#tel').click(function() {
		if (tel == null) {
			tel = $(this).val();
		}
	});
	
	$('#tel').blur(function() {
		if ($(this).val() == '' || $(this).val() == null) {
			$('#tel-tip .tip-ok').html('');
			$('#tel-tip .tip-error').html('');
			if ($(this).val() != tel) {
				$('.ui-content form').attr('onsubmit', 'return true');
			} else {
				$('.ui-content form').attr('onsubmit', 'return false');
			}
			return false;
		}
		var pattern = /^1[34578]\d{9}$/;
		if (pattern.test($(this).val())) {
			$('#tel-tip .tip-ok').html('恭喜您，合法的手机号');
			$('#tel-tip .tip-error').html('');
			if ($(this).val() != tel) {
				$('.ui-content form').attr('onsubmit', 'return true');
			} else {
				$('.ui-content form').attr('onsubmit', 'return false');
			}
		} else {
			$('.ui-content form').attr('onsubmit', 'return false');
			$('#tel-tip .tip-ok').html(''); 
			$('#tel-tip .tip-error').html('手机号格式不正确');
		}
	});
	
	// 性别
	$('.g-gender input').click(function() {
		if ($(this).val() != gender) {
			$('.ui-content form').attr('onsubmit', 'return true');
		} else {
			$('.ui-content form').attr('onsubmit', 'return false');
		}
	});
	
	// 生日
	var birthday = null;
	$('#birthday').click(function() {
		if (birthday == null) {
			birthday = $(this).val();
		}
	});
	
	$('#birthday').blur(function() {
		if ($(this).val() == '' || $(this).val() == null) {
			$('#birthday-tip .tip-ok').html('');
			$('#birthday-tip .tip-error').html('');
			if ($(this).val() != birthday) {
				$('.ui-content form').attr('onsubmit', 'return true');
			} else {
				$('.ui-content form').attr('onsubmit', 'return false');
			}
			return false;
		}
		if (new Date() >= new Date($(this).val())) {
			$('#birthday-tip .tip-ok').html('恭喜您，合法的生日');
			$('#birthday-tip .tip-error').html('');
			if ($(this).val() != birthday) {
				$('.ui-content form').attr('onsubmit', 'return true');
			} else {
				$('.ui-content form').attr('onsubmit', 'return false');
			}
		} else {
			$('.ui-content form').attr('onsubmit', 'return false');
			$('#birthday-tip .tip-ok').html(''); 
			$('#birthday-tip .tip-error').html('生日不能大于当前日期');
		}
	});
	
	// 邮箱
	var email = null;
	$('#email').click(function() {
		if (email == null) {
			email = $(this).val();
		}
	});
	
	$('#email').blur(function() {
		if ($(this).val() == '' || $(this).val() == null) {
			$('#email-tip .tip-ok').html('');
			$('#email-tip .tip-error').html('');
			if ($(this).val() != email) {
				$('.ui-content form').attr('onsubmit', 'return true');
			} else {
				$('.ui-content form').attr('onsubmit', 'return false');
			}
			return false;
		}
		var pattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		if (pattern.test($(this).val())) {
			$('#email-tip .tip-ok').html('恭喜您，合法的邮箱账号');
			$('#email-tip .tip-error').html('');
			if ($(this).val() != email) {
				$('.ui-content form').attr('onsubmit', 'return true');
			} else {
				$('.ui-content form').attr('onsubmit', 'return false');
			}
		} else {
			$('.ui-content form').attr('onsubmit', 'return false');
			$('#email-tip .tip-ok').html(''); 
			$('#email-tip .tip-error').html('邮箱格式不正确');
		}
	});
}

// 点击head.jsp头像显示个人信息
function showUserInfo() {
	var _this = null;
	$('.image').hover(function(){
		_this = $(this);
		_this.find('.nav-info').slideDown(300);
	}, function() {
		_this.find('.nav-info').hide();
	});
}

//登录注册切换
function switchLoginRegist(){
	$('.message a').click(
			function() {
				$('form').animate({
					height : "toggle",
					opacity : "toggle"
				}, "slow");
				$('.info h1').text() == "Login Form" ? $('.info h1').html('Regist Form') : $('.info h1').html('Login Form');
	});
};

//初始化布局页面高度
function initLayout(){
	var screenHeight = window.innerHeight;
	$('#cc').height()
};

// 注册校验用户名是否存在
function checkIsExist() {
    var username = $.trim($(".username").val());
    // TODO 判断是否为空
    $.ajax({  
        type:"POST",
        url:"/user/checkIsExist",
        data:"username="+username,
        dataType:"json",
        success:function(result) {
            if (result['tip'] == -1) {  
                $(".error p").html("<font color='red'>用户名已存在</font>");
            } else if (result['tip'] == 1) {
            	$(".error p").html("<font color='green'>恭喜您，合法的用户名</font>");
            } else if(result['tip'] == 0) {
            	$(".error p").html("<font color='red'>用户名不能为空</font>");
            }
        } 
    });
}

// 个人信息修改校验用户名是否存在
function checkUsernameIsExist(username) {
    var username = username;
    if (username ==null || username =='') {
		return false;
	}
    $.ajax({  
        type:"POST",
        url:"/user/checkIsExist",
        data:"username="+username,
        dataType:"json",
        success:function(result) {
            if (result['tip'] == -1) {
            	$('.ui-content form').attr('onsubmit', 'return false');
            	$('#username-tip .tip-ok').html('');
            	$('#username-tip .tip-error').html('用户名已存在');
            	return false;
            } else if (result['tip'] == 1) {
            	$('#username-tip .tip-ok').html('恭喜您，合法的用户名');
            	$('#username-tip .tip-error').html('');
            	return true;
            } else if(result['tip'] == 0) {
            	$('.ui-content form').attr('onsubmit', 'return false');
            	$('#username-tip .tip-ok').html('');
            	$('#username-tip .tip-error').html('用户名不能为空');
            	return false;
            }
        } 
    });
}

//返回顶部
$(window).scroll(function(e){
	goTop();
});
function goTop() {
	h = $(window).height();
    t = $(document).scrollTop();
    if(t > h){
        $('#gotop').show();
    }else{
        $('#gotop').hide();
    }
    $('#gotop').click(function(){
        $(document).scrollTop(0);
    });
}