[#ftl encoding="utf-8" strict_syntax=true]
[#macro fileUpload ]

    <script type="text/javascript">
        //上传文件绑定
        $(document).ready(function () {
            console.log('11111111111');
            $("#attachUploader").webUpload({
                auto: true,
                btntxt:"请选择上传文件",
                fileNumLimit:1,
                accept:'file',
                fileSuffix: 'doc,docx,ppt,pptx,xlsx,pdf,xls,txt,DOC,DOCX,PPT,PPTX,XLSX,XLS,TXT,pdf',
                swf: '${ctx}/assets/lib/webuploader/0.1.5/Uploader.swf',
                server: ctx + "/attach/fileUpload.json",//上传后台服务器地址
                deleteServer: ctx + "/attach/fileUpload.json",//删除后台服务器地址
            });
            console.log($("#attachUploader"));
        })
    </script>
    <input type="file" id="attachUploader">
[/#macro]