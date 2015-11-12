/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.fn.searchbar = function(options) {

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

	var dom = this;

	var targetSearchOption = {
		init : function(type, value){
		// type: 0(监控对象)、1（用户） 
		
		// 1. 定义DOM
		var o = this;
		o._holder = $("<div class='form-group'></div>");
		o._lable = $("<label>查询对象：</label>");
		o._warp = $("<div class='form-group'></div>");
		o._select = $("<select class='form-control'><option value='0'>监控对象</option><option value='1'>用户</option></select>");
		o._input_target = $("<input type='text' class='form-control' style='display:none' placeholder='请点击选择监控对象..' />");
		o._input_user = $("<input type='text' class='form-control' style='display:none' placeholder='请输入用户名..' />");
		
		// 2. 事件
		o._select.change(function(){
			if($(this).val() == '0'){
				o._input_target.show();
				o._input_user.hide();
			}else{
				o._input_target.hide();
				o._input_user.show();
			}
		});
		
		
		// 3. 初始化
		o._select.val(type);
		type == "0" ? (function(){o._input_target.show().val(value)})() : (function(){o._input_user.show().val(value)})();
		
		
		this._holder.append(this._label).append(this._select).append(this._warp.append(this._input_target).append(this._input_user));
		
		return this._holder;
		},
		getVal : function(){
			var o = this;
				return {
					type: o._select.val(),
					val: o._select.val() == '0' ? o._input_target.val():o._input_user.val()
				}
		}
	};
	
	var sharedDeviceSearchOption = {
		init : function(type, value){
		// type: 0(全部)、1（区间） 
		
		// 1. 定义DOM
		var o = this;
		o._holder = $("<div class='form-group'></div>");
		o._lable = $("<label>共享台数：</label>");
		o._warp = $("<div class='form-group'></div>");
		o._select = $("<select class='form-control'><option value='0'>全部</option><option value='1'>自定义</option></select>");
		o._input_target = $("<input type='text' class='form-control' style='display:none' placeholder='请点击选择监控对象..' />");
		o._input_user = $("<input type='text' class='form-control' style='display:none' placeholder='请输入用户名..' />");
		
		// 2. 事件
		o._select.change(function(){
			if($(this).val() == '0'){
				o._input_target.show();
				o._input_user.hide();
			}else{
				o._input_target.hide();
				o._input_user.show();
			}
		});
		
		
		// 3. 初始化
		o._select.val(type);
		type == "0" ? (function(){o._input_target.show().val(value)})() : (function(){o._input_user.show().val(value)})();
		
		
		this._holder.append(this._label).append(this._select).append(this._warp.append(this._input_target).append(this._input_user));
		
		return this._holder;
		},
		getVal : function(){
			var o = this;
				return {
					type: o._select.val(),
					val: o._select.val() == '0' ? o._input_target.val():o._input_user.val()
				}
		}
	};
	
	
	
	
	
	
	var search_btn = $("<button type=\"button\" class=\"btn btn-primary search_btn\">查询</button>").click(function(){
			alert("当前选择："+targetSearchOption.getVal().type+" \n 内容： "+targetSearchOption.getVal().val)
	});
	
	
	dom.append(targetSearchOption.init('0','Hello'));
	dom.append(search_btn);
	
	

};



