[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <script type="text/javascript">
        function create() {
            $.openWindow('创建模板', '80%', '80%', "${basePath}/config/optlog/opt-log-create.do");
        }
        
        function edit(id) {
            $.openWindow('修改模板', '80%', '80%', "${basePath}/config/optlog/opt-log-edit/"+ id +".do");
        }
        
        function del(id, single) {
            $.datadel({
            	url: "${basePath}/config/optlog/opt-log-delete/"+ id +".do",
                type: "post",
                data: {id: id},
                success:function(data){
                    if(data.code == 200) {
                    	$.openTip(data.msg, true, function () {
                    		initData();
                    	});
                    } else {
                        $.openTip(data.msg, true, function () {
                            $.closeLoading();
                        });
                        return;
                    }
                },
                error:function(){
                    $.openTip('删除信息出现异常，稍后再试.', true, function () {
                        $.closeLoading();
                    });
                    return;
                }
            }, single)
        }
		function initData() {
			$("#dataGridList").dataGrid({
                url: ctx + '/config/optlog/opt-log-list.json',
                title: 'OptLog管理列表',
                method: 'POST',
                checkbox: true,
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
                    {field: 'username', className: 'text-l', description: '操作人名字 ', sort: true, paramFormatter: function (row) {
                        return row.username
                    }},
                    {field: 'userId', className: 'text-l', description: '操作人ID ', sort: true, paramFormatter: function (row) {
                        return row.userId
                    }},
                    {field: 'message', className: 'text-l', description: '操作信息 ', sort: true, paramFormatter: function (row) {
                        return row.message
                    }},
                    {field: 'method', className: 'text-l', description: '操作信息 ', sort: true, paramFormatter: function (row) {
                        return row.method
                    }},
                    {field: 'clazz', className: 'text-l', description: '操作类名称 ', sort: true, paramFormatter: function (row) {
                        return row.clazz
                    }},
                    {field: 'optType', className: 'text-l', description: '操作类名称', sort: true, paramFormatter: function (row) {
                        return row.optType
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
        <span class="c-gray en">&gt;</span> OptLog管理
        <span class="c-gray en">&gt;</span> OptLog列表
        <a class="btn btn-refresh radius r" style="line-height:1.6em;margin-top:3px"
           href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont">&#xe68f;</i>
        </a>
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                     <li>主键ID：
                         <input type="text" name="id" id="id" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>新增时间：
                         <input type="text" name="createTime" id="createTime" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>修改时间：
                         <input type="text" name="updateTime" id="updateTime" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>有效状态：
                         <input type="text" name="status" id="status" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作人名字 ：
                         <input type="text" name="username" id="username" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作人ID ：
                         <input type="text" name="userId" id="userId" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作信息 ：
                         <input type="text" name="message" id="message" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作信息 ：
                         <input type="text" name="method" id="method" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作类名称 ：
                         <input type="text" name="clazz" id="clazz" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作类名称：
                         <input type="text" name="optType" id="optType" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
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