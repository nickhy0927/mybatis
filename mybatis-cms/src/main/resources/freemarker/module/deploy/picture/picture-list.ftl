[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <script type="text/javascript">
        function create() {
            $.parentOpenWindow('上传图片', '100%', '100%', "${basePath}/deploy/picture/picture-create.do");
        }
        
        function edit(id) {
            $.openWindow('修改模板', '80%', '80%', "${basePath}/deploy/picture/picture-edit/"+ id +".do");
        }
        
        function del(id, single) {
            $.datadel({
            	url: "${basePath}/deploy/picture/picture-delete/"+ id +".do",
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
                url: ctx + '/deploy/picture/picture-list.json',
                title: 'Picture管理列表',
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
                    {field: 'userId', className: 'text-l', description: '上传人ID ', sort: true, paramFormatter: function (row) {
                        return row.userId
                    }},
                    {field: 'attachmentId', className: 'text-l', description: '附件ID ', sort: true, paramFormatter: function (row) {
                        return row.attachmentId
                    }},
                    {field: 'sort', className: 'text-l', description: '排序 ', sort: true, paramFormatter: function (row) {
                        return row.sort
                    }},
                    {field: 'remaker', className: 'text-l', description: '图片备注 ', sort: true, paramFormatter: function (row) {
                        return row.remaker
                    }},
                    {field: 'url', className: 'text-l', description: '访问连接', sort: true, paramFormatter: function (row) {
                        return row.url
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
        <span class="c-gray en">&gt;</span> Picture管理
        <span class="c-gray en">&gt;</span> Picture列表
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                     <li>上传人 ：
                         <input type="text" name="realName" id="realName"
                                class="input-text" style="width:auto;" placeholder="输入上传人姓名">
                         <input type="text" name="userId" id="userId">
                     </li>
                     <li>图片备注 ：
                         <input type="text" name="remaker" id="remaker" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
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