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
                connectUrl:{ required:true, maxlength:200},
                driverClassName:{ required:true,maxlength:100},
                databaseName:{ required:true,maxlength:100},
                username:{ required:true},
                password:{ required:true,maxlength:50},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.openLoading("正在保存数据，请稍等...");
                    $.submitAjax('${basePath}', {
                        method: 'POST',
                        dataType: 'JSON',
                        url: '/template/database/database-save.json'
                    },$("#addForm").serialize(), function (result) {
                        if (result.code == 200) {
                            $.openTip(result.msg ,true ,function() {
                                parent.window.location.href = ctx + '/template/database/database-list.do';
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
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库连接地址：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="connectUrl" id="connectUrl" class="input-text" value="" placeholder="请输入数据库连接地址">
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
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库用户名：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="username" id="username" class="input-text" value="" placeholder="请输入数据库用户名">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>数据库密码：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="password" name="password" id="password" class="input-text" value="" placeholder="请输数据库密码">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>是否可用：</label>
                <div class="formControls col-xs-9 col-sm-9 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="used" id="base-1" value="1" />
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="base-2" name="used" value="0" checked="checked">
                        <label for="base-2" style="margin-left: 20px;">否</label>
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