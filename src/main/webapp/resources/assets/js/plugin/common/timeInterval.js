;(function($) {

	$.fn.timeinterval = function(options) {
		var settings = $.extend({
				config:[
					{key:"5分钟", value:"5min"},
//					{key:"1小时", value:"1h"}, 
//					{key:"3小时", value:"3h"},
					// {key:"一天", value:"24h"}
                                    ],
				queryFunc : function(inter, start, end) {}
			}, options);
		
		// var template =  "<div class=\"navbar-form navbar-left\">";
			// template +=		"<div class=\"form-group search_form\" id=\"target_search\">";
		var	template  = 		"<label class='dnone'>时间粒度：</label>";
			template +=			"<select searchOption='time_granularity' id=\"ti\" class=\"form-control dnone input-small\"></select>";
			template +=         "<div class='form-group'><div class='checkbox'><label><input id='chkhms' searchOption='chkhms' type='checkbox'/></label></div></div>";
			template +=			"<label class=\"_timearea seleTime\">具体时间：</label>";									
			template +=			"<select searchOption='HourMinuteSecond' type=\"text\" name=\"time_Start\" id=\"time_Start\"  class=\"form-control seleTime input-small\"></select>";								
			//template +=			"<select type=\"text\" name=\"time_End\" id=\"time_End\"  class=\"form-control\"></select>";
			// template +=		"</div>";
			// template +=		"<button class=\"btn btn-primary\">查询</button>";
			// template += "</div>";
		this.append(template);
		var self = this;

		for (var _index in settings.config ){
			var _view = settings.config[_index].key;
			var _val = settings.config[_index].value;
			$("#ti", this).append("<option value='" + _val + "'>" + _view + "</option>");
		}
		//$("._timearea, #time_Start, #time_End", this).hide();
		$("._timearea, #time_Start", this).hide();

		function select5m() {
			$("#time_Start, #time_End", self).empty();
			var timemap = ["00","05","10","15","20","25","30","35","40","45","50","55"];
			for (var i = 0; i<=24; i++) {
				if(i<10){
					i="0"+i.toString();
				}
				var group = "<optgroup label='"+i+":00'>";
				for(var j=0; j<12; j++){
					if (j == 0 && i == 0) continue;
					if (j != 0 && i == 24) break;
					var timeStr = i+":"+timemap[j];
					group += "<option value='"+timeStr+"'>"+timeStr+"</option>";
				}
				group += "</optgroup>";
				$("#time_Start", self).append(group);
			};
			$("._timearea, #time_Start", self).show();
			//$("._timearea, #time_Start, #time_End", self).show();
		}

		function select1h() {
			$("#time_Start, #time_End", self).empty();
			for (var i = 1; i<=24; i++) {
				if(i<10){
					i="0"+i.toString();
				}
				var timeStr = i + ":00";
				var group = "<option value='"+timeStr+"'>"+timeStr+"</option>";
				$("#time_Start, #time_End", self).append(group);
			};
			$("._timearea, #time_Start", self).show();
			//$("._timearea, #time_Start, #time_End", self).show();
		}

		function select3h() {
			$("#time_Start, #time_End", self).empty();
			for (var i = 1; i<=8; i++) {				
				var timeStr = i * 3;
				if(timeStr<10){
					timeStr="0"+timeStr.toString();
				}
				timeStr += ":00";
				var group = "<option value='"+timeStr+"'>"+timeStr+"</option>";
				$("#time_Start").append(group);
				$("#time_End").append(group);
			};
			$("._timearea, #time_Start", self).show();
//			$("._timearea, #time_Start, #time_End", self).show();
		}

		select5m();
		$("#ti", this).change(function(){
			var selected = $(this).val();
			if(selected=="5min"){
				$("#chkhms").show();
				select5m();
			}else if(selected=="1h"){
				$("#chkhms").show();
				select1h();
			}
			else if(selected=="3h"){
				$("#chkhms").show();
				select3h();
			}
			else if(selected=="24h"){
				$("._timearea, #time_Start", self).hide();
				$("#chkhms").hide();
				$('#time_Start option:selected').val('00:00');
			}
			else{
				$("._timearea, #time_Start", self).hide();
				//$("._timearea, #time_Start, #time_End", self).hide();
			}
		});
		
		$('#time_Start').change(function(){
			var timeSelected=$('#time_Start option:selected').val();
			if(timeSelected=='24:00'){
				$('#time_Start option:selected').val('00:00');
			}
		})
		
//		$('#chkhms').change(function(){
//			if($(this).context.checked==true){
//				$('.seleTime').show();
//			}else{
//				$('.seleTime').hide();
//			}
//		})

		$(".search_btn", this).click(function(){
			var _inter = $("#ti", self).val();
			var _start = $("#time_Start", self).val();
			//var _end = $("#time_End", self).val();
			settings.queryFunc(_inter, _start);
		});
		
		//$("#time_Start").change(function(){
//			var _start = $("#time_Start", self).val();
//			var hh=_start.split(":")[0];
//			var mm=_start.split(":")[1];
//			var int=parseInt();
//			console.log(int);
//			$("#time_End",self).val(_start);
//		})
		
		return this;
	}
})(jQuery);