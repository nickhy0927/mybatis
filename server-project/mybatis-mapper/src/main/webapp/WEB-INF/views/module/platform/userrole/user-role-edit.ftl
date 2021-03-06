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
            	status: { required:true, maxlength:200},
            	userId: { required:true, maxlength:200},
            	roleId: { required:true, maxlength:200},
            	id: { required:true, maxlength:200},
            	createTime: { required:true, maxlength:200},
            	updateTime: { required:true, maxlength:200},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.openLoading("正在保存数据，请稍等...");
                    $.submitAjax("${basePath}", {
                        method: 'POST',
                        dataType: 'JSON',
                        url: ctx + '/platform/userrole/user-role-update.json'
                    },$("#addForm").serialize(), function (result) {
                        if (result.code == 200) {
                            $.openTip(result.msg ,true ,function() {
                                parent.window.location.href = ctx + '/platform/userrole/user-role-list.do';
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
                		有效状态1 有效 0 无效 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="status" id="status" class="input-text" value="${userRole.status}" placeholder="please enter status">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		用户ID ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="userId" id="userId" class="input-text" value="${userRole.userId}" placeholder="please enter userId">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		角色ID：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="roleId" id="roleId" class="input-text" value="${userRole.roleId}" placeholder="please enter roleId">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="id" id="id" class="input-text" value="${userRole.id}" placeholder="please enter id">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="createTime" id="createTime" class="input-text" value="${userRole.createTime}" placeholder="please enter createTime">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="updateTime" id="updateTime" class="input-text" value="${userRole.updateTime}" placeholder="please enter updateTime">
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