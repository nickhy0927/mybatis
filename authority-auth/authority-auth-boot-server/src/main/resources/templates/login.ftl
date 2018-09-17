<#assign basePath = request.contextPath />
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Language" content="zh_CN"/>
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/static/assets/wopop/style_log.css">
	    <link rel="stylesheet" type="text/css" href="${basePath}/static/assets/wopop/style.css">
	    <link rel="stylesheet" type="text/css" href="${basePath}/static/assets/wopop/userpanel.css">
	    <link rel="stylesheet" type="text/css" href="${basePath}/static/assets/jquery-ui/jquery-ui.min.css">
	    <style type="text/css">
	    	.code_img {
	    		float: right;
	    		height: 35px;
	    		width: 100px;
	    	}
	    </style>
	    <script type="text/javascript" th:src="${basePath}/static/assets/jquery/jquery.js"></script>
		<script type="text/javascript" src="${basePath}/static/assets/jquery-ui/jquery-ui.js"></script>
		<script type="text/javascript">  
			/* console.log('${basePath}url}') */
			var _topWin = window;    
			while (_topWin != _topWin.parent.window) {    
			     _topWin = _topWin.parent.window;    
			}    
			if (window != _topWin)_topWin.document.location.href = '${basePath}/user/login.do';    
		    function open() {
				$("#submitDialog").dialog({
					closeOnEscape: false,
		            width: 250,
		            dialogClass: "no-close",  
		            height: 100,
		            resizable: false,
		            draggable: false,
		            modal: true,
		            //隐藏默认的关闭按钮
		            open: function () {
		                $(".ui-dialog-titlebar-close", $(this).parent()).hide();
		                $(".ui-dialog-titlebar").remove();
		                $("#submitDialog").attr('style','width: auto;min-height: 0px;max-height: none;height: 30px;color:white')
		                $("#submitDialog").parent().css('background',"#969494");
		                $(".ui-widget-content").parent().css('width',"auto");
		            }
				});
			}
	    	$(function () {
	   			$("#login_btn").click(function () {
	   				open();
				});
	   			setInterval(function () {
	   				$("#loginErr").html('');
	   			}, 10000);
			});
	    	function changeCode() {
	    		var rmd = new Date().getTime();
	    		$("#code_img").attr('src','${basePath}/auth/authCode.image?rmd=' + rmd);
			}
	    	
	    	function rememberme() {
	    		var rmb = $("input[type='checkbox']").is(':checked');
	    		if(rmb) {
	    			$("input[type='checkbox']").val(1);
	    		} else {
	    			$("input[type='checkbox']").val(0)
	    		}
			}
	    </script>
	</head>
	<body>
		<div class="logo">
			 <img src="${basePath}/static/assets/images/header-logo.png" width="280" height="80">
		</div>
		<div id="submitDialog" style="display: none;">
		 	<img style="height: 30px;" src="${basePath}/static/assets/jquery/loading_circle_40b82ef.gif">
		 	&nbsp;&nbsp;<span>正在操作，请稍候...</span>
		</div>
	    <form method="post" id="loginForm" action="/login">
	    	<div class="login_m">
			    <div class="login_boder">
			    	<div style="margin-left:15px;float:left;padding:10px 20px 15px 10px;color:#000;font-size:25px;">
			    		<span>用户登录</span>
			    	</div>
			        <div class="login_padding" id="login_model">
				        <dl class="login-list">
			              <dt class="ico-user"></dt>
			              <dd>
			                <span>登录账户：</span>
			                <input type="text" id="username" name="username" class="inputstyle" value="" placeholder="登录账户">
			              </dd>
			            </dl>
			            <dl class="login-list">
			              <dt class="ico-pass"></dt>
			              <dd>
			                <span>登录密码：</span>
			                <input type="password" name="password" id="password" class="inputstyle" value="" placeholder="登录密码">
			              </dd>
			            </dl>
			            <dl class="login-list">
			              <dt class="ico-code"></dt>
			              <dd><span>验证码：</span>
			                <input type="input" placeholder="验证码"  id="valid_code" name="valid_code" class="valid_code" autocomplete="off">
			                <a style="cursor: pointer;" title="看不清，点击换一张"  onclick="changeCode()"><img  onclick="changeCode()" id="code_img" class="code_img" src="${basePath}/auth/authCode.image"> </a>
			              </dd>
			            </dl>
			            <dl class="login-list">
			              <dt><input type="checkbox" onclick="rememberme()" value="1" checked="" name="remember_login_name" autocomplete="off"></dt>
			              <dd><span style="border:none;">记住用户名</span></dd>
			            </dl>
						<div class="btn-box">
			            	<input type="button" style="cursor: pointer;" name="login_btn" id="login_btn" value="开始登录系统" class="loginbtnfocus" autocomplete="off">
			            </div>
	
			            <div class="rem_sub" style="padding-top:10px;width: 100%;margin-top: 59px;text-align: center;color: red;">
			                <span id="loginErr"></span>
			            </div>
			        </div>
			    </div>
		    </div>
	    </form>
	</body>
</html>