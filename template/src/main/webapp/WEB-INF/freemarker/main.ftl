<#include 'common/header.ftl'>
<@htmlHead title="主页" >
    <script type="text/javascript">
        $(document).ready(function () {
            $.openLoading('正在加载,请稍后...');
            setInterval(function () {
                $.closeLoading();
            },5000)
        })
    </script>
</@htmlHead>

<@htmlBody>
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span> 法务管理
        <span class="c-gray en">&gt;</span> 法务事务
        <a class="btn btn-refresh radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
            <i class="Hui-iconfont">&#xe68f;</i>
        </a>
    </nav>
    <div class="page-container">
        <form name="listForm">
            <div class="text-l cl">
                <ul class="sel-list">
                    <li>
                        关联项目名：
                        <input type="text" onclick="select_project()" name="projectName" id="projectName" class="input-text" style="width:auto;" placeholder="输入关联项目名">
                    </li>
                    <li>事务编号：
                        <input type="text" name="code" id="code" class="input-text" style="width:auto;" placeholder="输入事务编号">
                    </li>
                    <li>事务名称：
                        <input type="text" name="name" id="name" class="input-text" style="width:auto;" placeholder="输入事务名称">
                    </li>
                    <li>
                        <button type="button" class="btn btn-success radius" id="searchButton" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
                        <button type="reset" class="btn btn-danger radius" id="searchButton" name="">&nbsp;&nbsp; 重置&nbsp;&nbsp;</button>
                    </li>
                </ul>
            </div>
        </form>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
                <a href="javascript:;" onclick="affair_add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增事务</a>
            </span>
        </div>
        <div class="mt-20">
            <table id="dataGridList" class="table table-border table-bordered table-hover table-bg table-sort"></table>
        </div>
    </div>
</@htmlBody>