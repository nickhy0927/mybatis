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
            	code: { required:true, maxlength:200},
            	name: { required:true, maxlength:200},
            	roleId: { required:true, maxlength:200},
            	remark: { required:true, maxlength:200},
            	frozen: { required:true, maxlength:200},
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.closeLoading();
                    $.openLoading('正在保存角色信息，请稍等...');
                    $.ajax({
                        url: '${basePath}/platform/role/role-update.json',
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
        function removeIframe(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        var zNodes = ${roleList};
        function showTree() {
            showMenu();
        }
        function zTreeOnClick(event, treeId, treeNode) {
            $("#roleName").val(treeNode.name);
            $("#roleId").val(treeNode.id);
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
            var cityObj = $("#roleId");
            var cityOffset = $("#roleId").offset();
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
    </script>
[/@htmlHead]
[@htmlBody]
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
            <input type="hidden" name="id" id="id" class="input-text" value="${role.id}" placeholder="please enter id">
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		角色编号 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="code" id="code" class="input-text" value="${role.code}" placeholder="please enter code">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		角色名称 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value="${role.name}" placeholder="please enter name">
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		上级角色 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" onfocus="showTree()" onclick="showTree()"
                           name="roleName" id="roleName" class="input-text" value="${role.roleName!''}" placeholder="请选择上级角色">
                    <input type="hidden" name="roleId" id="roleId" class="input-text" value="${role.roleId!''}">
                    <ul style="display: none" id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>是否冻结：
                </label>
                <div class="formControls col-xs-3 col-sm-3 skin-minimal">
                    <div class="radio-box">
                        <input name="locked" [#if role.frozen == 1]checked="checked"[/#if] value="1" type="radio" id="locked-1" checked>
                        <label for="locked-1">未冻结</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" value="0" [#if role.frozen == 0]checked="checked"[/#if] id="locked-2" name="locked">
                        <label for="locked-2">冻结</label>
                    </div>
                </div>
            </div>
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                		角色描述 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="remark" id="remark" class="input-text" value="${role.remark}" placeholder="please enter remark">
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