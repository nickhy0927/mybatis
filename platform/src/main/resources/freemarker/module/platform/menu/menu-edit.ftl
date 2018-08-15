<#include "/common/header.ftl">
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
            	code: { required:true, maxlength:200},
            	name: { required:true, maxlength:200},
            	url: { required:true, maxlength:200},
            	alias: { required:true, maxlength:200},
            	enable: { required:true, maxlength:200},
            	shows: { required:true, maxlength:200},
            	remark: { required:true, maxlength:200},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.openLoading("正在保存数据，请稍等...");
                    $.submitAjax("${basePath}", {
                        method: 'POST',
                        dataType: 'JSON',
                        url: '/platform/menu/menu-update.json'
                    },$("#addForm").serialize(), function (result) {
                        if (result.code == 200) {
                            $.openTip(result.msg ,true ,function() {
                                parent.window.location.href = ctx + '/platform/menu/menu-list.do';
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
            <input type="hidden" name="id" id="id" class="input-text" value="${menu.id}" placeholder="please enter id">
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		菜单编号 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="code" id="code" class="input-text" value="${menu.code}" placeholder="please enter code">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		菜单名称 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value="${menu.name}" placeholder="please enter name">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		菜单地址 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="url" id="url" class="input-text" value="${menu.url}" placeholder="please enter url">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		菜单别名 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="alias" id="alias" class="input-text" value="${menu.alias}" placeholder="please enter alias">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		上级菜单 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="menuId" id="menuId" class="input-text" value="${menu.menuId}" placeholder="please enter menuId">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		是否启用：
                </label>
                <div class="formControls col-xs-3 col-sm-3 skin-minimal">
					<div class="radio-box">
						<input name="enable" <#if menu.enable == 1>checked="checked"</#if> value="1" type="radio" id="enable-1" checked>
						<label for="sex-1">启用</label>
					</div>
					<div class="radio-box">
						<input type="radio" <#if menu.enable == 2>checked="checked"</#if> value="2" id="enable-2" name="enable">
						<label for="sex-2">停用</label>
					</div>
				</div>
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		是否显示：
                </label>
                <div class="formControls col-xs-3 col-sm-4 skin-minimal">
					<div class="radio-box">
						<input name="shows" value="1" <#if menu.shows == 1>checked="checked"</#if> type="radio" id="shows-1" checked>
						<label for="sex-1">显示</label>
					</div>
					<div class="radio-box">
						<input type="radio" value="2" <#if menu.shows == 2>checked="checked"</#if> id="shows-2" name="shows">
						<label for="sex-2">隐藏</label>
					</div>
				</div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		信息备注：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value="${menu.remark}" placeholder="please enter remark">
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