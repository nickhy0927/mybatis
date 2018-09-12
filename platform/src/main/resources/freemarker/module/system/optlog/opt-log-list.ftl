[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <script type="text/javascript">
        function del(id, single) {
            $.datadel({
            	url: "${basePath}/config/操作日志/opt-log-delete/"+ id +".do",
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
                title: '操作日志管理列表',
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
                        return row.username;
                    }},
                    {field: 'message', className: 'text-l', description: '操作信息 ', sort: true, paramFormatter: function (row) {
                        return row.message;
                    }},
                    {field: 'clazz', className: 'text-l', description: '操作类名称 ', sort: true, paramFormatter: function (row) {
                        return row.clazz;
                    }},
                    {field: 'optType', className: 'text-l', description: '操作类型', sort: true, paramFormatter: function (row) {
                        return row.optType == 1 ? '新增操作' : (row.optType == 2 ? '修改操作' : '删除操作')
                    }},
                    {field: 'operate', className: 'text-c', description: '操作', paramFormatter: function (row) {
                        return "<a href=\"#\" title=\"删除\" onclick=\"del('" + row.id + "', true)\">"
                                + "<i class=\"Hui-iconfont\">&#xe609;</i>"
                             + "</a>";
                    }}
                ],
                trClick: function (data) {
                    $.openWindow('查看日志详情', '90%', '95%', '${basePath}/config/optlog/opt-log-view/' + data.id +'.do');
                }
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
        <span class="c-gray en">&gt;</span> 操作日志管理
        <span class="c-gray en">&gt;</span> 操作日志列表
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                     <li>操作人名字 ：
                         <input type="text" name="username" id="username" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作信息 ：
                         <input type="text" name="message" id="message" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作方法 ：
                         <input type="text" name="method" id="method" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>操作类名称 ：
                         <input type="text" name="clazz" id="clazz" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
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
[/@htmlBody]