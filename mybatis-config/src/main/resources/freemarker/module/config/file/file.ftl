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
                projectPath:{ required:true, maxlength:64},
                packages:{ required:true,maxlength:100},
                entityName:{ required:true,maxlength: 100},
                sqlMapperPath:{ required:true,maxlength:50},
                baseDir:{ required:true,maxlength:100},
                target:{ required:true,maxlength:50},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.openLoading("正在保存数据，请稍等...");
                    $.submitAjax('${basePath}', {
                        method: 'POST',
                        dataType: 'JSON',
                        url: '/template/database/${id}/make-template.json'
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
            <input value="${tableName}" type="hidden" name="tableName" id="tableName">
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2">
                    <span class="c-red">*</span>
                    项目路径：
                </label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" name="projectPath" id="projectPath" class="input-text" value="" placeholder="请输入项目路径">
                </div>
                <label class="form-label col-xs-2 col-sm-2">
                    <span class="c-red">*</span>
                    包名称：
                </label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" name="packages" id="packages" class="input-text" value="" placeholder="请输入包路径">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>实体名称：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" name="entityName" id="entityName" class="input-text" value="" placeholder="请输入实体名称">
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>sql文件路径：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" name="sqlMapperPath" id="sqlMapperPath" class="input-text" value="" placeholder="请输入sql文件路径">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>页面基础目录：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" name="baseDir" id="baseDir" class="input-text" value="" placeholder="请输入页面基础目录">
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>页面文件目录：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" name="target" id="target" class="input-text" value="" placeholder="请输入页面文件目录">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>是否重新生成：</label>
                <div class="formControls col-xs-4 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="overwrite" id="overwrite-1" value="1" checked="checked"/>
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="overwrite-2" name="overwrite" value="0">
                        <label for="base-2" style="margin-left: 20px;">否</label>
                    </div>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>是否生成实体：</label>
                <div class="formControls col-xs-4 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="entitywrite" id="entitywrite-1" value="1"  checked="checked"/>
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="entitywrite-2" name="entitywrite" value="0">
                        <label for="base-2" style="margin-left: 20px;">否</label>
                    </div>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>是否生成dao：</label>
                <div class="formControls col-xs-4 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="daowrite" id="daowrite-1" value="1" checked="checked"/>
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="daowrite-2" name="daowrite" value="0">
                        <label for="base-2" style="margin-left: 20px;">否</label>
                    </div>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>是否生成service：</label>
                <div class="formControls col-xs-4 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="servicewrite" id="servicewrite-1" value="1"  checked="checked"/>
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="servicewrite-2" name="servicewrite" value="0">
                        <label for="base-2" style="margin-left: 20px;">否</label>
                    </div>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>是否生成controller：</label>
                <div class="formControls col-xs-4 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="controllerwrite" id="controllerwrite-1" value="1" checked="checked"/>
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="controllerwrite-2" name="controllerwrite" value="0">
                        <label for="base-2" style="margin-left: 20px;">否</label>
                    </div>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>是否生成列表页面：</label>
                <div class="formControls col-xs-4 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="list" id="list-1" value="1"  checked="checked"/>
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="list-2" name="list" value="0">
                        <label for="base-2" style="margin-left: 20px;">否</label>
                    </div>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>是否生成新增页面：</label>
                <div class="formControls col-xs-4 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="create" id="create-1" value="1" checked="checked"/>
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="create-2" name="create" value="0">
                        <label for="base-2" style="margin-left: 20px;">否</label>
                    </div>
                </div>
                <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>是否生成编辑页面：</label>
                <div class="formControls col-xs-4 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input type="radio" name="edit" id="edit-1" value="1"  checked="checked"/>
                        <label for="base-1" style="margin-left: 20px;">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="edit-2" name="edit" value="0">
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