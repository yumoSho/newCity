(function ($) {
    'use strict';

    /**
     * jqGrid column model template
     * {
     *    colModel: [
     *      { name: 'xxx', index:'xxx', template: 'actions2' }
     *    ]
     * }
     *
     * {@see grid.base.js#jqGrid}
     */
    $.jgrid.cmTemplate = $.extend($.jgrid.cmTemplate, {
        updateGoodsPriceOne: { sortable: false, resizable: false, search: false, formatter: 'updateGoodsPriceOne', align: 'center', width: 90 },
        leave: { sortable: false, resizable: false, search: false, formatter: 'leave', align: 'center', width: 90 },
        orderz: { sortable: false, resizable: false, search: false, formatter: 'orderz', align: 'center', width: 90 },
        orderz2: { sortable: false, resizable: false, search: false, formatter: 'orderz2', align: 'center', width: 90 },
        actionsComment: { sortable: false, resizable: false, search: false, formatter: 'actionsComment', align: 'center', width: 90 },
        actionsConsult: { sortable: false, resizable: false, search: false, formatter: 'actionsConsult', align: 'center', width: 90 },
        actions0: { sortable: false, resizable: false, search: false, formatter: 'actions0', align: 'center', width: 90 },
        actions2: { sortable: false, resizable: false, search: false, formatter: 'actions2', align: 'center', width: 90 },
        actions3: { sortable: false, resizable: false, search: false, formatter: 'actions3', align: 'center', width: 90 },
        actions4: { sortable: false, resizable: false, search: false, formatter: 'actions4', align: 'center', width: 120 },
        img: { sortable: false, search: false, formatter: 'img', align: 'center', type: 'S' },
        img2: { sortable: false, search: false, formatter: 'img2', align: 'center', type: 'S' },
        bool: { formatter: 'checkbox', edittype: 'checkbox', editoptions: {value: '1:是;0:否'}, defaultValue: '0', stype: 'select'/*, dataUrl, surl*/, searchoptions: { value: ':全部;1:是;0:否' }, align: 'center', type: 'B' },
        auditsatus: { formatter: 'checkbox', edittype: 'checkbox', editoptions: {value: '0:待审核;1:审核通过;2:审核未通过'}, defaultValue: '0', stype: 'select'/*, dataUrl, surl*/, searchoptions: { value: ':全部;0:待审核;1:审核通过;2:审核未通过' }, align: 'center', type: 'B' },
        logType: { formatter: 'checkbox', edittype: 'checkbox', editoptions: {value: '1:操作日志;2:登录日志;3:修改日志'}, defaultValue: '1', stype: 'select', searchoptions: { value: ':全部;1:操作日志;2:登录日志;3:修改日志' }, align: 'center', type: 'S' }, 
        // enum: { align: 'center', formatter: 'checkbox', editable: true, edittype: 'select', editoptions: { value: 'v1:Text1;v2:Text2', defaultValue: 'v2' } },
        num: { formatter: 'number', align: 'right', sorttype: 'number', searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'nu', 'nn'] }, type: 'N' },
        int: { formatter: 'integer', align: 'right', sorttype: 'number', searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'nu', 'nn'] }, type: 'I' },
        text: { stype: 'text', searchoptions: { sopt: ['cn','bw', 'bn', 'ew', 'en',  'nc', 'nu', 'nn', 'eq', 'ne' /*, 'in', 'ni'*/] }, align: 'left', type: 'S' },
        link: { formatter: 'link', formatoptions: { target: '_blank' }, stype: 'text', searchoptions: { sopt: ['bw', 'bn', 'ew', 'en', 'cn', 'nc', 'nu', 'nn', 'eq', 'ne' /*, 'in', 'ni'*/] }, align: 'left', type: 'S' },
        // date2: { formatter: 'date2', type: 'D' },
        date: {
            align: 'center', formatter: 'date', formatoptions: { srcformat: 'Y-m-d H:i:s' ,newformat:'Y-m-d H:i:s'/*, newformat: 'd-m-Y'*/ },
            searchoptions: {
                sopt: ['eq']
            }, type: 'D'
        }
    });

    /**
     * jqGrid formatter
     * {
     *  colModel: [
     *      { formatter: 'img' }
     *  ]
     * }
     * {@see grid.fmatter.js}
     */
    $.fn.fmatter = $.extend($.fn.fmatter, {
        img: function (val, opts, rwd, act) {
            if (!val) return "";

            var op = { w: 100, h: 100 };
            if (opts.colModel !== undefined && opts.colModel.formatoptions !== undefined) {
                op = $.extend({}, op, opts.colModel.formatoptions);
            }

            val = /^http/.test(val) ? val : (window.contextPath || '') + "/" + val + '_100x100.jpg';   //缩略图

            var noneVal = /^http/.test(val) ? val : (window.contextPath || '') + "/" +"images/blank.gif";  //暂无图片

            //return '<img src="' + noneVal + '" lz-src="' +  val + ' " width="' + op.w + '" height="' + op.h + '">';

            return '<img src="' +  val + ' " width="' + op.w + '" height="' + op.h + '">';
        },
        img2: function (val, opts, rwd, act) {
            if (!val) return "";

            var op = { w: 50, h: 50 };
            if (opts.colModel !== undefined && opts.colModel.formatoptions !== undefined) {
                op = $.extend({}, op, opts.colModel.formatoptions);
            }
            val = /^http/.test(val) ? val : (window.contextPath || '') + "/" + val;
            return '<img title="点击放大" class="ad-img" style="cursor: pointer;" src="' +  val + '" width="' + op.w + '" height="' + op.h + '">';
        },
        updateGoodsPriceOne: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<a href="javascript:void(0);" class="updatePrice">保存价格</a>' +
                '</div>';
        },
        orderz: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-search.png" width="15" height="15" title="Edit" alt="查看详情">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'del\');" src="' + ctx + '/images/admin/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        orderz2: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions21.call(this, \'update\');" src="' + ctx + '/images/admin/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions21.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-search.png" width="15" height="15" title="Show" alt="Show">' +
                '</div>';
        },
        leave: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-search.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        actions0: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '</div>';
        },
        actions2: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'del\');" src="' + ctx + '/images/admin/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions2: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                visible=$('#datagrid').jqGrid('getRowData', rid).visible,
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}

                };
            switch (act) {
                case 'del':
                	// ;
                	/*$grid.jqGrid('delGridRow', rid, op.delOptions);
                    $grid.jqGrid('delRowData', rid);*/
                    if(visible==1){
                        Glanway.Messager.alert("提示", "首页导航显示分类不能删除");
                        return false;
                    }
                    var rowData = $grid.jqGrid('getRowData',rid);
                    var str = "您确定要删除选中的记录吗?";
                	Glanway.Messager.confirm('警告', str,function (r) {
                        if(!r) return;
                        var loadding = layer.load(1, {
                            shade: [0.1,'#fff'] //0.1透明度的白色背景
                        });
                    	 $.ajax({
                            url: 'delete',
                            type: 'post',
                            traditional: true,
                            data: { "id": rid }
                        }).done(function (data) {
                             layer.close(loadding);
                            var removed;
                            if (data && data.success) {
                                removed = data.result || [];
                                $grid.trigger("reloadGrid");
                                /*
                                $.gritter.add({
                                    title:'提示',
                                    text:'操作成功',
                                    sticky: false,
                                    time: 3000
                                });
                                */
                                layer.alert('操作成功', {
                                    skin:'layer-ext-message' //样式类名
                                    ,closeBtn:1
                                    ,time:3000
                                    ,title:'提示 '
                                    ,shade: 0
                                    ,offset:'rb'
                                    ,btn:''
                                });
                            } else {
                            	/*-
                            	$.gritter.add({
                                    title:'提示',
                                    text:'没有相关权限！',
                                    sticky: false,
                                    time: 3000
                                });
                                */
                                layer.alert(data.message, {
                                    skin:'layer-ext-message' //样式类名
                                    ,closeBtn:1
                                    ,time:3000
                                    ,title:'提示 '
                                    ,shade: 0
                                    ,offset:'rb'
                                    ,btn:''
                                });
                            }
                        }).fail(function (data) {
                             layer.close(loadding);
                            // TODO ERROR MSG
                            layer.alert("请求失败", {
                                skin:'layer-ext-message' //样式类名
                                ,closeBtn:1
                                ,time:3000
                                ,title:'提示 '
                                ,shade: 0
                                ,offset:'rb'
                                ,btn:''
                            });
                        });
                    });
                    break;
                case 'edit':
                    window.location.href = "edit/" + rid;
                    break;
            }
        },
        actionsConsult: function (value, opts) {//TODO
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.actionsConsult1.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<a href="javascript:void(0);" onclick="jQuery.fn.fmatter.actionsConsult1.call(this, \'update\');">回复</a>' +

                '</div>';
        },
        actionsConsult1: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };

            switch (act) {
                case 'edit':
                    window.location.href = "edit/" + rid;
                    break;
                case 'update':
                	$("#datagrid").setSelection(rid);
                	showConsult();
                	break;
            }
        },
        actionsComment: function (value, opts) {//TODO
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.actionsComment1.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<a href="javascript:void(0);" onclick="jQuery.fn.fmatter.actionsComment1.call(this, \'update\');">回复</a>' +
                '<img onclick="jQuery.fn.fmatter.actionsComment1.call(this, \'del\');" src="' + ctx + '/images/admin/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        actionsComment1: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };

            switch (act) {
                case 'edit':
                    window.location.href = "edit/" + rid;
                    break;
                case 'update':
                	$("#datagrid").setSelection(rid);
                	showComment();
                	break;
                case 'del':
                    // ;
                    /*$grid.jqGrid('delGridRow', rid, op.delOptions);
                     $grid.jqGrid('delRowData', rid);*/
                    Besture.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                        r && $.ajax({
                            url: 'delete',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data && data.success) {
                                $grid.trigger("reloadGrid");
                                layer.alert('操作成功', {
                                    skin:'layer-ext-message' //样式类名
                                    ,closeBtn:1
                                    ,time:3000
                                    ,title:'提示 '
                                    ,shade: 0
                                    ,offset:'rb'
                                    ,btn:''
                                });
                            }else{
                                layer.alert(data.message, {
                                    skin:'layer-ext-message' //样式类名
                                    ,closeBtn:1
                                    ,time:3000
                                    ,title:'提示 '
                                    ,shade: 0
                                    ,offset:'rb'
                                    ,btn:''
                                });
                            }
                        }).fail(function () {
                            layer.alert('操作失败', {
                                skin:'layer-ext-message' //样式类名
                                ,closeBtn:1
                                ,time:3000
                                ,title:'提示 '
                                ,shade: 0
                                ,offset:'rb'
                                ,btn:''
                            });
                        });
                    });
            }
        },
        actions3: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions3.call(this, \'show\');" src="' + ctx + '/images/admin/icon-search.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions3.call(this, \'del\');" src="' + ctx + '/images/admin/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        // 好吧，那个留给你们了, 我再加个吧...
        rowactions3: function(act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id");
            if ('show' === act) {
                window.location.href = "show?id=" + rid;
            } else {
                $.fn.fmatter.rowactions2.apply(this, arguments);
            }
        },
        actionsEdit: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '</div>';
        },
        actions4: function (value, opts) {
            var ctx = (window.Besture || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'del\');" src="' + ctx + '/images/admin/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions4: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };
            switch (act) {
                case 'del':
                    // ;
                    /*$grid.jqGrid('delGridRow', rid, op.delOptions);
                     $grid.jqGrid('delRowData', rid);*/
                    Besture.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                        r && $.ajax({
                            url: 'delete',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data && data.success) {
                                layer.alert('操作成功', {
                                    skin:'layer-ext-message' //样式类名
                                    ,closeBtn:1
                                    ,time:3000
                                    ,title:'提示 '
                                    ,shade: 0
                                    ,offset:'rb'
                                    ,btn:''
                                });
                            }else{
                                layer.alert(data.message, {
                                    skin:'layer-ext-message' //样式类名
                                    ,closeBtn:1
                                    ,time:3000
                                    ,title:'提示 '
                                    ,shade: 0
                                    ,offset:'rb'
                                    ,btn:''
                                });
                            }
                        }).fail(function () {
                            layer.alert('操作失败', {
                                skin:'layer-ext-message' //样式类名
                                ,closeBtn:1
                                ,time:3000
                                ,title:'提示 '
                                ,shade: 0
                                ,offset:'rb'
                                ,btn:''
                            });
                        });
                    });
                    break;
            }
        },
    });

    (function () {
        var _parseDate = $.jgrid.parseDate;
        // $.jgrid.fmatter.date -> $.jgrid.formatter.date.masks 提供的无法处理 ms 和 $.jgrid.parseDate
        $.jgrid.parseDate = function (format, date, newformat, opts) {
            if (/*'ms' == format && */typeof date === 'number') {
                date = new Date(date);
            }
            return _parseDate.call(this, format, date, newformat, opts);
        };
    })();

    // 执行搜索前将搜索转换为特定的格式
    $.jgrid.extend({
        _: function () {
            var $self = this,
                me = $self[0];
            function clear() {
                var postData = me.p.postData;
                for (var p in postData) {
                    if (/^search_/.test(p) && postData.hasOwnProperty(p)) {
                        delete postData[p];
                    }
                }
            }

            $self.on('jqGridToolbarBeforeSearch', function () {
                var me = this,
                    postData = me.p.postData,
                    filters = $.extend({rules: []}, $.parseJSON(postData.filters));

                function getType(field) {
                    var cm = me.p.colModel, i;
                    for (i = 0; i < cm.length; i++) {
                        if (cm[i].index === field) {
                            return cm[i].type || 'S';
                        }
                    }
                    return 'S';
                }
                clear();
                $.each(filters.rules, function (i, rule) {
                    var key = "search_" + rule.field + '_' + rule.op + '_' + getType(rule.field);
                    if (postData[key]) {
                        postData[key] = $.isArray(postData[key]) ? postData[key].push(rule.data) : [postData[key], rule.data];
                    } else {
                        postData[key] = rule.data;
                    }
                });
            }).on('jqGridToolbarBeforeClear', clear);
            return this;
        }
    });
    /**
     * jqGrid defaults
     *
     */
    $.jgrid.defaults = $.extend($.jgrid.defaults, {
        mtype: 'post',
        // height: 'auto',
        altRows: true,
        gridview: true,
        rownumbers: true,
        /*add by begin chensheng 表格宽度自适应*/
        shrinkToFit:false,
        /*add by end chensheng 表格宽度自适应*/
        rowNum: 10,
        rowList: [5, 10, 15],
        viewrecords: true,
        // pginput: false,
        recordpos: 'right',
        pagerpos: 'center',
        prmNames: { page: 'page', rows: "rows", sort: "sort", order: "order" },
        sortname: 'id', // 默认排序字段
        sortorder: "asc",
        jsonReader: {
            root: 'rows',            // 数据根
            page: 'page',            // 当前页码
            total: 'totalPages',     // 总page
            records: 'total',       // 总记录
            repeatitems: false      // 数据不可重复
        },
        /*gridComplete*/onInitGrid: function () {
            var me = this,
                p = me.p,
                $self = $(me);
            /*add by begin chensheng 表格宽度自适应*/
            var $listPanel = $(".listPage");
            if($listPanel.length > 0){
                $self.setGridWidth($listPanel.width()-22);
                $(window).resize(function(){
                    $self.setGridWidth($listPanel.width()-22);
                });
            }
            /*add by begin chensheng 表格宽度自适应*/
            
            // 一些常规化处理
            // 如果有分页条的话在分页条上..
            p.pager && $self.jqGrid('navGrid', p.pager);
            $self.jqGrid('filterToolbar', { searchOperators: true }).jqGrid('_');
        }
    });

    /**
     * {@see grid.formedit.js#navGrid}
     */
    $.jgrid.nav = $.extend($.jgrid.nav, {
        edit: false,
        add: false,
        del: false,
        search: false,
        refresh: true,
        refreshicon: "ui-icon-refresh",
        refreshstate: 'firstpage',
        position: "left",
        beforeRefresh: null,
        afterRefresh: null,
        cloneToTop: false
    });

   /* $('.ui-jqgrid-bdiv').lazyload({
        //placehoder: /^http/.test(val) ? val : (window.contextPath || '') +'/images/loading.gif',
        //errorPlaceholder: /^http/.test(val) ? val : (window.contextPath || '') +'/images/noimg.jpg'

        placehoder: '${ctx}/images/loading.gif',
        errorPlaceholder: '${ctx}/images/noimg.jpg'

    });*/

})(jQuery);