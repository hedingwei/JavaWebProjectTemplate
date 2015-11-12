// JavaScript Document

function SearchBar(){
	this.formdata = [];	
	this.submit();
	this.init();
}

/*
SearchBar.prototype.init = function(){
	var $this = this;
	// 获取从URI上的传参并显示在 searchOption 相应域中
	var uri = window.location.search;
	
	$this.isInit = false;
		$("*[searchOption]").each(function(i){
				//$this.formdata[i]={name:$(this).attr('searchOption'),value:$this.type($(this)),obj:$(this)};
				var val = $(this).attr('searchOption');
				var re = new RegExp("" +val+ "=([^&?]*)", "ig");
				var uriVal = ((uri.match(re))?(decodeURI(uri.match(re)[0].substr(val.length+1))):null);
				console.log(uriVal);
				if (uriVal != null) {
					$this.initValBytype($(this),uriVal);
					$this.isInit = true;
				};
		});
	$this.searchOption();
	eval($('form').attr('func')+"($this.isInit, $this.formdata)");
    
}*/

SearchBar.prototype.init = function(){
	var $this = this;
	// 获取从URI上的传参并显示在 searchOption 相应域中
	var uri = window.location.search;
	
	$this.isInit = false;
		$("*[searchOption]").each(function(i){
				//$this.formdata[i]={name:$(this).attr('searchOption'),value:$this.type($(this)),obj:$(this)};
				var val = $(this).attr('searchOption');
				var re = new RegExp("" +val+ "=([^&?]*)", "ig");
				var uriVal = ((uri.match(re))?(decodeURI(uri.match(re)[0].substr(val.length+1))):null);
				if (uriVal != null) {
					$this.initValBytype($(this),uriVal);
					$this.isInit = true;
				};
		});
	$this.searchOption();
	eval($('form').attr('func')+"($this.isInit, $this.formdata)");
    
}


//判别tag类型；
SearchBar.prototype.initValBytype = function(obj,val){
	switch(obj.get(0).tagName){
			case 'INPUT':
				var type = obj.attr("type");
				switch(type){
					case 'radio':
						obj.attr("checked","checked");
						break;
					case 'checkbox':
						if(val==1){
							obj.attr("checked","checked");
						}else{
							obj.removeAttr("checked");
							//obj.context.checked ==false;
						}
						break;
					default:
						obj.val(val);
						break;
					}
				break;
			case 'TEXTAREA':
				obj.html(val);
				break;
			case 'SELECT':
				obj.val(val).trigger('change');
				break;
		}
};

//判别tag类型；
SearchBar.prototype.type = function(obj){
	var value="";
	switch(obj.get(0).tagName){
			case 'INPUT':
				var type = obj.attr("type");
				switch(type){
					case 'radio':
						//value =typeof obj.attr("checked") !='undefined'?obj.val():"";
						value = obj.context.checked ==true? "1":"";
						break;
					case 'checkbox':
						//console.log(obj.context.checked)
						value =obj.context.checked ==true? "1":"";
						break;
					default:
						value = obj.val();
						break;
					}
				break;
			case 'TEXTAREA':
				value = obj.html();
				break;
			case 'SELECT':
				value = obj.val();
				break;
		}
	return value;
};
	
//加载字段
SearchBar.prototype.searchOption = function(){
	var $this = this;
	$("*[searchOption]").each(function(i){
			$this.formdata[i]={name:$(this).attr('searchOption'),value:$this.type($(this)),obj:$(this)};
		});
	};
	
//提交
SearchBar.prototype.submit = function(){
	var $this = this;
	$("*[Search = 'Submit']").click(function(){
			$this.searchOption();
			eval($('form').attr('func')+"(true, $this.formdata)");
	});
	$("input[isEnter='true']").keydown(function(event){
		if(event.keyCode == '13'){
			$this.searchOption();
			eval($('form').attr('func')+"(true, $this.formdata)");
		}
	});
	/*$(window).keydown(function(event){
		if(event.keyCode == '13'){
			$this.searchOption();
			eval($('form').attr('func')+"(true, $this.formdata)");
		}
	});*/
	
};

//验证
SearchBar.prototype.validate = function(obj){
	var $this = this;
	
	};

//实例
// $(function(){new SearchBar()});