<#include "../../../common/header.ftl">
<@htmlHead>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.skin-minimal input').iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
            $.validator.addMethod("phone", function (value, element) {
                var reg = /!(/^1[34578]\d{9}$/;
                return this.optional(element) || reg.test(value);
            }, "请输入12位以内的纯数字号码");

            //表单验证
            $.validation('addForm', {
                realName: {required: true, maxlength: 200},
                loginName: {required: true, maxlength: 200},
                password: {required: true, maxlength: 200},
                locked: {required: true},
                enable: {required: true},
                email: {required: true, maxlength: 200},
                mobile: {required: true, phone: true},
                remark: {required: true, maxlength: 200},
                position: {required: true, maxlength: 200},
            }, function () {
                $.openTip('你确定要保存吗？', false, function () {
                    $.closeLoading();
                    $.openLoading('正在保存用户信息，请稍等...');
                    $.ajax({
                        url: '${basePath}/platform/user/user-save.json',
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
                })
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
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    真实姓名 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="realName" id="realName" class="input-text" value="" placeholder="请输入真是姓名">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    登录账号 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="loginName" id="loginName" class="input-text" value=""
                           placeholder="请输入登录账户">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    登录密码 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="password" id="password" class="input-text" value="" placeholder="请输入账户密码">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>是否锁定：
                </label>
                <div class="formControls col-xs-3 col-sm-3 skin-minimal">
                    <div class="radio-box">
                        <input name="locked" value="1" type="radio" id="locked-1" checked>
                        <label for="locked-1">锁定</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" value="0" id="locked-2" name="locked">
                        <label for="locked-2">解锁</label>
                    </div>
                </div>
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    是否启用：
                </label>
                <div class="formControls col-xs-3 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input name="enable" value="1" type="radio" id="enable-1" checked>
                        <label for="enable-1">启用</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" value="0" id="enable-2" name="enable">
                        <label for="enable-2">停用</label>
                    </div>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    电子邮箱 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="email" id="email" class="input-text" value="" placeholder="请输入电子邮箱">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    电话号码 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="mobile" id="mobile" class="input-text" value="" placeholder="请输入电话号码">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    用户职位 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="position" id="position" class="input-text" value="" placeholder="请输入用户职位">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    信息备注 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value="" placeholder="请输入用户备注信息">
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