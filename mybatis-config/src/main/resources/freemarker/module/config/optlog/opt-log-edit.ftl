[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <script type="text/javascript">
        $(document).ready(function () {
            $('.skin-minimal input').iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
            $.validation('addForm', {
            	id: { required:true, maxlength:200},
            	createTime: { required:true, maxlength:200},
            	updateTime: { required:true, maxlength:200},
            	status: { required:true, maxlength:200},
            	username: { required:true, maxlength:200},
            	userId: { required:true, maxlength:200},
            	message: { required:true, maxlength:200},
            	method: { required:true, maxlength:200},
            	clazz: { required:true, maxlength:200},
            	optType: { required:true, maxlength:200},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.closeLoading();
                    $.openLoading("正在保存数据，请稍等...");
                    $.ajax({
                        url: '${basePath}/config/optlog/opt-log-update.json',
                        method: 'POST',
                        dataType: 'JSON',
                        data: $("#addForm").serialize(),
                        success: function (data) {
                            $.closeLoading();
                            if (data.status != 200 && data.code != 200) {
                                $.openTip(data.msg, true);
                                return;
                            }
                            $.openTip(data.msg, true, function () {
                                window.parent.initData();
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            });
                        },
                        error: function (err) {
                            $.closeLoading();
                            $.openTip(err, true);
                            return;
                        }
                    });
                });
            });
        });
        //取消
        function removeIframe(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    </script>
[/@htmlHead]
[@htmlBody]
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		操作人名字 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="username" id="username" class="input-text" value="${optLog.username}" placeholder="please enter username">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		操作人ID ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="userId" id="userId" class="input-text" value="${optLog.userId}" placeholder="please enter userId">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		操作信息 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="message" id="message" class="input-text" value="${optLog.message}" placeholder="please enter message">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		操作信息 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="method" id="method" class="input-text" value="${optLog.method}" placeholder="please enter method">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		操作类名称 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="clazz" id="clazz" class="input-text" value="${optLog.clazz}" placeholder="please enter clazz">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		操作类名称：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="optType" id="optType" class="input-text" value="${optLog.optType}" placeholder="please enter optType">
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
[/@htmlBody]