[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <script type="text/javascript">
        function create() {
            $.openWindow('新增角色', '80%', '60%', "${basePath}/platform/role/role-create.do");
        }
        
        function edit(id) {
            $.openWindow('修改角色', '80%', '60%', "${basePath}/platform/role/role-edit/"+ id +".do");
        }
        
        function del(id, single) {
            $.datadel({
            	url: "${basePath}/platform/role/role-delete/"+ id +".json",
                type:"post",
                data:{id: id},
                success:function(data){
                    if(data.code == 200) {
                    	$.openTip(data.msg, true, function () {
                    		initData();
                    	});
                    }
                },
                error:function(e){
                    $.openTip('删除角色信息失败，请稍后再试.');
                }
            }, single)
        }
		function initData() {
			$("#dataGridList").dataGrid({
                url: ctx + '/platform/role/role-list.json',
                title: '角色管理列表',
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
					{field: 'code', className: 'text-l', description: '角色编号 ', sort: true},
					{field: 'name', className: 'text-l', description: '角色名称 ', sort: true},
					{field: 'roleName', className: 'text-l', description: '上级角色 ', sort: true},
					{field: 'remark', className: 'text-l', description: '信息备注 ', sort: true},
                    {field: 'frozen', className: 'text-l', description: '是否冻结', sort: true, paramFormatter: function (row) {
                            return row.frozen == 1 ? '未冻结' : '冻结';
                    }},
                    {field: 'operate', className: 'text-c', description: '操作', paramFormatter: function (row) {
                        return "<a href=\"#\" title=\"修改\" onclick=\"edit('" + row.id + "')\">"
                                + "<i class=\"Hui-iconfont\">&#xe60c;</i>"
                             + "</a>&nbsp;&nbsp;"
                             + "<a href=\"#\" title=\"删除\" onclick=\"del('" + row.id + "', true)\">"
                                + "<i class=\"Hui-iconfont\">&#xe609;</i>"
                             + "</a>";
                    }}
                ]
            });
		}
        $(document).ready(function () {
            initData();
        })
    </script>
[/@htmlHead]
[@htmlBody]
	<nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span> 角色管理
        <span class="c-gray en">&gt;</span> 角色列表
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                    <li>角色名称：
                        <input type="text" name="connectUrl" id="connectUrl" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                    </li>
                    <li>
                        <button type="button" class="btn btn-success radius" id="searchButton" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
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
[/@htmlBody]