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
            //表单验证
            $.validation('addForm', {
            	id: {required:true},
            	createTime: {required:true},
            	updateTime: {required:true},
            	status: {required:true},
            	title: {required:true},
            	content: {required:true},
            	categoryId: {required:true},
            	userId: {required:true},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.closeLoading();
                    $.openLoading("正在保存数据，请稍等...");
                    $.ajax({
                        url: '${basePath}/deploy/news/news-save.json',
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
                		新闻标题 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="title" id="title" class="input-text" value="" placeholder="please enter title">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		新闻内容 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="content" id="content" class="input-text" value="" placeholder="please enter content">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		栏目ID ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="categoryId" id="categoryId" class="input-text" value="" placeholder="please enter categoryId">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		新闻创建人ID：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="userId" id="userId" class="input-text" value="" placeholder="please enter userId">
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