<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.common.page/core" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    response.flushBuffer();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>
        <page:block name="title"></page:block>
    </title>
    <link rel="stylesheet" href="${ctx}/layerui/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/layerui/font-awesome/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${ctx}/layerui/build/css/app.css" media="all">
    <link rel="stylesheet" href="${ctx}/layerui/build/css/themes/green.css" media="all">
    <page:block name="css"></page:block>

    <script src="${ctx}/layerui/layui/layui.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}'
    </script>
    <page:block name="javascript"></page:block>
</head>
    <body id="mainIndex" class="kit-theme">
        <page:block name="body"></page:block>
    </body>
</html>