[#ftl encoding="utf-8" strict_syntax=true]
[#include "/common/header.ftl"]
[@htmlHead]
    <script type="text/javascript">
        function create() {
            $.openWindow('创建模板', '80%', '80%', "${basePath}/platform/attachment/attachment-create.do");
        }
        
        function edit(id) {
            $.openWindow('修改模板', '80%', '80%', "${basePath}/platform/attachment/attachment-edit/"+ id +".do");
        }
        
        function del(id, single) {
            $.datadel({
            	url: "${basePath}/platform/attachment/attachment-delete/"+ id +".do",
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
                url: ctx + '/platform/attachment/attachment-list.json',
                title: 'Attachment管理列表',
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
                    {field: 'name', className: 'text-l', description: '附件的名称 ', sort: true, paramFormatter: function (row) {
                        return row.name
                    }},
                    {field: 'path', className: 'text-l', description: '附件上传的路径 ', sort: true, paramFormatter: function (row) {
                        return row.path
                    }},
                    {field: 'fileType', className: 'text-l', description: '附件的类型 ', sort: true, paramFormatter: function (row) {
                        return row.fileType
                    }},
                    {field: 'fileSize', className: 'text-l', description: '附件的大小 ', sort: true, paramFormatter: function (row) {
                        return row.fileSize
                    }},
                    {field: 'fileName', className: 'text-l', description: '附件原始名称 ', sort: true, paramFormatter: function (row) {
                        return row.fileName
                    }},
                    {field: 'suffix', className: 'text-l', description: '附件原始名称', sort: true, paramFormatter: function (row) {
                        return row.suffix
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
        <span class="c-gray en">&gt;</span> Attachment管理
        <span class="c-gray en">&gt;</span> Attachment列表
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
                     <li>附件的名称 ：
                         <input type="text" name="name" id="name" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>附件上传的路径 ：
                         <input type="text" name="path" id="path" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>附件的类型 ：
                         <input type="text" name="fileType" id="fileType" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>附件的大小 ：
                         <input type="text" name="fileSize" id="fileSize" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>附件原始名称 ：
                         <input type="text" name="fileName" id="fileName" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                     <li>附件原始名称：
                         <input type="text" name="suffix" id="suffix" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
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