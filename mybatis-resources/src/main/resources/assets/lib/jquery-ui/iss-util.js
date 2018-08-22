function number_format(number, decimals, dec_point, thousands_sep) {
    /*
    * 参数说明：
    * number：要格式化的数字
    * decimals：保留几位小数
    * dec_point：小数点符号
    * thousands_sep：千分位符号
    * */
    number = (number + '').replace(/[^0-9+-Ee.]/g, '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function (n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.ceil(n * k) / k;
        };

    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    var re = /(-?\d+)(\d{3})/;
    while (re.test(s[0])) {
        s[0] = s[0].replace(re, "$1" + sep + "$2");
    }

    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

var _date_format = function (now) {
    var year = now.getYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
};

function _datadel(ajaxOption, single) {
    var ids = $.getCheckedValue();
    ids = ids.join(",");
    if (!ids && ids != "" && !single) {
        $.openTip("请选择一项再进行操作.", true, function () {
            _closeLoading();
            return;
        })
    } else {
        $.openTip(ajaxOption.message ? ajaxOption.message : '确定删除吗？', false, function () {
            _closeLoading();
            $.openLoading('正在进行删除操作,请稍后...');
            ajaxOption = !ajaxOption ? {} : ajaxOption;
            if (!ajaxOption.data || !ajaxOption.data.id) {
                ajaxOption.data = {id: ids};
            }
            $.ajax(ajaxOption);
        });
    }
}

function _getCheckboxIds() {
    var ids = "";
    if (dataIds.length > 0) {
        for (var i = 0; i < dataIds.length; i++) {
            var id = dataIds[i];
            ids += id + ",";
        }
    }
    if (ids && ids.length > 1) {
        ids = ids.substring(0, ids.length - 1);
    }
    return ids;
}


function _closeLoading() {
    layer.closeAll();
}

function openLoading(msg) {
    var msg = msg ? msg : "";
    layer.msg(msg, {icon: 16, time: 1000 * 10000, shade: [0.3, '#000']});
}

function _openTip(content, isAlert, callback, title) {
    if (isAlert) {
        layer.confirm(content, {
            title: title ? title : '提示信息',
            closeBtn: 0,
            btn: ['确定'] //按钮
        }, function () {
            if (callback) {
                callback();
            } else {
                _closeLoading();
            }
        }, function () {
            _closeLoading();
        });
    } else {
        layer.confirm(content, {
            title: title ? title : '提示信息',
            closeBtn: 0,
            btn: ['确定', '取消'] //按钮
        }, function () {
            callback();
        }, function () {
            _closeLoading();
        });
    }
}

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

/**
 * 打开窗口
 * @param title 标题
 * @param width 宽度
 * @param height 高度
 * @param url 访问地址
 * @private
 */
function _openWindow(title, width, height, url) {
    layer.open({
        type: 2,
        title: title,
        area: [width || '700px', height || '450px'],
        fixed: true, //不固定
        move: false,
        shadeClose: false,
        shade: 0.6,
        maxmin: false,
        content: url
    });
}

function _submitAjax(ctx, opt, data, success, error) {
    $.ajax({
        url: ctx + opt.url,
        method: opt.method || 'POST',
        dataType: opt.dataType || 'JSON',
        data: data,
        async: false,
        success: function (data) {
            try {
                data = eval('(' + data +')');
            } catch (err) {}
            console.log(data);
            if (data.status != 200 && data.code != 200) {
                $.openTip(data.message, true, function () {
                    $.closeLoading();
                }, '提示信息');
                return;
            }
            $.closeLoading();
            success(data);
        },
        error: function (err) {
            $.closeLoading();
            error(err);
        }
    })
}

function _validation(id, rules, fn) {
    $("#" + id).validate({
        rules: rules,
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            fn(form);
        }
    });
}


$.extend({
    /**
     * 打开确认框、弹出框
     */
    openTip: _openTip,
    openLoading: openLoading,
    closeLoading: _closeLoading,
    getCheckboxIds: _getCheckboxIds,
    datadel: _datadel,
    dateSimpleFormat: _date_format,
    openWindow: _openWindow,
    submitAjax: _submitAjax,
    validation: _validation
});