<#include "../../../common/header.ftl">
<@htmlHead>
    <script type="text/javascript">
        function create() {
            $.openWindow('创建模板', '80%', '80%', "${basePath}/menu/menu-create.do");
        }

        $(document).ready(function () {
            $("#dataGridList").dataGrid({
                url: ctx + '/menu/menu-list.json',
                title: 'Menu管理列表',
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
					{field: 'id', className: 'text-l', description: '主键ID', sort: true},
					{field: 'createTime', className: 'text-l', description: '新增时间', sort: true},
					{field: 'updateTime', className: 'text-l', description: '修改时间', sort: true},
					{field: 'status', className: 'text-l', description: '有效状态', sort: true},
					{field: 'code', className: 'text-l', description: '菜单编号 ', sort: true},
					{field: 'name', className: 'text-l', description: '菜单名称 ', sort: true},
					{field: 'url', className: 'text-l', description: '菜单地址 ', sort: true},
					{field: 'alias', className: 'text-l', description: '菜单别名 ', sort: true},
					{field: 'menuId', className: 'text-l', description: '上级菜单 ', sort: true},
					{field: 'enable', className: 'text-l', description: '是否启用 1 启用 0 停用 ', sort: true},
					{field: 'shows', className: 'text-l', description: '是否显示 1 显示 0 隐藏 ', sort: true},
					{field: 'remark', className: 'text-l', description: '信息备注', sort: true},
                    {field: 'operate', className: 'text-c', description: '操作', paramFormatter: function (row) {
                        return "<a href=\"#\" title=\"修改\" onclick=\"edit('" + row.id + "')\">"
                                + "<i class=\"Hui-iconfont\">&#xe60c;</i>"
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
        <span class="c-gray en">&gt;</span> Menu管理
        <span class="c-gray en">&gt;</span> Menu列表
        <a class="btn btn-refresh radius r" style="line-height:1.6em;margin-top:3px"
           href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont">&#xe68f;</i>
        </a>
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                    <li>数据库名称：
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
                <a href="javascript:;" onclick="create()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增</a>
            </span>
        </div>
        <div class="mt-20">
            <table id="dataGridList" class="table table-border table-bordered table-hover table-bg table-sort"></table>
        </div>
    </div>
</@htmlBody>