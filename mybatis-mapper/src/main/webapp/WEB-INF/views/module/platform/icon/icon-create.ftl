[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <script type="text/javascript">
        function removeIframe() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        $(document).ready(function () {

            $.validation('addForm', {
                code: {required: true, maxlength: 200},
                name: {required: true, maxlength: 200},
                url: {required: true, maxlength: 200},
                alias: {required: true, maxlength: 200},
                enable: {required: true, maxlength: 200},
                shows: {required: true, maxlength: 200},
                remark: {required: true, maxlength: 200},
            }, function () {
                $.openTip('你确定要保存吗？', false, function () {
                    $.closeLoading();
                    $.openLoading('正在保存图标信息，请稍等...');
                    $.ajax({
                        url: '${basePath}/platform/icon/icon-save.json',
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
    </script>
[/@htmlHead]
[@htmlBody]
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span> 图标名称：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value="" placeholder="请输入菜单名称">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>图标属性：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="attr" id="attr" class="input-text" value="" placeholder="请输入菜单地址">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>图标备注：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value="" placeholder="请输入菜单别名">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>是否启用：
                </label>
                <div class="formControls col-xs-3 col-sm-3 skin-minimal">
                    <div class="radio-box">
                        <input name="enable" value="1" type="radio" id="enable-1" checked>
                        <label for="sex-1">启用</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" value="0" id="enable-2" name="enable">
                        <label for="sex-2">停用</label>
                    </div>
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