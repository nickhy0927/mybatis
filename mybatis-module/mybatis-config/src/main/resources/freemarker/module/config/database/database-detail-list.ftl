<#include '../../../common/header.ftl' >
<@htmlHead>
    <script type="text/javascript">
        function createTemplate(tableName) {
            $.openWindow('创建模板', '100%', '100%', '${basePath}/config/database/${id}/create-template.do?tableName=' + tableName);
        }
        function createPage(tableName) {
            $.parentOpenWindow('创建模板', '100%', '100%', '${basePath}/config/database/${id}/create-page.do?tableName=' + tableName);
        }

        $(document).ready(function () {
            $("#dataGridList").dataGrid({
                url: ctx + '/config/database/getDatabaseDetailInfo.json?id=${id}',
                title: '数据库管理列表',
                method: 'POST',
                checkbox: true,
                pageSize: 6,
                orderField: 'createTime',
                sort: 'desc',
                searchButtonId: '#searchButton',
                queryParamsId: ['#connectUrl', "#databaseName", "#username"],
                tableId: '#dataGridList',
                columns: [
                    {field: 'id', className: 'text-c'},
                    {field: 'tableName', className: 'text-l', description: '数据库连接地址'},
                    {field: 'tableComment', className: 'text-l', description: '数据库名称', sort: true},
                    {field: 'operate', className: 'text-c', description: '操作', paramFormatter: function (row) {
                        return "<a href=\"#\" title=\"创建后台\" onclick=\"createTemplate('" + row.tableName + "')\">"
                                + "<i class=\"Hui-iconfont\">&#xe60c;</i>"
                             + "</a>&nbsp;&nbsp;"
                             + "<a href=\"#\" title=\"创建页面\" onclick=\"createPage('" + row.tableName + "')\">"
                                + "<i class=\"Hui-iconfont\">&#xe6df;</i>"
                             + "</a>";
                    }}
                ]
            });
        })
    </script>
</@htmlHead>
<@htmlBody>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                    <li>
                        数据库名称：
                        <input type="text" name="connectUrl" id="connectUrl" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                    </li>
                    <li>数据库名称：
                        <input type="text" name="databaseName" id="databaseName" class="input-text" style="width:auto;"  placeholder="输入数据库名称">
                    </li>
                    <li>数据库用户名：
                        <input type="text" name="username" id="username" class="input-text" style="width:auto;"
                               placeholder="输入数据库用户名">
                    </li>
                    <li>
                        <button type="button" class="btn btn-success radius" id="searchButton" name=""><i
                                class="Hui-iconfont">&#xe665;</i> 搜索
                        </button>
                        <button type="reset" class="btn btn-danger radius" id="searchButton" name="">&nbsp;&nbsp; 重置&nbsp;&nbsp;</button>
                    </li>
                </ul>
            </div>
        </form>
        <div class="mt-20">
            <table id="dataGridList" class="table table-border table-bordered table-hover table-bg table-sort"></table>
        </div>
    </div>
</@htmlBody>