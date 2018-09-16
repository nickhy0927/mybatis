<%@page import="java.util.Date"%>
<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.common.page/core" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<page:extends name="title">单点登录系统</page:extends>
<page:extends name="css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/wopop/style_log.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/wopop/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/wopop/userpanel.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/jquery-ui/jquery-ui.min.css">
    <style type="text/css">
    	.code_img {
    		float: right;
    		height: 35px;
    		width: 100px;
    	}
    </style>
</page:extends>
<page:extends name="javascript">
	<script type="text/javascript" src="${ctx}/static/assets/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/static/assets/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript">  
		/* console.log('${url}') */
		var _topWin = window;    
		while (_topWin != _topWin.parent.window) {    
		     _topWin = _topWin.parent.window;    
		}    
		if (window != _topWin)_topWin.document.location.href = '${ctx}//user/login.do?url=${url}';    
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
	            open: function (event, ui) {
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
    		$("#code_img").attr('src','${ctx}/auth/authCode.image?rmd=' + rmd);
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
</page:extends>
<page:extends name="body">
	<div class="logo">
		 <img src="${ctx}/static/assets/images/header-logo.png" width="280" height="80">
	</div>
	<div id="submitDialog" style="display: none;">
	 	<img style="height: 30px;" src="${ctx}/static/assets/jquery/loading_circle_40b82ef.gif">
	 	&nbsp;&nbsp;<span>正在操作，请稍候...</span>
	</div>
    <form method="post" id="loginForm" action="${ctx}/login">
    	<div class="login_m">
		    <div class="login_boder">
		    	<div style="margin-left:15px;float:left;padding:10px 20px 15px 10px;color:#000;font-size:25px;">
		    		<span>${title}</span>
		    	</div>
		        <div class="login_padding" id="login_model">
			        <dl class="login-list">
		              <dt class="ico-user"></dt>
		              <dd>
		                <span>登录账户：</span>
		                <input type="text" id="username" name="username" class="inputstyle" value="${users.username}" placeholder="登录账户">
		              </dd>
		            </dl>
		            <dl class="login-list">
		              <dt class="ico-pass"></dt>
		              <dd>
		                <span>登录密码：</span>
		                <input type="password" name="password" id="password" class="inputstyle" value="${users.password}" placeholder="登录密码">
		              </dd>
		            </dl>
		            <dl class="login-list">
		              <dt class="ico-code"></dt>
		              <dd><span>验证码：</span>
		                <input type="input" placeholder="验证码"  id="valid_code" name="valid_code" class="valid_code" autocomplete="off">
		                <a style="cursor: pointer;" title="看不清，点击换一张"  onclick="changeCode()"><img  onclick="changeCode()" id="code_img" class="code_img" src="${ctx}/auth/authCode.image?rmd=<%=new Date().getTime()%>"> </a>
		              </dd>
		            </dl>
		            <dl class="login-list">
		              <dt><input type="checkbox" onclick="rememberme()" value="1" checked="" name="remember_login_name" autocomplete="off"></dt>
		              <dd><span style="border:none;">记住用户名</span></dd>
		            </dl>
					<div class="btn-box">
		            	<input type="submit" style="cursor: pointer;" name="login_btn" id="login_btn" value="登录后台" class="loginbtnfocus" autocomplete="off">
		            </div>

		            <div class="rem_sub" style="padding-top:10px;width: 100%;margin-top: 59px;text-align: center;color: red;">
		                <span id="loginErr"></span>
		            </div>
		        </div>
		    </div>
	    </div>
    </form>
</page:extends>
<jsp:include page="/base/parent.jsp"></jsp:include>