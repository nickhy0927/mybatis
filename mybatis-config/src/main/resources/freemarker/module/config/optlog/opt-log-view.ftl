<#include '../../../common/header.ftl'>
<@htmlHead>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</@htmlHead>
<@htmlBody>
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red"></span>操作人名字：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    ${optLog.username!'系统用户'}
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red"></span>操作方法：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    ${optLog.method!''}
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red"></span>操作类：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    ${optLog.clazz!''}
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red"></span>操作类型：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    ${optLog.optType!''}
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red"></span>操作信息：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    ${optLog.message!''}
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red"></span>操作数据：</label>
                <div class="formControls col-xs-9 col-sm-9">
                    <pre id="show">
                        <script type="text/javascript">
                           var data = ${optLog.data!'undefined'};
                           if (data) {
                               var res = JSON.stringify(data, null, 4);    // 缩进4个空格
                               $('#show').html(res);
                           }
                        </script>
                    </pre>
                </div>
            </div>
        </form>
    </article>
</@htmlBody>