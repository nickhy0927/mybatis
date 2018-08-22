[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead title="菜单列表"]
    <script type="text/javascript">
        function create() {
            $.openWindow('新增图标', '80%', '60%', "${basePath}/platform/icon/icon-create.do");
        }
        
        function edit(id) {
            $.openWindow('修改图标', '80%', '80%', "${basePath}/platform/icon/icon-edit/"+ id +".do");
        }
        
        function del(id, single) {
            $.datadel({
            	url: "${basePath}/platform/icon/icon-delete/"+ id +".json",
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
                    // alert("错误！！");
                    console.log(e);
                    $.openTip('删除菜单信息失败，请稍后再试.', true, function () {
                        initData();
                    })
                }
            }, single)
        }
		function initData() {
			$("#dataGridList").dataGrid({
                url: ctx + '/platform/icon/icon-list.json',
                title: '图标管理列表',
                method: 'POST',
                checkbox: true,
                pageSize: 6,
                orderField: 'createTime',
                sort: 'desc',
                searchButtonId: '#searchButton',
                queryParamsId: ['#name', "#attr"],
                tableId: '#dataGridList',
                columns: [
                    {field: 'id', className: 'text-c'},
                    {field: 'name', className: 'text-l', description: '图标名称 ', sort: true},
                    {field: 'attr', className: 'text-l', description: '图标属性 ', sort: true},
                    {field: 'enable', className: 'text-l', description: '是否启用 ', sort: true, paramFormatter: function (row) {
                            return row.enable == 1 ? '启用' : '停用';
                        }},
                    {field: 'remark', className: 'text-c', description: '图标备注'},
                    {field: 'operate', className: 'text-c', description: '操作', paramFormatter: function (row) {
                        return "<a href=\"#\" title=\"修改\" onclick=\"edit('" + row.id + "')\">"
                                + "<i class=\"Hui-iconfont\">&#xe60c;</i>"
                             + "</a>&nbsp;&nbsp;"
                             + "<a href=\"#\" title=\"删除\" onclick=\"del('" + row.id + "', 'true')\">"
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
        <span class="c-gray en">&gt;</span> 菜单管理
        <span class="c-gray en">&gt;</span> 菜单管理列表
        <a class="btn btn-refresh radius r" style="line-height:1.6em;margin-top:3px"
           href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont">&#xe68f;</i>
        </a>
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                    <li>菜单名称：
                        <input type="text" name="name" id="name" class="input-text" style="width:auto;" placeholder="输入菜单名称">
                    </li>
                    <li>菜单地址：
                        <input type="text" name="url" id="url" class="input-text" style="width:auto;"  placeholder="输入菜单地址">
                    </li>
                    <li>菜单别名：
                        <input type="text" name="alias" id="alias" class="input-text" style="width:auto;"
                               placeholder="输入菜单别名">
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