layui.define([ 'element', 'form', 'table', 'loader' ], function(exports) {
	var layer = layui.layer;
	var $ = layui.jquery;
	var table = layui.table;
	var commons = {
		openDialog : function(optionName, url, width, height) {
			$('a[data-action="' + optionName + '"]').on('click', function() {
				layer.open({
					type : 2,
					area : [ width, height ],
					content : url
				});
			})
		},
		initTable : function(options) {
			var settings = {
				elem : '#tableList',
				url : '',
				page : true,
				cols : [ options.columns ],
				method : 'post'
			};
			$.extend(true, settings, options);
			console.log(settings);
			table.render(settings);
		},
        createTable : function(options) {
            var settings = {
                elem : '#tableList',
                url : '',
                page : true,
                cols : [{

                }],
                method : 'post'
            };
            $.extend(true, settings, options);
            console.log(settings);
            table.render(settings);
        }
	};
	exports('commons', commons);
});