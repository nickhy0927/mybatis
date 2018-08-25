[#ftl encoding="utf-8" strict_syntax=true]
${r'[#ftl encoding="utf-8" strict_syntax=true]'}
${r'[#include "/common/header.ftl"]'}
${r'[@htmlHead]'}
    <script type="text/javascript">
        $(document).ready(function () {
            $('.skin-minimal input').iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
            $.validation('addForm', {
            	[#list columnList as column]
            	${column.javaProperty?uncap_first}: { required:true, maxlength:200},
                [/#list]
            }, function () {
                $.openTip('你确定要保存吗？',false ,function() {
                    $.closeLoading();
                    $.openLoading("正在保存数据，请稍等...");
                    $.ajax({
                        url: '${r'${basePath}'}/${accessPath}-update.json',
                        method: 'POST',
                        dataType: 'JSON',
                        data: $("#addForm").serialize(),
                        success: function (data) {
                            $.closeLoading();
                            if (data.status != 200 && data.code != 200) {
                                $.openTip(data.msg, true);
                                return;
                            }
                            $.openTip(data.msg, true, function () {
                                window.parent.initData();
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            });
                        },
                        error: function (err) {
                            $.closeLoading();
                            $.openTip(err, true);
                            return;
                        }
                    });
                });
            });
        });
        //取消
        function removeIframe(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    </script>
${r'[/@htmlHead]'}
${r'[@htmlBody]'}
    <article class="page-container">
        <form class="form form-horizontal" id="addForm">
        	[#list columnList as column]
        	[#if column.javaProperty?uncap_first != 'id' && column.javaProperty?uncap_first != 'updateTime' && column.javaProperty?uncap_first != 'createTime' && column.javaProperty?uncap_first != 'status']
			<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                	<span class="c-red">*</span>
                	[#if column.remarks??]
                		${column.remarks}：
                	[#else]
                		${column.javaProperty?uncap_first}：
                    [/#if]
                </label>
                <div class="formControls col-xs-9 col-sm-9">
                    <input type="text" name="${column.javaProperty?uncap_first}" id="${column.javaProperty?uncap_first}" class="input-text" value="${r'${'}${domainObjectName?uncap_first + "." + column.javaProperty?uncap_first}${r'}'}" placeholder="please enter ${column.javaProperty?uncap_first}">
                </div>
            </div>
			[/#if]
            [/#list]
            <div class="row cl">
                <div class="col-xs-7 col-sm-8 col-xs-offset-2 col-sm-offset-2">
                    <button class="btn btn-primary radius" type="submit">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
                    <button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                </div>
            </div>
        </form>
    </article>
${r'[/@htmlBody]'}