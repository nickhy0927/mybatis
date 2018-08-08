;var tab;
layui.define(['element', 'nprogress', 'form', 'table', 'loader', 'tab', 'navbar', 'onelevel', 'laytpl', 'spa'], function (exports) {
    var $ = layui.jquery;
    var navbar = layui.navbar;
    tab = layui.tab;
    var app = {
        config: {
            type: 'iframe'
        },
        set: function (options) {
            var that = this;
            $.extend(true, that.config, options);
            return that;
        },
        init: function () {
            var that = this,
                _config = that.config;
            if (_config.type === 'iframe') {
                tab.set({
                    renderType: 'iframe',
                    //mainUrl: 'table.html',
                    openWait: true,
                    elem: '#container',
                    onSwitch: function (data) { //选项卡切换时触发
                        console.log(data.layId); //lay-id值
                    },
                    closeBefore: function (data) { //关闭选项卡之前触发
                        return true; //返回true则关闭
                    }
                }).render();
                // navbar加载方式一，直接绑定已有的dom元素事件                
                navbar.bind(function (data) {
                    tab.tabAdd(data);
                });
                // 处理顶部一级菜单
                var onelevel = layui.onelevel;
                if (onelevel.hasElem()) {
                    onelevel.set({
                        remote: {
                            url: ctx + '/layerui/onelevel1.json' //远程地址
                        },
                        onClicked: function (id) {
                            console.log(id);
                            navbar.set({
                                remote: {
                                    url: ctx + '/layerui/navbar1.json'
                                }
                            }).render(function (data) {
                                tab.tabAdd(data);
                            });
                        },
                        renderAfter: function (elem) {
                            elem.find('li').eq(0).click(); //模拟点击第一个
                        }
                    }).render();
                }
            }

            // ripple start
            var addRippleEffect = function (e) {
                // console.log(e);
                layui.stope(e)
                var target = e.target;
                if (target.localName !== 'button' && target.localName !== 'a') return false;
                var rect = target.getBoundingClientRect();
                var ripple = target.querySelector('.ripple');
                if (!ripple) {
                    ripple = document.createElement('span');
                    ripple.className = 'ripple';
                    ripple.style.height = ripple.style.width = Math.max(rect.width, rect.height) + 'px'
                    target.appendChild(ripple);
                }
                ripple.classList.remove('show');
                var top = e.pageY - rect.top - ripple.offsetHeight / 2 - document.body.scrollTop;
                var left = e.pageX - rect.left - ripple.offsetWidth / 2 - document.body.scrollLeft;
                ripple.style.top = top + 'px';
                ripple.style.left = left + 'px';
                ripple.classList.add('show');
                return false;
            };
            document.addEventListener('click', addRippleEffect, false);
            // ripple end

            return that;
        }
    };

    //输出test接口
    exports('app', app);
});