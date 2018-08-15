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
            	id: { required:true, maxlength:200},
            	createTime: { required:true, maxlength:200},
            	updateTime: { required:true, maxlength:200},
            	status: { required:true, maxlength:200},
            	code: { required:true, maxlength:200},
            	name: { required:true, maxlength:200},
            	val: { required:true, maxlength:200},
            	remark: { required:true, maxlength:200},
            	enable: { required:true, maxlength:200},
            	sysParamsId: { required:true, maxlength:200},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.openLoading("正在保存数据，请稍等...");
                    $.submitAjax("${basePath}", {
                        method: 'POST',
                        dataType: 'JSON',
                        url: ctx + '/platform/basedata/base-data-save.json'
                    },$("#addForm").serialize(), function (result) {
                        if (result.code == 200) {
                            $.openTip(result.msg ,true ,function() {
                                parent.window.location.href = ctx + '/platform/basedata/base-data-list.do';
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
                		主键ID：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="id" id="id" class="input-text" value="" placeholder="please enter id">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		新增时间：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="createTime" id="createTime" class="input-text" value="" placeholder="please enter createTime">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		修改时间：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="updateTime" id="updateTime" class="input-text" value="" placeholder="please enter updateTime">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		有效状态：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="status" id="status" class="input-text" value="" placeholder="please enter status">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		字典编号 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="code" id="code" class="input-text" value="" placeholder="please enter code">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		字典名称 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value="" placeholder="please enter name">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		字典值 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="val" id="val" class="input-text" value="" placeholder="please enter val">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		信息备注 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value="" placeholder="please enter remark">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		是否启用 1 启用  0 停用 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="enable" id="enable" class="input-text" value="" placeholder="please enter enable">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		系统参数ID：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="sysParamsId" id="sysParamsId" class="input-text" value="" placeholder="please enter sysParamsId">
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