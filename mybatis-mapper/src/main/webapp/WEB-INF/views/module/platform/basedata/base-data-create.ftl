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
        var zNodes = ${baseDataList};
        function showTree() {
            showMenu();
        }
        function zTreeOnClick(event, treeId, treeNode) {
            $("#baseDataName").val(treeNode.name);
            $("#baseDataId").val(treeNode.id);
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
            var cityObj = $("#baseDataId");
            var cityOffset = $("#baseDataId").offset();
            $("#treeDemo").css({left:cityOffset.left+"px",top:cityOffset.top+cityObj.outerHeight()+"px"}).slideDown("fast");
        }
        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            $("body").bind("mousedown", function (event) {
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
            $.validator.addMethod("sort", function(value, element) {
                var reg = /^(\d{1,7})$/;
                return this.optional(element) || reg.test(value);
            }, "请输入7位以内的纯数字");

            //表单验证
            $.validation('addForm', {
            	code: { required:true, maxlength:200},
            	name: { required:true, maxlength:200},
                sort: { required:true,sort: true},
            	val: { required:true, maxlength:200},
            	remark: { required:true, maxlength:200},
            	enable: { required:true}
            }, function () {
                $.openTip('你确定要基础数据保存吗？',false ,function() {
                    $.closeLoading();
                    $.openLoading('正在保存基础数据信息，请稍等...');
                    $.ajax({
                        url: '${basePath}/platform/basedata/base-data-save.json',
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
                		字典编号 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="code" id="code" class="input-text" value="${code}" placeholder="请输入字典编号">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		字典名称 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value=""
                           placeholder="请输入字典名称">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		字典值 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="val" id="val" class="input-text"
                           value="" placeholder="请输入字典值">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>上级菜单：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <div class="input-append">
                        <input type="text" name="baseDataName" id="baseDataName" class="input-text device_select"
                               onfocus="showTree()" onclick="showTree()" readonly="readonly" value=""
                               placeholder="请选择上级字典">
                        <input name="baseDataId" type="hidden" id="baseDataId">
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
                        <input type="radio" value="0" id="enable-2" name="enable">
                        <label for="sex-2">停用</label>
                    </div>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    字典排序 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="sort" id="sort" class="input-text"
                           value="" placeholder="请输入排序值">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    信息备注 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value=""
                           placeholder="请输入字典备注说明">
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