[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <style type="text/css">
        ul.ztree {
            margin-top: 0px;
            margin-left: 15px;
            margin-rigth: 15px;
            border: 1px solid #617775;
            background: #f0f6e4;
            width: auto;
            height: auto;
            overflow-y: scroll;
            overflow-x: auto;
        }
    </style>
    <script type="text/javascript">
        var zNodes = ${menuList};
        var setting = {
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "ps", "N": "s"}
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        var zTree;

        var items = '${defaultValue}';
        $(document).ready(function () {
            zTree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            if (items) {
                items = eval('(' + items + ')');
                console.log(items);
                $.each(items, function (index, item) {
                    var node = zTree.getNodeByParam("id", item.menuId);//根据ID找到该节点
                    zTree.checkNode(node, true, true);
                });
            }

            $('#submitBtn').on('click', function () {
                $.openTip('确定要保存权限吗？', false, function () {
                    var nodes = zTree.getCheckedNodes(true);
                    var menuIds = [];
                    for (var i = 0; i < nodes.length; i++) {
                        menuIds.push(nodes[i].id);
                    }
                    console.log(menuIds);
                    $.openLoading("正在保存权限信息，请稍候...");
                    $.ajax({
                        method: 'POST',
                        dataType: 'JSON',
                        url: '${basePath}/platform/rolemenu/role-menu-save.json',
                        data: {
                            roleId: '${role.id}',
                            menuIds: menuIds.join(",")
                        },
                        success: function (res) {
                            $.closeLoading();
                            if (res.code == 200) {
                                $.openTip(res.msg, true, function () {
                                    $.closeLoading();
                                    window.parent.initData();
                                });
                            } else {
                                $.openTip(res.msg, true);
                                return;
                            }
                        }, error: function (err) {
                            $.closeLoading();
                            $.openTip("保存权限失败,请稍候再试.", true);
                            return;
                        }
                    })
                })
            })

        });

        //取消
        function removeIframe() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    </script>
[/@htmlHead]
[@htmlBody]
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-4">
                    <span class="c-red">*</span> 当前角色：
                </label>
                <div class="formControls col-xs-5 col-sm-5">
                    <input type="text" readonly="readonly" name="roleName" id="roleName" class="input-text"
                           value="${role.name}" placeholder="请输入国际化编码">
                    <input type="hidden" readonly="readonly" name="roleId" id="roleId" class="input-text"
                           value="${role.id}"
                           placeholder="">
                </div>
            </div>
            <div class="row cl" style="height: 75%;overflow-y: auto">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
            <div class="row cl">
                <div class="col-xs-7 col-sm-8">
                    <button class="btn btn-primary radius" type="button" id="submitBtn">&nbsp;&nbsp;保存&nbsp;&nbsp;
                    </button>
                    <button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                </div>
            </div>
        </form>
    </article>
[/@htmlBody]