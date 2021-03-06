<#include '/common/header.ftl'>
<@htmlHead>
    <link rel="stylesheet" type="text/css" href="${basePath}/assets/lib/bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="${basePath}/assets/lib/bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
        table caption {
            height: 40px;
            line-height: 40px;
            border-top: 1px solid #ddd;
            border-left: 1px solid #ddd;
            border-right: 1px solid #ddd;
            text-align: left;
            font-size: 12px;
            padding: 2px 10px;
        }
        table td {
            font-size: 12px;
            padding: 2px 10px;
        }

        table {
            margin-bottom: 20px;
            border-collapse: collapse !important;
        }
        .operate {
            width: 50px;
            text-align: center !important;
            padding-top: 12px !important;
            font-size: 20px !important;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {

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
            <table class="table table-bordered">
                <caption>${MessageResources.getMessage("database.create.page.lable.create.caption")}</caption>
                <tr>
                    <td>${MessageResources.getMessage("database.create.page.lable.show.text")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.select.field")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.field.type")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.default.value")}</td>
                    <td style="text-align: center">${MessageResources.getMessage("database.create.page.option")}</td>
                </tr>
                <tr>
                    <td>
                        <input type="text" class="form-control">
                    </td>
                    <td>
                        <select class="form-control">
                            <option value="">请选择</option>
                            <#list columnList as column>
                                <#if column_index == 0>
                                    <#assign i = column.javaProperty >
                                </#if>
                                <option value="${column.javaProperty}">${column.javaType}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <select class="form-control">
                            <option value="">请选择</option>
                            <#list columnList as column>
                                <option value="${column.javaProperty}">${column.remarks?default(column.javaProperty)}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="form-control">
                    </td>
                    <td class="operate">
                        <i class="icon Hui-iconfont">&#xe604;</i>
                    </td>
                </tr>
            </table>
            <table class="table table-bordered">
                <caption>${MessageResources.getMessage("database.create.page.lable.edit.caption")}</caption>
                <tr>
                    <td>${MessageResources.getMessage("database.create.page.lable.show.text")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.select.field")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.field.type")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.default.value")}</td>
                    <td style="text-align: center">${MessageResources.getMessage("database.create.page.option")}</td>
                </tr>
                <tr>
                    <td>
                        <input type="text" class="form-control">
                    </td>
                    <td>
                        <select class="form-control">
                            <option value="">请选择</option>
                            <#list columnList as column>
                                <option value="${column.javaProperty}">${column.javaType}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <select class="form-control">
                            <option value="">请选择</option>
                            <#list columnList as column>
                                <option value="${column.javaProperty}">${column.remarks?default(column.javaProperty)}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="form-control">
                    </td>
                    <td class="operate">
                        <i class="icon Hui-iconfont">&#xe604;</i>
                    </td>
                </tr>
            </table>
            <table class="table table-bordered">
                <caption>${MessageResources.getMessage("database.create.page.lable.list.caption")}</caption>
                <tr>
                    <td>${MessageResources.getMessage("database.create.page.lable.show.text")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.select.field")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.field.type")}</td>
                    <td>${MessageResources.getMessage("database.create.page.lable.default.value")}</td>
                    <td style="text-align: center">${MessageResources.getMessage("database.create.page.option")}</td>
                </tr>
                <tr>
                    <td>
                        <input type="text" class="form-control">
                    </td>
                    <td>
                        <select class="form-control">
                            <option value="">请选择</option>
                            <#list columnList as column>
                                <option value="${column.javaProperty}">${column.javaType}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <select class="form-control">
                            <option value="">请选择</option>
                            <#list columnList as column>
                                <option value="${column.javaProperty}">${column.remarks?default(column.javaProperty)}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="form-control">
                    </td>
                    <td class="operate">
                        <i class="icon Hui-iconfont">&#xe604;</i>
                    </td>
                </tr>
            </table>
            <div class="row cl">
                <div class="col-xs-7 col-sm-8 col-xs-offset-2 col-sm-offset-2">
                    <button class="btn btn-primary radius" type="submit">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
                    <button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                </div>
            </div>
        </form>
    </article>
</@htmlBody>