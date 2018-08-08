<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.common.page/core" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<page:extends name="title">数据库管理列表</page:extends>
<page:extends name="javascript">
    <script type="text/javascript">
        layui.config({
            base: ctx + '/layerui/build/js/'
        }).use(['commons'], function() {
            var commons = layui.commons;
            commons.openDialog('http://www.baidu.com','95%','95%');
            commons.initTable({
            	elem : '#tableList',
                url: ctx + '/template/database/database-list.json',
                method: 'post',
                where: {
                    layui:true
                },
                limit: 6,
                limits: [10, 20, 30, 40, 50, 60],
                layout: ['prev', 'page', 'next', 'skip', 'count', 'limit'],
                loading: true,
                columns: [ //表头
                    {type: 'checkbox',fixed: 'left'},
                    {field: 'connectUrl', title: '数据库连接地址', fixed: 'left',width: '27%'},
                    {field: 'driverClassName', title: '数据库驱动',width: '20%', sort: true},
                    {field: 'driverClassName1', title: '数据库驱动1',width: '20%', sort: true},
                    {field: 'driverClassName2', title: '数据库驱动2',width: '20%', sort: true},
                    {field: 'driverClassName3', title: '数据库驱动3',width: '20%', sort: true},
                    {field: 'driverClassName', title: '数据库驱动4',width: '20%', sort: true},
                    {field: 'databaseName', title: '数据库名称',width: '15%', sort: true},
                    {field: 'username', title: '数据库用户名',width: '10%'},
                    {field: 'password', title: '数据库密码',width: '10%'},
                    {field: 'poperation', title: '操作', width: '180', align: 'center', fixed: 'right', toolbar: '#opt-btns'}
                ],
                id: 'tableList',
                done: function (res, curr, count) {
                    console.log(res);
                }
            });
        });
    </script>
</page:extends>
<page:extends name="body">
    <div class="page-content">
    	<div class="kit-search-btns">
	        <a href="javascript:;" data-action="add" class="layui-btn layui-btn-sm">
	            <i class="layui-icon">&#xe608;</i>
	           	 新增
	        </a>
	        <a href="javascript:;" data-action="del-bulk" class="layui-btn layui-btn-sm layui-btn-danger">
	            <i class="layui-icon">&#xe640;</i>
	                           批量删除
	        </a>
	    </div>
    	<div class="kit-search-btns" id="opt-btns" style="display: none;">
	        <a href="javascript:;" title="删除" data-action="del-bulk" class="layui-btn layui-btn-sm layui-btn-danger">
	            <i class="layui-icon">&#xe640;</i>
	        </a>
	        <a href="javascript:;" title="修改" data-action="edit-bulk" class="layui-btn layui-btn-sm layui-btn-normal">
	            <i class="layui-icon">&#xe642;</i>
	        </a>
	        <a href="javascript:;" title="查看" data-action="edit-bulk" class="layui-btn layui-btn-sm layui-btn-warm">
	            <i class="layui-icon">&#xe642;</i>
	        </a>
	    </div>
	    <!--第二步：添加如下 HTML 代码-->
	            <table id="tableList" class="layui-table layui-table" lay-skin="row"></table>
	            <div id="paged" class="page"></div>
    </div>
</page:extends>
<jsp:include page="/parent/base.jsp"/>
