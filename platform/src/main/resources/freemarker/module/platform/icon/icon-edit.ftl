<#include "/common/header.ftl">
<@htmlHead>
    <style type="text/css">
        ul.ztree {
            margin-top: 0px;
            border: 1px solid #617775;
            background: #f0f6e4;
            width: auto;
            height: auto;
            overflow-y: scroll;
            overflow-x: auto;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.skin-minimal input').iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
            $.validator.addMethod("num", function (value, element) {
                var reg = /^(\d{1,7})$/;
                return this.optional(element) || reg.test(value);
            }, "请输入7位以内的纯数字");

            //表单验证
            $.validation('addForm', {
                code: {required: true, maxlength: 200},
                name: {required: true, maxlength: 200},
                url: {required: true, maxlength: 200},
                alias: {required: true, maxlength: 200},
                sort: {required: true, num: true},
                enable: {required: true, maxlength: 200},
                shows: {required: true, maxlength: 200},
                remark: {required: true, maxlength: 200}
            }, function () {
                $.openTip('你确定要保存吗？', false, function () {
                    $.closeLoading();
                    $.openLoading('正在保存图标信息，请稍等...');
                    $.ajax({
                        url: '${basePath}/platform/icon/icon-update.json',
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
        function removeIframe() {
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
                    图标名称 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value="${icon.name!''}"
                           placeholder="请输入图标名称">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    图标属性 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="attr" id="attr" class="input-text" value="${icon.attr!''}" placeholder="请输入图标属性">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    图标备注 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value="${icon.remark!''}"
                           placeholder="请输入图标备注">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    是否启用：
                </label>
                <div class="formControls col-xs-3 col-sm-3 skin-minimal">
                    <div class="radio-box">
                        <input name="enable" <#if icon.enable == 1>checked="checked"</#if> value="1" type="radio"
                               id="enable-1" checked>
                        <label for="sex-1">启用</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" <#if menu.enable == 0>checked="checked"</#if> value="0" id="enable-2"
                               name="enable">
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
</@htmlBody>