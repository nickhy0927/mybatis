<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.common.page/core" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<page:extends name="title">OA办公系统</page:extends>
<page:extends name="css">
    <style type="text/css">
        .row {
            margin-right: -25px;
            margin-left: -25px;
        }
    </style>
</page:extends>
<page:extends name="javascript">
    <script type="text/javascript">
        var message;
        layui.config({
            base: '${ctx}/layerui/build/js/'
        }).use(['app', 'message'], function() {
            var app = layui.app;
            //将message设置为全局以便子页面调用
            message = layui.message;
            //主入口
            app.set({
                type: 'iframe'
            }).init();
        });
    </script>
</page:extends>
<page:extends name="body">
    <div class="layui-layout layui-layout-admin kit-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">KIT ADMIN</div>
            <div class="layui-logo kit-logo-mobile">K</div>
            <ul class="layui-nav layui-layout-left kit-nav" kit-one-level>
                <li class="layui-nav-item layui-this"><a href="javascript:;" data-icon="" data-id="1">平台管理</a></li>
                <li class="layui-nav-item"><a href="javascript:;" data-id="2">产品</a></li>
                <li class="layui-nav-item"><a href="javascript:;"data-id="3">大数据</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child"> <!-- 二级菜单 -->
                        <dd><a href="javascript:;" data-id="4">移动模块</a></dd>
                        <dd><a href="javascript:;" data-id="5">后台模版</a></dd>
                        <dd><a href="javascript:;" data-id="6">电商平台</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">社区</a></li>
            </ul>
            <ul class="layui-nav layui-layout-right kit-nav">
                <li class="layui-nav-item">
                    <a href="">控制台<span class="layui-badge">9</span></a>
                </li>
                <li class="layui-nav-item">
                    <a href="">个人中心<span class="layui-badge-dot"></span></a>
                </li>
                <li class="layui-nav-item" style="right: 10px">
                    <a href=""><img src="http://t.cn/RCzsdCq" class="layui-nav-img">我</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">修改信息</a></dd>
                        <dd><a href="javascript:;">安全管理</a></dd>
                        <dd><a href="javascript:;">退出系统</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
        <div class="layui-side layui-bg-black kit-side">
            <div class="layui-side-scroll">
                <!-- <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div> -->
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                </ul>
            </div>
        </div>
        <div class="layui-body" id="container">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;"></div>
        </div>

        <div class="layui-footer">
            <!-- 底部固定区域 -->
            2017 &copy;
            <a href="javacript:;">kit.zhengjinfan.cn/</a> MIT license

        </div>
    </div>
</page:extends>
<jsp:include page="/parent/base.jsp" />