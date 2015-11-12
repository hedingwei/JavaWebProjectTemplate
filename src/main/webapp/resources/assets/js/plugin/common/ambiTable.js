/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$.fn.ambiTable = function(options) {

    //1. 默认参数
    var settings = $.extend({
        titles: [],
        title_with_checkbox: false,
        url: '',
        fnServerData: function(data){
            // 做逻辑处理
            return data;
        },
        fnServerParams: function(data) {

        }
    }, options);

    this.addClass("table").addClass("display").addClass("table-striped").addClass("table-bordered").addClass("table-hover");

    var aoColumns = [];

    for (var i = 0; i < options.titles.length; i++) {
        if($.inArray(i,settings.hide_Column) !== -1){
            aoColumns.push({"sTitle": options.titles[i],  bVisible: false});
        }else{
            aoColumns.push({"sTitle": options.titles[i]});
        }
    }
	
    if (options.title_with_checkbox) {
        aoColumns.unshift({"sTitle": "<input type='checkbox' class='chkAll'/>"});
    }
	
	var tableParams;

    var _dataTable = this.dataTable({
        "aoColumns": aoColumns,
		"oLanguage":{
			"sProcessing":"<div class='load'><p><img src='../images/loading.gif' style='width: 100px; height:100px; margin-left:37%;'></p><p style='color:#E3A957; text-align:center;'><b>正在加载数据，请耐心等候...</b></p></div>",
			"sLengthMenu":"每页显示_MENU_条记录",
			"sEmptyTable":"表中无数据存在",
			"sZeroRecords":"对不起，查询不到相关数据！",
			"sInfo":"当前显示_START_到_END_条，共_MAX_条记录",
			"sInfoFiltered":"数据表中共为_MAX_条记录",
			"sSearch":"搜索",
			"oPaginate":{
				"sFirst":"首页",
				"sPrevious":"上一页",
				"sNext":"下一页",
				"sLast":"末页"
			}
		},
		"bFilter":false,
		//"bDestroy": true,
		"bRetrieve": true,
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": settings.url,
        "fnServerParams": function ( aoData ) {
            // aoData.push( { "name": "ambimmort", "value": "1234" });
            // aoData.push( { "name": "fgggg", "value": "vvvvvvvvv" });
            settings.fnServerParams(aoData);
          },
        "fnServerData": function(sUrl, aoData, fnCallback, oSettings) {
			Pa=aoData;
			//console.log(tableParams);
            oSettings.jqXHR = $.ajax({
                "url": sUrl,
                "data": aoData,
                "success": function(data){
					//console.log(data);
					var d = options.fnServerData(data);
					//console.log(d);
					if(typeof(d)!="undefined"){
					if(options.title_with_checkbox){						
						for(var j=0; j<d.aaData.length; j++){
							d.aaData[j].unshift("<input type='checkbox' class='chk' value='" + d.aaData[j][settings.index_Column] + "'/>");
						}
					}
					
                    fnCallback(d);
					}
                },
                "dataType": "text",
                "cache": false
            });
        },
        "bAutoWidth": true,
        "bSort": false,
        "sPaginationType": "full_numbers"

    });

    //全选、反选
    var o = this;
    this.find(".chkAll").click(function() {
        var $this = this;
        o.find("tbody input[type='checkbox']").each(function() {
            this.checked = $this.checked;
        });
        o.find("tbody input[type='checkbox']").click(function() {
            if (this.checked === false) {
                $("#chkAll").attr("checked", false);
            }
        });
    });
	
	return _dataTable;
};



