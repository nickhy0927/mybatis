<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.common.page/core" prefix="page"%>
<page:extends name="title">新增数据库来源</page:extends>
<page:extends name="css">
	<style type="text/css"></style>
</page:extends>
<page:extends name="javascript">
	<script type="text/javascript">
		layui.use('form', function(){
		  	var form = layui.form;
		  	var $ = layui.jquery;
		  	$('form').css({
		  		'padding': 20
		  	})
		  	$('.layui-form-label').css({
		  		"width": '120px',
		  		'text-align': 'rigth'
		  	})
		  	$('.layui-input-block').css({
		  		'margin-left': $('.layui-form-label').width() + 50
		  	})
		  	//自定义验证规则
		  	form.verify({
		    	title: function(value){
			      	if(value.length < 5){
			        	return '标题也太短了吧';
			      	}
			    },
			    pass: [/(.+){6,12}$/, '密码必须6到12位'],
			    money: [
			      	/^\d+\.\b\d{2}\b$/
			      	,'金额必须为小数保留两位'
		    	]
			});
		  
		  	//初始赋值
		  	/* form.val('first', {
		    	'title': '测试', 
		    	'phone': 11111111111,
		    	'email': 'xu@sentsin.com',
		    	'password': 123123,
		    	'quiz': 2,
		    	'interest': 3,
		    	'like[write]': true,
		    	//,'open': false,
		    	'sex': '男',
		    	'desc': 'form 是我们非常看重的一块'
		  	}); */
		  	//事件监听
		  	form.on('select', function(data){
		    	console.log('select: ', this, data);
		  	});
		  
		  	form.on('select(quiz)', function(data){
		    	console.log('select.quiz：', this, data);
		  	});
		  
		  	form.on('select(interest)', function(data){
		    	console.log('select.interest: ', this, data);
		  	});
		  	form.on('checkbox', function(data){
		    	console.log(this.checked, data.elem.checked);
		  	});
		  
		  	form.on('switch', function(data){
		    	console.log(data);
		  	});
		  
		  	form.on('radio', function(data){
		    	console.log(data);
		  	});
		  	//监听提交
		  	form.on('submit(*)', function(data){
		    	console.log(data)
		    	alert(JSON.stringify(data.field));
		    	return false;
		  	});
		});
	</script>
</page:extends>
<page:extends name="body">
	<form class="layui-form layui-form-pane1" action="" lay-filter="first">
	  	<div class="layui-form-item">
	    	<label class="layui-form-label">数据库连接地址</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="title" lay-verify="required|title" required placeholder="请输入标题" autocomplete="off" class="layui-input">
	    	</div>
	  	</div>
  		<div class="layui-form-item">
    		<label class="layui-form-label">手机</label>
    		<div class="layui-input-block">
      			<input type="tel" name="phone" lay-verify="required|number" lay-verType="tips" autocomplete="off" class="layui-input">
    		</div>
  		</div>
	  	<div class="layui-form-item">
	    	<label class="layui-form-label">邮箱</label>
	    	<div class="layui-input-block">
	      		<input type="email" name="email" lay-verify="email"  lay-verType="alert" autocomplete="off" class="layui-input">
	   	 	</div>
  		</div>
	  	<div class="layui-form-item">
	    	<label class="layui-form-label">密码</label>
	    	<div class="layui-input-block">
	      		<input type="password" name="password" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
	    	</div>
	  	</div>
	  	<div class="layui-form-item">
	      	<label class="layui-form-label">行内表单</label>
	      	<div class="layui-input-block">
		        <select name="quiz" lay-verify="required" lay-verType="tips">
		          	<option value="">请选择问题</option>
		          	<option value="0">你工作的第一个城市</option>
		          	<option value="1" disabled>你的工号</option>
		          	<option value="2">你最喜欢的老师</option>
		        </select>
	      	</div>
	  	</div>
	  	<div class="layui-form-item" pane>
	    	<label class="layui-form-label">复选框</label>
	    	<div class="layui-input-block">
		      	<input type="checkbox" name="like[write]" title="写作">
		      	<input type="checkbox" name="like[read]" title="阅读">
		      	<input type="checkbox" name="like[game]" title="游戏" disabled>
	    	</div>
	  	</div>
	  	<div class="layui-form-item" pane>
	   	 	<label class="layui-form-label">原始复选框</label>
	    	<div class="layui-input-block">
	      		<input type="checkbox" name="like1[write]" lay-skin="primary" title="写作">
	      		<input type="checkbox" name="like1[read]" lay-skin="primary" title="阅读">
	      		<input type="checkbox" name="like1[game]" lay-skin="primary" title="游戏" disabled>
	    	</div>
	  	</div>
	  	<div class="layui-form-item" pane>
	    	<label class="layui-form-label">单选框</label>
	    	<div class="layui-input-block">
	      		<input type="radio" name="sex.id" value="男" title="男">
	      		<input type="radio" name="sex.id" value="女" title="女" checked>
	      		<input type="radio" name="sex.id" value="中型" title="中">
	   	 	</div>
	  	</div>
	  	<div class="layui-form-item">
	    	<div class="layui-input-block">
	      		<button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
	      		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    	</div>
	  	</div>
	</form>
</page:extends>
<jsp:include page="/parent/base.jsp" />