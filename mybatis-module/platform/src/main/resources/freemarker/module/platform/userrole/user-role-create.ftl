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
        var zNodes = ${roleTrees};
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
                    var node = zTree.getNodeByParam("id", item.roleId);//根据ID找到该节点
                    zTree.checkNode(node, true, true);
                });
            }

            $('#submitBtn').on('click', function () {
                $.openTip('${MessageResources.getMessage("UserRole.save.confirm")}', false, function () {
                    var nodes = zTree.getCheckedNodes(true);
                    var roleIds = [];
                    for (var i = 0; i < nodes.length; i++) {
                        roleIds.push(nodes[i].id);
                    }
                    $.openLoading('${MessageResources.getMessage("UserRole.save.loading")}');
                    $.ajax({
                        method: 'POST',
                        dataType: 'JSON',
                        url: '${basePath}/platform/userrole/user-role-save.json',
                        data: {
                            roleIds: roleIds.join(","),
                            userId: '${user.id}'
                        },
                        success: function (res) {
                            $.closeLoading();
                            if (res.code == 200) {
                                $.openTip(res.msg, true, function () {
                                    window.parent.initData();
                                    $.closeLoading();
                                });
                            } else {
                                $.openTip(res.msg, true);
                                return;
                            }
                        }, error: function (err) {
                            $.closeLoading();
                            $.openTip('${MessageResources.getMessage("UserRole.save.fail")}', true);
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
                <label class="form-label col-xs-4 col-sm-3">
                    <span class="c-red">*</span> 当前用户：
                </label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" readonly="readonly" name="username" class="input-text"
                           value="${user.realName}">
                    <input type="hidden" name="userId" value="${user.id}">
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