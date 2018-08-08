<#include "../../../common/header.ftl">
<@htmlHead>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.skin-minimal input').iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
            $.validator.addMethod("phone", function(value, element) {
                var reg = /^(\d{1,12})$/;
                return this.optional(element) || reg.test(value);
            }, "请输入12位以内的纯数字号码");

            //表单验证
            $.validation('addForm', {
            	realName: { required:true, maxlength:200},
            	loginName: { required:true, maxlength:200},
            	password: { required:true, maxlength:200},
            	locked: { required:true, maxlength:200},
            	enable: { required:true, maxlength:200},
            	email: { required:true, maxlength:200},
            	mobile: { required:true, maxlength:200},
            	remark: { required:true, maxlength:200},
            	position: { required:true, maxlength:200},
            	lastLoginTime: { required:true, maxlength:200},
            	id: { required:true, maxlength:200},
            	createTime: { required:true, maxlength:200},
            	updateTime: { required:true, maxlength:200},
            	status: { required:true, maxlength:200},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.openLoading("正在保存数据，请稍等...");
                    $.submitAjax("${basePath}", {
                        method: 'POST',
                        dataType: 'JSON',
                        url: ctx + '/platform/user/user-update.json'
                    },$("#addForm").serialize(), function (result) {
                        if (result.code == 200) {
                            $.openTip(result.msg ,true ,function() {
                                parent.window.location.href = ctx + '/platform/user/user-list.do';
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            });
                        } else {
                            $.openTip(result.msg ,true, function() {
                                $.closeLoading();
                            });
                        }
                    },function (err) {
                        console.log(err);
                        $.openTip("信息保存失败" ,true, function() {
                            $.closeLoading();
                        });
                    })
                })
            });
        });
        //取消
        function removeIframe(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    </script>
</@htmlHead>
<@htmlBody>
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		真实姓名 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="realName" id="realName" class="input-text" value="${user.realName}" placeholder="please enter realName">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		登录账号 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="loginName" id="loginName" class="input-text" value="${user.loginName}" placeholder="please enter loginName">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		登录密码 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="password" id="password" class="input-text" value="${user.password}" placeholder="please enter password">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		是否锁定 1 锁定 0 未锁定 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="locked" id="locked" class="input-text" value="${user.locked}" placeholder="please enter locked">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		是否启用 1 启用 0 停用 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="enable" id="enable" class="input-text" value="${user.enable}" placeholder="please enter enable">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		电子邮箱 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="email" id="email" class="input-text" value="${user.email}" placeholder="please enter email">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		电话号码 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="mobile" id="mobile" class="input-text" value="${user.mobile}" placeholder="please enter mobile">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		信息备注 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value="${user.remark}" placeholder="please enter remark">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		用户职位 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="position" id="position" class="input-text" value="${user.position}" placeholder="please enter position">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		最后一次登录时间：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="lastLoginTime" id="lastLoginTime" class="input-text" value="${user.lastLoginTime}" placeholder="please enter lastLoginTime">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="id" id="id" class="input-text" value="${user.id}" placeholder="please enter id">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="createTime" id="createTime" class="input-text" value="${user.createTime}" placeholder="please enter createTime">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="updateTime" id="updateTime" class="input-text" value="${user.updateTime}" placeholder="please enter updateTime">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="status" id="status" class="input-text" value="${user.status}" placeholder="please enter status">
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-7 col-sm-8 col-xs-offset-2 col-sm-offset-2">
                    <button class="btn btn-primary radius" type="submit">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
                    <button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                </div>
            </div>
        </form>
    </article>
</@htmlBody>