<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.common.page/core" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<page:extends name="title">
    404
</page:extends>
<page:extends name="body">
    <h1>404测试成功</h1>
</page:extends>
<jsp:include page="/parent/base.jsp" />
