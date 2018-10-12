[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[#include "/common/fileUpload.ftl"]
[@htmlHead]
    <script type="text/javascript">
        $(document).ready(function () {
            $('.skin-minimal input').iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
            //表单验证
            $.validation('addForm', {
            	id: {required:true},
            	createTime: {required:true},
            	updateTime: {required:true},
            	status: {required:true},
            	userId: {required:true},
            	attachmentId: {required:true},
            	sort: {required:true},
            	remaker: {required:true},
            	url: {required:true},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.closeLoading();
                    $.openLoading("正在保存数据，请稍等...");
                    $.ajax({
                        url: '${basePath}/deploy/picture/picture-save.json',
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
            [@fileUpload][/@fileUpload]
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		上传人ID ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="userId" id="userId" class="input-text" value="" placeholder="please enter userId">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		附件ID ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="attachmentId" id="attachmentId" class="input-text" value="" placeholder="please enter attachmentId">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		排序 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="sort" id="sort" class="input-text" value="" placeholder="please enter sort">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		图片备注 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remaker" id="remaker" class="input-text" value="" placeholder="please enter remaker">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		访问连接：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="url" id="url" class="input-text" value="" placeholder="please enter url">
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