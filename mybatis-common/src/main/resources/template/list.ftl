[#ftl encoding="utf-8" strict_syntax=true]
${r'[#ftl encoding="utf-8" strict_syntax=true]'}
${r'[#include "/common/header.ftl"]'}
${r'[@htmlHead]'}
    <script type="text/javascript">
        function create() {
            $.openWindow('创建模板', '80%', '80%', "${r'${basePath}'}/${accessPath}-create.do");
        }
        
        function edit(id) {
            $.openWindow('修改模板', '80%', '80%', "${r'${basePath}'}/${accessPath}-edit/"+ id +".do");
        }
        
        function del(id, single) {
            $.datadel({
            	url: "${r'${basePath}'}/${accessPath}-delete/"+ id +".do",
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
                url: ctx + '/${accessPath}-list.json',
                title: '${domainObjectName}管理列表',
                method: 'POST',
                checkbox: true,
                pageSize: 6,
                order: 'createTime',
                sort: 'desc',
                searchButtonId: '#searchButton',
                queryParams: {
                    connectUrl: $('#connectUrl').val(),
                    databaseName: $("#databaseName").val()
                },
                tableId: '#dataGridList',
                columns: [
                    {field: 'id', className: 'text-c'},
                    [#list columnList as column]

					{field: '${column.javaProperty?uncap_first}', className: 'text-l', description: '${column.remarks}', sort: true, paramFormatter: function (row) {}},
                    [/#list]
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
${r'[/@htmlHead]'}
${r'[@htmlBody]'}
	<nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span> ${domainObjectName}管理
        <span class="c-gray en">&gt;</span> ${domainObjectName}列表
        <a class="btn btn-refresh radius r" style="line-height:1.6em;margin-top:3px"
           href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont">&#xe68f;</i>
        </a>
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                    [#list columnList as column]
                     <li>${column.remarks}：
                         <input type="text" name="${column.javaProperty?uncap_first}" id="${column.javaProperty?uncap_first}" class="input-text" style="width:auto;" placeholder="输入数据库连接地址">
                     </li>
                    [/#list]
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
${r'[/@htmlBody]'}