<#include "/common/header.ftl">
<@htmlHead>
    <script type="text/javascript">
        function removeIframe() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        var settings = {
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: deviceTypeOnClick
            }
        };

        function deviceTypeOnClick(e, treeId, treeNode) {
            // var zTree = $.fn.zTree.getZTreeObj("deviceTypeTree");
            // var nodes = zTree.getSelectedNodes();
            // $(".selectDevTypeid").val(nodes[0].id);
            // $("#selectDevType").val(nodes[0].name);
        }

        showDevTypeTree();
        function showDevTypeTree() {
            $.ajax({
                url: ctx + '/platform/menu/query-menu.json',
                type: 'POST',
                data: {
                    menuId: ""
                },
                async: false,
                success: function (msg) {
                    var obj = eval("(" + msg + ")");
                    var deviceTypeNodes = [];
                    getDevTypeObj(obj, deviceTypeNodes);
                    console.log(deviceTypeNodes);
                    $.fn.zTree.init($("#deviceTypeTree"), settings, deviceTypeNodes);
                    var deptObj = $("#selectDevType");
                    var deptOffset = $("#selectDevType").offset();
                    /* $("#devTreeContent").css({
                        left: deptOffset.left + "px",
                        top: deptOffset.top + deptObj.outerHeight() + "px"
                    }).slideDown("fast"); */
                    $('#deviceTypeTree').css({width: deptObj.outerWidth() - 12 + "px"});
                    var zTree = $.fn.zTree.getZTreeObj("deviceTypeTree");
                    var node = zTree.getNodeByParam("id", $('.selectDevTypeid').val(), null);
                    zTree.selectNode(node);
                    $("body").bind("mousedown", onBodyDownByDevType);
                }
            });
        }

        function getDevTypeObj(dataObj, treeNodes) {
            for (var i = 0; i < dataObj.length; i++) {
                treeNodes.push({
                    id: dataObj[i].id,
                    pId: dataObj[i].pId,
                    name: dataObj[i].name
                });
                loadChildDevTypeObj(dataObj[i], treeNodes);
            }
        }

        function loadChildDevTypeObj(dataObj, treeNodes) {
            var childObj = dataObj.children;
            for (var j = 0; j < childObj.length; j++) {
                treeNodes.push({
                    id: childObj[j].id,
                    pId: childObj[j].menuId,
                    name: childObj[j].name
                });
                loadChildDevTypeObj(childObj[j], treeNodes);
            }
        }

        function onBodyDownByDevType(event) {
            if (event.target.id.indexOf('switch') == -1) {
                hideDeviceTypeMenu();
            }
        }
        function hideDeviceTypeMenu() {
            $("#devTreeContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDownByDevType);
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

            //è¡¨åéªè¯
            $.validation('addForm', {
                code: {required: true, maxlength: 200},
                name: {required: true, maxlength: 200},
                url: {required: true, maxlength: 200},
                alias: {required: true, maxlength: 200},
                enable: {required: true, maxlength: 200},
                shows: {required: true, maxlength: 200},
                remark: {required: true, maxlength: 200},
            }, function () {
            	$.openTip('你确定要保存吗？',false ,function() {
                    $.openLoading("正在保存数据，请稍等...");
                    $.submitAjax("${basePath}", {
                        method: 'POST',
                        dataType: 'JSON',
                        url: '/platform/menu/menu-save.json'
                    }, $("#addForm").serialize(), function (result) {
                        if (result.code === 200) {
                            $.openTip(result.msg, true, function () {
                                window.parent.initData();
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            });
                        } else {
                            $.openTip(result.msg, true, function () {
                                $.closeLoading();
                            });
                        }
                    }, function (err) {
                        console.log(err);
                        $.openTip("保存数据失败，请稍后再试.", true, function () {
                            $.closeLoading();
                        });
                    })
                })
            });
        });
    </script>
</@htmlHead>
<@htmlBody>
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>菜单编号:
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" readonly="readonly" name="code" id="code" class="input-text" value="${code}">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span> 菜单名称:
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value="" placeholder="请输入菜单名称">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>访问地址:</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="url" id="url" class="input-text" value="" placeholder="请输入菜单地址">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>菜单别名:
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="alias" id="alias" class="input-text" value="" placeholder="请输入菜单别名">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>上级菜单:
               	</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="menuId" id="menuId" class="input-text device_select"
                           onfocus="showDevTypeTree()" onclick="showDevTypeTree()" readonly="readonly" value=""
                           placeholder="请选择上级菜单">
                    <input type="hidden" class="selectDevTypeid">
                    <div id="devTreeContent" class="menuContent"
                         style="display: none; position: absolute; border: 1px #CCC solid; background-color: #F0F6E4;">
                        <ul id="deviceTypeTree" class="ztree" style="margin-top:0;"></ul>
                    </div>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>是否启用:
                </label>
                <div class="formControls col-xs-3 col-sm-3 skin-minimal">
                    <div class="radio-box">
                        <input name="enable" value="1" type="radio" id="enable-1" checked>
                        <label for="sex-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" value="2" id="enable-2" name="enable">
                        <label for="sex-2">否</label>
                    </div>
                </div>
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                   	是否显示:
                </label>
                <div class="formControls col-xs-3 col-sm-4 skin-minimal">
                    <div class="radio-box">
                        <input name="shows" value="1" type="radio" id="shows-1" checked>
                        <label for="sex-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" value="2" id="shows-2" name="shows">
                        <label for="sex-2">否</label>
                    </div>
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
</@htmlBody>