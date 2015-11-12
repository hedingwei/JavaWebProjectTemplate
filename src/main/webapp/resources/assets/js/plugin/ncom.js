/*
 AMForm
 */
function AMForm() {
	this.formdata = [];
	this.submit();
	this.flag = true;
}

AMForm.prototype.init = function() {
	var $this = this;
	$this.searchOption();
	eval($('form').attr('func') + "($this.formdata)");
}

//加载字段
AMForm.prototype.searchOption = function() {
	var $this = this;
	$("*[amparam]").each(function(i) {
		$this.formdata[i] = {
			name: $(this).attr('amparam'),
			value: $this.type($(this)),
			obj: $(this),
			validator: $(this).attr("validator"),
			valiTip: $(this).attr("valiTip")
		};
	});

};


//判别tag类型；
AMForm.prototype.type = function(obj) {
	var $this = this;
	var value = "";
	switch (obj.get(0).tagName) {
		case 'INPUT':
			var type = obj.attr("type");
			switch (type) {
				case 'radio':
					value = typeof obj.attr("checked") != 'undefined' ? obj.val() : null;
					break;
				case 'checkbox':
					value = obj.context.checked == true ? "true" : "false";
					//value =typeof obj.attr("checked") !='undefined'?obj.val():null;
					break;
				default:
					if ($(this).hasClass('select2')) {
						value = $this.trim(obj.select2('val'));
						break;
					} else {
						value = $this.trim(obj.val());
						break;
					}
			}
			break;
		case 'TEXTAREA':
			value = $this.trim(obj.val());
			break;
		case 'SELECT':
			value = obj.val();
			break;
		case 'SPAN':
			var appArr = [];
			var apps = obj.find('.appBlock');
			apps.each(function(index, ele) {
				var tmp = {
					"id": $(ele).attr("id"),
					"name": $(ele).find("span").html()
				}
				appArr.push(tmp);
			})
			value = appArr;
			break;
	}
	return value;
};

//提交
AMForm.prototype.submit = function() {
	var $this = this;
	var param = {};
	$("*[submit='ok']").click(function() {
		$this.searchOption();
		for (var i = 0; i < $this.formdata.length; i++) {
			//console.log($this.formdata[i]);
			var key = $this.formdata[i].name;
			var value = $this.formdata[i].value;
			param[key] = value;
            var obj = $this.formdata[i].obj;
			//console.log($this.formdata[i]);

			//$this.validate($this.formdata[0].validator, $this.formdata[0].value, $this.formdata[0].valiTip, $this.formdata[0].obj);
			//$this.validate($this.formdata[1].validator, $this.formdata[1].value, $this.formdata[1].valiTip, $this.formdata[1].obj);
			//if($this.formdata[2].value == "true"){
			//	$this.validate($this.formdata[4].validator, $this.formdata[4].value, $this.formdata[4].valiTip, $this.formdata[4].obj);
			//}
			//if($this.formdata[7].value == "true"){
			//	$this.validate($this.formdata[10].validator, $this.formdata[10].value, $this.formdata[10].valiTip, $this.formdata[10].obj);
			//}

			if (!$this.flag) {
				return
			}

		}

		eval($("form[func]").attr('func') + "(param,$this.formdata)");
	});
};

