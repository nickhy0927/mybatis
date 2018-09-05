<#include '../../../common/header.ftl'>
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
                ip:{ required:true, maxlength:200},
                driverClassName:{ required:true,maxlength:100},
                databaseName:{ required:true,maxlength:100},
                mysqldumpPath:{ required:true,maxlength:100},
                params:{ required:true,maxlength:100},
                databaseType:{ required:true,maxlength:100},
                username:{ required:true},
                password:{ required:true,maxlength:50},
            }, function () {
                $.openTip('你确定要保存数据库吗？',false ,function() {
                    $.closeLoading();
                    $.openLoading('正在保存数据库信息，请稍等...');
                    $.ajax({
                        url: '${basePath}/config/database/database-save.json',
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
</@htmlHead>
<@htmlBody>
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库连接地址：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="ip" id="ip" class="input-text" value="" placeholder="请输入数据库连接地址">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库名称：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="databaseName" id="databaseName" class="input-text" value="" placeholder="请输入数据库名称">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库驱动名称：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="driverClassName" id="driverClassName" class="input-text" value="" placeholder="请输入数据库驱动名称">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库端口：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="port" id="port" class="input-text" value="" placeholder="请输入数据库端口">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库用户名：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="username" id="username" class="input-text" value="" placeholder="请输入数据库用户名">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>连接数据库密码：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="password" name="password" id="password" class="input-text" value="" placeholder="请输数据库密码">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>导出命令目录：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="mysqldumpPath" id="mysqldumpPath" class="input-text" value="" placeholder="请输入mysqldump命令目录">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>连接数据库参数：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="params" id="params" class="input-text" value="" placeholder="请输入连接数据库参数">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>是否可用：</label>
                <div class="formControls col-xs-3 col-sm-3 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="used" id="base-1" value="1" />
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="base-2" name="used" value="0" checked="checked">
                        <label for="base-2" style="margin-left: 20px;">否</label>
                    </div>
                </div>
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库类型：</label>
                <div class="formControls col-xs-3 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="databaseType" id="databaseType-1" value="1" />
                        <label for="base-1" style="margin-left: 20px;">mysql</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="databaseType-2" name="databaseType" value="2" checked="checked">
                        <label for="base-2" style="margin-left: 20px;">oracle</label>
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