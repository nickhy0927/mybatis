<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.common.page/core" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<page:extends name="title">DataTable列表例子</page:extends>
<page:extends name="javascript">
    <script type="text/javascript">
	    layui.config({
	        base: '${ctx}/layuier/build/js/'
	    }).use(['commons'], function() {
	        var commons = layui.commons;
	        commons.openDialog("add",'http://www.baidu.com','95%','95%');
	        commons.initTable({
	        	url: ctx + '/systemplate/sys-template-list.json',
	        	columns: [ //表头
                    {field: 'id', title: 'ID', sort: true, fixed: 'left'},
                    {field: 'projectPath', title: '用户名'},
                    {field: 'javaFilePackage', title: '性别', sort: true},
                    {field: 'sqlMapperPath', title: '城市'},
                    {field: 'sign', title: '签名'} ,
                    {field: 'experience', title: '积分', width: 80, sort: true},
                    {field: 'score', title: '评分', width: 80, sort: true},
                    {field: 'classify', title: '职业', width: 80} ,
                    {field: 'wealth', title: '财富', width: 135, sort: true}
                ]
	        });
	    });
    </script>
</page:extends>
<page:extends name="body">
	<div class="kit-search-btns">
        <a href="javascript:;" data-action="add" class="layui-btn layui-btn-sm"><i class="layui-icon">&#xe608;</i> 新增</a>
        <a href="javascript:;" data-action="del-bulk" class="layui-btn layui-btn-sm layui-btn-danger"><i class="layui-icon">&#xe640;</i> 批量删除</a>
    </div>
    <!--第二步：添加如下 HTML 代码-->
    <table id="tableList" class="layui-table" lay-skin="row"></table>
</page:extends>
<jsp:include page="/parent/basepage.jsp"/>