<#include "../../../common/header.ftl">
<@htmlHead>
    <script type="text/javascript">
        function create() {
            $.openWindow('新增基础数据', '80%', '70%', "${basePath}/platform/basedata/base-data-create.do");
        }
        
        function edit() {
            $.openWindow('修改基础数据', '80%', '70%', "${basePath}/platform/basedata/base-data-edit/"+ id +".do");
        }
        
        function del(id, single) {
            $.datadel({
            	url: "${basePath}/platform/basedata/base-data-edit/"+ id +".do",
                type:"post",
                data:{id: id},
                success:function(data){
                    if(data.code == 200) {
                    	$.openTip(data.msg, true, function () {
                    		initData();
                    	});
                    } else {
                        $.openTip(data.msg, true);
                        return;
                    }
                },
                error:function(e){
                    $.openTip('删除基础数据失败', true);
                    return;
                }
            }, single)
        }
		function initData() {
			$("#dataGridList").dataGrid({
                url: ctx + '/platform/basedata/base-data-list.json',
                title: '基础数据管理列表',
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
					{field: 'code', className: 'text-l', description: '字典编号', sort: true},
					{field: 'name', className: 'text-l', description: '字典名称', sort: true},
					{field: 'val', className: 'text-l', description: '字典值', sort: true},
					{field: 'enable', className: 'text-l', description: '是否启用', sort: true, paramFormatter: function (row) {
                        return row.enable == 1 ? '启用': '停用';
                    }},
                    {field: 'remark', className: 'text-l', description: '信息备注', sort: true},
                    {field: 'sysParamsId', className: 'text-l', description: '上级字典'},
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
</@htmlHead>
<@htmlBody>
	<nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span> 基础数据管理
        <span class="c-gray en">&gt;</span> 基础数据列表
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                    <li>字典名称：
                        <input type="text" name="name" id="name" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                    </li>
                    <li>属性值：
                        <input type="text" name="val" id="val" class="input-text" style="width:auto;"  placeholder="输入数据库名称">
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