<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="page" uri="http://www.common.page/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<html>
<head>
    <title><page:block name="title"></page:block></title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/wopop/style_log.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/wopop/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/wopop/userpanel.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/jquery-ui/jquery-ui.min.css">
    <page:block name="css"></page:block>
    <style type="text/css">
        .code_img {
            float: right;
            height: 35px;
            width: 100px;
        }
    </style>
    <script type="text/javascript" src="${ctx}/static/assets/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/jquery-ui/jquery-ui.js"></script>
    <page:block name="javascript"></page:block>
</head>
<body>

</body>
</html>
