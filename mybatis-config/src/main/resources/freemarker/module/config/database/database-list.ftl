<#include '../../../common/header.ftl' >
<@htmlHead>
    <script type="text/javascript">
        function database_add() {
        	var ids = $('#tableList').getCheckedValue();
        	var array = $('#tableList').getCheckedObjArray();
        	console.log(array);
        	console.log(ids);
            //$.openWindow('新增数据库', '80%', '400px', '${basePath}/template//database/database-create.do');
        }

        function databaseViewDetail(id) {
            $.openWindow('查看数据库表', '100%', '100%', '${basePath}/template/database/database-detail-list.do?id=' + id);
        }
        
        function datadel(id, single) {
	        $.datadel({
	        	
	        }, 2);
        }

        $(document).ready(function () {
            $("#dataGridList").dataGrid({
                url: ctx + '/template/database/database-list.json',
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
                    {field: 'connectUrl', className: 'text-l', description: '数据库连接地址'},
                    {field: 'driverClassName', className: 'text-l', description: '数据库驱动', sort: true},
                    {field: 'databaseName', className: 'text-l', description: '数据库名称', sort: true},
                    {field: 'username', className: 'text-l', description: '数据库用户名', sort: true},
                    {field: 'password', className: 'text-l', description: '数据库密码', sort: true},
                    {field: 'operate', className: 'text-c', description: '操作', paramFormatter: function (row) {
                        return "<a href=\"#\" title=\"修改\" onclick=\"data_edit('" + row.id + "')\">"
                                + "<i class=\"Hui-iconfont\">&#xe60c;</i>"
                             + "</a>&nbsp;&nbsp;"
                             + "<a href=\"#\" title=\"查看数据库表\" onclick=\"databaseViewDetail('" + row.id + "')\">"
                                + "<i class=\"Hui-iconfont\">&#xe725;</i>"
                             + "</a>&nbsp;&nbsp;"
                            + "<a href=\"#\" title=\"删除\" onclick=\"datadel('" + row.id + "',true)\">"
                                + "<i class=\"Hui-iconfont\">&#xe609;</i>"
                                + "</a>";
                    }}
                ]
            });
        })
    </script>
</@htmlHead>
<@htmlBody>
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span> 模板管理
        <span class="c-gray en">&gt;</span> 数据库列表
        <a class="btn btn-refresh radius r" style="line-height:1.6em;margin-top:3px"
           href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont">&#xe68f;</i>
        </a>
    </nav>
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
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
                <a href="javascript:;" onclick="database_add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增事务</a>
            </span>
        </div>
        <div class="mt-20">
            <table id="dataGridList" class="table table-border table-bordered table-hover table-bg table-sort"></table>
        </div>
    </div>
</@htmlBody>