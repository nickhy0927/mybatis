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
            //表单验证
            $.validation('addForm', {
                id: {required: true},
                createTime: {required: true},
                updateTime: {required: true},
                status: {required: true},
                name: {required: true},
                code: {required: true},
                categoryId: {required: true},
                type: {required: true},
            }, function () {
                $.openTip('你确定要保存栏目吗？', false, function () {
                    $.closeLoading();
                    $.openLoading("正在保存栏目，请稍等...");
                    $.ajax({
                        url: '${basePath}/deploy/category/category-update.json',
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
        function removeIframe() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
        var zNodes = ${catgoryList};
        console.log(zNodes)
        function showTree() {
            showMenu();
        }
        function zTreeOnClick(event, treeId, treeNode) {
            $("#categoryName").val(treeNode.name);
            $("#categoryId").val(treeNode.id);
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

        function showMenu() {
            var cityObj = $("#categoryId");
            var cityOffset = $("#categoryId").offset();
            $("#treeDemo").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");
        }

        function hideMenu() {
            $("#treeDemo").fadeOut("fast");
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

            var categoryList = ${SysConstant.getCategoryList()};
            debugger;
            console.log(categoryList);
            if (categoryList) {
                $.each(categoryList, function (index, obj) {
                    var options = "<option value='" + obj['value'] + "'>" + obj['text'] + "</option>";
                    if (obj['value'] == "${category.type!''}") {
                        options = "<option value='" + obj['value'] + "' selected='selected'>" + obj['text'] + "</option>";
                    }
                    $('select[name="type"]').append(options);
                })
            }
        });
    </script>
[/@htmlHead]
[@htmlBody]
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
            <input type="hidden" id="id" name="id" value="${category.id}">
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    栏目编号 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" readonly="readonly" name="code" id="code" class="input-text" value="${category.code}"
                           placeholder="请输入栏目编号">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    栏目名称 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="name" id="name" class="input-text" value="${category.name!''}" placeholder="请输入栏目名称">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    上级栏目 ：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" onfocus="showTree()" onclick="showTree()"
                           name="categoryName" id="categoryName" value="${category.categoryName!''}" class="input-text"
                           placeholder="请选择上级栏目">
                    <input type="hidden" name="categoryId" value="${category.categoryId!''}" id="categoryId">
                    <ul style="display: none" id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                    <span class="c-red">*</span>
                    栏目类型：
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <span class="select-box">
                        <select class="select" size="1" name="type">
                            <option value="" selected>请选择栏目类型</option>
                        </select>
                    </span>
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