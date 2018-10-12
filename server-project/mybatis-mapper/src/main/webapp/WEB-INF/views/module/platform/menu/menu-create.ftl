[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
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
        function removeIframe() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        var zNodes = ${menuList};
        function showTree() {
            showMenu();
        }
        function zTreeOnClick(event, treeId, treeNode) {
            $("#menuName").val(treeNode.name);
            $("#menuId").val(treeNode.id);
            $("#treeDemo").attr('style', "display:none;z-index:100;position：absolute").slideUp("fast");
        }
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: zTreeOnClick
            }
        };

        function showMenu(){
            var cityObj = $("#menuId");
            var cityOffset = $("#menuId").offset();
            $("#treeDemo").css({left:cityOffset.left+"px",top:cityOffset.top+cityObj.outerHeight()+"px"}).slideDown("fast");
        }
        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            $("body").bind("mousedown", function (event) {
                console.log(event.target.id);
                var reg = RegExp(/switch/);
                var target = event.target.id;
                if (!target.match(reg)) {
                    hideMenu();
                }
            });
        });

        function hideMenu() {
            $("#treeDemo").fadeOut("fast");
        }

        $(document).ready(function () {

            $('.skin-minimal input').iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
            $.validator.addMethod("phone", function (value, element) {
                var reg = /^(\d{1,12})$/;
                return this.optional(element) || reg.test(value);
            }, "输入的电话号码不正确");

            $.validation('addForm', {
                code: {required: true, maxlength: 200},
                name: {required: true, maxlength: 200},
                url: {required: true, maxlength: 200},
                alias: {required: true, maxlength: 200},
                enable: {required: true, maxlength: 200},
                localCode: {required: true, maxlength: 200},
                shows: {required: true, maxlength: 200},
                remark: {required: true, maxlength: 200}
            }, function () {
                $.openTip('你确定要保存吗？', false, function () {
                    $.closeLoading();
                    $.openLoading('正在保存菜单信息，请稍等...');
                    $.ajax({
                        url: '${basePath}/platform/menu/menu-save.json',
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
                    <span class="c-red">*</span>菜单编号：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" readonly="readonly" name="code" id="code" class="input-text" value="${code}">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span> 菜单名称：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value="" placeholder="请输入菜单名称">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span> 国际化编码：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="localCode" id="localCode" class="input-text" value="" placeholder="请输入国际化编码">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>访问地址：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="url" id="url" class="input-text" value="" placeholder="请输入菜单地址">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>菜单别名：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="alias" id="alias" class="input-text" value="" placeholder="请输入菜单别名">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>上级菜单：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <div class="input-append">
                        <input type="text" name="menuName" id="menuName" class="input-text device_select"
                               onfocus="showTree()" onclick="showTree()" readonly="readonly" value=""
                               placeholder="请选择上级菜单">
                        <input name="menuId" type="hidden" id="menuId">
                        <ul style="display: none" id="treeDemo" class="ztree"></ul>
                    </div>
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
                        <input type="radio" value="2" id="enable-2" name="enable">
                        <label for="sex-2">停用</label>
                    </div>
                </div>
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    是否显示：
                </label>
                <div class="formControls col-xs-3 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input name="shows" value="1" type="radio" id="shows-1" checked>
                        <label for="sex-1">显示</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" value="2" id="shows-2" name="shows">
                        <label for="sex-2">隐藏</label>
                    </div>
                </div>
            </div>
            [#--<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    是否主页显示：
                </label>
                <div class="formControls col-xs-9 col-sm-9 skin-minimal">
                    <div class="radio-box">
                        <input name="showIndex" value="1" type="radio" id="showIndex-1" checked>
                        <label for="sex-1">显示</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" value="2" id="showIndex-2" name="showIndex">
                        <label for="sex-2">隐藏</label>
                    </div>
                </div>
            </div>--]
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    菜单排序：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="sort" id="sort" class="input-text"
                           placeholder="请输入菜单顺序">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>菜单备注:
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value="" placeholder="请输入菜单备注信息">
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