//验证
AMForm.prototype.validate = function(type, value, tip, obj) {
	var $this = this;
	if (value == "") {
		this.flag = $this.tips(this, (tip == "" || typeof(tip) == 'undefined') ? "输入不能为空" : tip, false);
	} else {
		switch (type) {
			case 'undefined':
			{
				this.flag = true;
				break;
			}
			case 'Phone':
			{
				var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
				this.flag = $this.tips(this, "请输入正确的电话号码", reg.test(value) ,obj);
				break;
			}
			case 'Email':
			{
				var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
				this.flag = $this.tips(this, "请输入正确的Email地址", reg.test(value),obj);
				break;
			}
			case 'IP':
			{
				var reg = /^(?:(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){3}(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/;
				this.flag = $this.tips(this, "请输入正确的IP地址", reg.test(value),obj);
				break;
			}
			case 'UserName':
			{
				var reg = /^[0-9a-zA-Z@_.]+$/;
				this.flag = $this.tips(this, "用户账号不能为特殊字符", reg.test(value),obj);
				break;
			}
			case 'UserNameCN':
			{
				var reg = /^[a-zA-Z0-9_()\u4e00-\u9fa5]+$/;
				this.flag = $this.tips(this, "名称不能为特殊字符", reg.test(value),obj);
				break;
			}
			case 'UserNameCNList':
			{
				var reg = /[~#^$@%&!*,.{}=+'"\\:;/]/;
				var list = value.split("\n");
				var len = list.length;
				for (var i = 0; i < len; i++) {
					this.flag = $this.tips(this, "包含特殊字符", (!reg.test(list[i])),obj);
					//console.log(this.flag+"  ->   "+list[i]);
					if (!this.flag) {
						return;
					} else {
						if (list[i].length > 30) {
							alert("输入一行数据过长");
							this.flag = false;
						}
					}
				}
				//this.flag = obj.tips(this,"名称不能为特殊字符",reg.test(value));
				break;

			}
			case 'websiteName':
			{
				//var reg = /^(http:\/\/)?(www.)?(\w+\.)+\w{2,4}(\/)?$/;
				this.flag = $this.tips(this, "请输入网址", $(this).val(value),obj);
				break;
			}
			case 'MobilePhone':
			{
				var reg = /^1[3|4|5|8]\d{9}$/;
				this.flag = $this.tips(this, "请输入正确的手机号码", reg.test(value),obj);
				break;
			}
			case 'QQ':
			{
				var reg = /^\d{5,11}$/;
				this.flag = $this.tips(this, "请输入正确的QQ号码", reg.test(value),obj);
				break;
			}
			case 'number':
			{
				var reg = /^\d+$/;
				this.flag = $this.tips(this, "请输入数字", reg.test(value),obj);
				break;
			}
			case 'date':
			{
				var reg = /^\d{4}.[0-3][0-9].[0-3][0-9]$/; //2001/01/01;2001.01.01
				this.flag = $this.tips(this, "请输入日期", reg.test(value),obj);
				break;
			}
			case 'MAC':
			{
				var reg = /[A-Fa-f\d]{2}:[A-Fa-f\d]{2}:[A-Fa-f\d]{2}:[A-Fa-f\d]{2}:[A-Fa-f\d]{2}:[A-Fa-f\d]{2}/; //2001/01/01;2001.01.01
				this.flag = $this.tips(this, "请输入正确的MAC地址", reg.test(value),obj);
				break;
			}
			case 'positive_integer':
			{ //正整数
				var reg = /^[1-9]([0-9])*/;
				var _val = $(this).val().toString();
				if (_val.indexOf(".") != -1) {
					this.flag = $this.tips(this, "第一位不能为0", reg.test(obj.trim(-1)),obj);
				} else {
					this.flag = $this.tips(this, "第一位不能为0", reg.test(value),obj);
				}
				break;
			}
		}

	}
};

AMForm.prototype.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
};

//解析url参数
AMForm.prototype.expurl = function(val) {
	var uri = window.location.search;
	var re = new RegExp("" + val + "=([^&?]*)", "ig");
	return ((uri.match(re)) ? (decodeURI(uri.match(re)[0].substr(val.length + 1))) : null);
}

AMForm.prototype.initParam = function(params,callback) {
	var $this = this;
	$this.isInit = false;
	$("*[amparam]").each(function(i) {
		var val = $(this).attr('amparam');
		var uriVal = params[val];
		if (uriVal != null) {
			$this.initValBytype($(this), uriVal);
			$this.isInit = true;
		};
	});
	callback();
};

AMForm.prototype.initValBytype = function(obj, val) {

	switch (obj.get(0).tagName) {
		case 'INPUT':
			var type = obj.attr("type");
			switch (type) {
				case 'radio':
					obj.attr("checked", "checked");
					break;
				case 'checkbox':
					obj.prop("checked", val);
					break;
				default:
					if (obj.hasClass('select2')) {
						//console.log(JSON.parse(val));
						obj.select2('data', JSON.parse(val));
						break;
					} else {
						obj.val(val);
						break;
					}
			}
			break;
		case 'TEXTAREA':
			obj.html(val);
			break;
		case 'SELECT':
			obj.val(val).trigger('change');
			break;
		case 'SPAN':
			var data = JSON.parse(val);
			for (var i = 0, len = data.length; i < len; i++) {
				var selectedAppId = data[i].id;
				var selectedAppName = data[i].name;
				var appStr = "<span class='form-control appBlock' id='" + selectedAppId + "'><span>" + selectedAppName + "</span><a class='btn btn-default btn-sm del_app' onClick=delApp(this)>x</a></span>"
				$(".applan").append(appStr);
			}
			break;
	}
};


//tips结构样式
AMForm.prototype.tips = function(obj, title, regvalue, currentObj) {
	//var cont = $("<span class='tooltip'>"+title+"</span>");
	if (regvalue == false) {
		console.log($(currentObj));
		alert(title);
		//var bordercss = $(obj).css("border");
		$(currentObj).css("border","2px solid red");

		//$(document).one("click",{},function(e){
		//	$(obj).css("border",bordercss);
		//});
		return false;
	} else {
		return true;
	}

}
