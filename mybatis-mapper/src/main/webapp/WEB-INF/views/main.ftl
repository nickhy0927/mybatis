<#include 'common/header.ftl'>
<@htmlHead title="主页" >
    <link rel="stylesheet" type="text/css" href="${basePath}/assets/lib/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/assets/lib/bootstrap-select/css/bootstrap-select.min.css"/> <!-- 自定义样式 -->
    <script type="text/javascript" src="${basePath}/assets/lib/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basePath}/assets/lib/bootstrap-select/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="${basePath}/assets/lib/bootstrap-select/js/i18n/defaults-zh_CN.js"></script>
    <script type="text/javascript" src="${basePath}/assets/lib/bootstrap-select/js/bootstrapSelect.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // $.openLoading('正在加载,请稍后...');
            // setInterval(function () {
            //     $.closeLoading();
            // },5000)
            $('.selectpicker').combobox({
                data: ${SystemConstants.getSelectList()},
                valueField: 'value',
                textField: 'text'
            });
        })
    </script>
</@htmlHead>

<@htmlBody>
    <div class="page-container">
        <br>
        <a href="${basePath}/main.do?lang=zh_CN">中文</a> &nbsp;&nbsp;&nbsp;
        <a href="?lang=en_US">英文</a>
        <br/>
        <input value="${test}"><br>
        <br>
        ${MessageResources.getMessage("test.msg")}
        <br>
        <hr>
        <select name="lang" class="selectpicker"></select>
    </div>
</@htmlBody>