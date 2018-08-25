[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <script type="text/javascript">
        function create() {
            $.openWindow('新增用户信息', '90%', '95%', "${basePath}/platform/user/user-create.do");
        }
        
        function edit(id) {
            $.openWindow('修改用户信息', '80%', '80%', "${basePath}/platform/user/user-edit/"+ id +".do");
        }
        
        function del(id, single) {
            $.datadel({
            	url: "${basePath}/platform/user/user-delete/"+ id +".json",
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
                    $.openTip('删除用户信息失败，请稍后再试.');
                }
            }, single)
        }
		function initData() {
			$("#dataGridList").dataGrid({
                url: ctx + '/platform/user/user-list.json',
                title: '用户管理列表',
                method: 'POST',
                checkbox: true,
                pageSize: 6,
                orderField: 'createTime',
                sort: 'desc',
                searchButtonId: '#searchButton',
                queryParamsId: ['#realName', "#loginName"],
                tableId: '#dataGridList',
                columns: [
                    {field: 'id', className: 'text-c'},
					{field: 'realName', className: 'text-l', description: '真实姓名 ', sort: true},
					{field: 'loginName', className: 'text-l', description: '登录账号 ', sort: true},
					{field: 'locked', className: 'text-l', description: '是否锁定', sort: true, paramFormatter: function (row) {
                        return row.locked == 1 ? '锁定' : '未锁定';
                    }},
					{field: 'enable', className: 'text-l', description: '是否启用', sort: true, paramFormatter: function (row) {
					    return row.enable == 1 ? '启用' : '停用';
                    }},
					{field: 'position', className: 'text-c', description: '用户职位 '},
					{field: 'lastLoginTime', className: 'text-l', description: '最后一次登录时间', sort: true, paramFormatter: function (row) {}},
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
        <span class="c-gray en">&gt;</span> User管理
        <span class="c-gray en">&gt;</span> User列表
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                    <li>真实姓名：
                        <input type="text" name="realName" id="realName" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                    </li>
                    <li>登录账户：
                        <input type="text" name="loginName" id="loginName" class="input-text" style="width:auto;"  placeholder="输入数据库名称">
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
[/@htmlBody]