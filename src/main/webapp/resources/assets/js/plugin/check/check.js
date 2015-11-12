// JavaScript Document


function Validator(){
	this.flag = true;
	this.num=[];
	console.log(this)
	this.init();
}

				

//tips结构样式
Validator.prototype.tips = function(obj, title, regvalue){
	var cont = $("<span class='tooltip'>"+title+"</span>");	
	if(regvalue == false){
		$(obj).css("border","1px solid red");
		$(obj).after(cont);
		
		$(document).one("click",{o:cont},function(e){
			e.data.o.fadeOut(4000);
		});
		
		
		return false;
	}else{
		//$("a").remove(".tooltip");
		//cont.remove();
		$(obj).css("border","1px solid #DBDFE6");
		return true;	
	}
	
}


Validator.prototype.init = function(){
	var obj = this;
	//input框失去焦点验证
	$("input[validator]").unbind('blur').blur(function(){
		if($(this).val()==""){
			this.flag = obj.tips(this,"输入不能为空",false);
		}
		else{
				var t = $(this).attr("validator");
				switch(t){
					case 'Phone' :{
						
						var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"请输入正确的电话号码",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'Email' :{
						var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"请输入正确的Email地址",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'Ip' :{
						var reg = /^(?:(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){3}(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/;
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"请输入正确的IP地址",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'UserName' :{
						var reg = /^\w+$/;
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"用户名输入有误",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'UserNameCN' :{
						var reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"名称不能为特殊字符",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'MobilePhone' :{
						var reg = /^1[3|4|5|8]\d{9}$/ ;
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"请输入正确的手机号码",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'QQ' :{
						var reg = /^\d{5,11}$/;
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"请输入正确的QQ号码",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'number' :{
						var reg = /^-?[1-9]*(\.\d*)?$|^-?0(\.\d*)?$/;
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"请输入数字",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'date' :{
						var reg = /^\d{4}.[0-3][0-9].[0-3][0-9]$/;	//2001/01/01;2001.01.01
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"请输入日期",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'MAC' :{
						var reg = /[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}/;	//2001/01/01;2001.01.01
						$(this).val(obj.trim($(this).val()));
						this.flag = obj.tips(this,"请输入正确的MAC地址",reg.test(obj.trim($(this).val())));
						break;		
					}
					case 'positive_integer' :{//正整数
						var reg = /^[1-9]([0-9])*/;
						var _val = $(this).val().toString();
						if(_val.indexOf(".")!=-1){
							this.flag = obj.tips(this,"第一位不能为0",reg.test(obj.trim(-1)));	
						}else{
							this.flag = obj.tips(this,"第一位不能为0",reg.test(obj.trim($(this).val())));	
						}
						break;		
					}
				}

		}
	});	

};

//按钮调用验证方法
Validator.prototype.validator = function(){
	this.num=[];
	var obj = this;
	$("input[validator]").each(function(){
		if($(this).val()==''){
			if(typeof(this.flag) != 'undefined'){
				if(this.flag == true){
					$(this).css("border","1px solid #DBDFE6");
					obj.num.push(true);
				}else{
					$(this).css("border","1px solid red");
					obj.num.push(false);	
				}
					
			}else{	
				this.flag = false;
				$(this).css("border","1px solid red");
				obj.num.push(this.flag);	
			}
		}
		if($(this).val()==""){
			this.flag = obj.tips(this,"输入不能为空",false);
		}
		else{
			var t = $(this).attr("validator");
			switch(t){
				case 'Phone' :{
					
					var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;

					this.flag = obj.tips(this,"请输入正确的电话号码",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'Email' :{
					var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
					this.flag = obj.tips(this,"请输入正确的Email地址",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'Ip' :{
					var reg = /^(?:(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){3}(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/;
					this.flag = obj.tips(this,"请输入正确的IP地址",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'UserName' :{
					var reg = /^\w+$/;
					this.flag = obj.tips(this,"用户名输入有误",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'UserNameCN' :{
					var reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
					this.flag = obj.tips(this,"名称不能为特殊字符",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'MobilePhone' :{
					var reg = /^1[3|4|5|8]\d{9}$/ ;
					this.flag = obj.tips(this,"请输入正确的手机号码",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'QQ' :{
					var reg = /^\d{5,11}$/;
					this.flag = obj.tips(this,"请输入正确的QQ号码",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'number' :{
					var reg = /^\d+$/;
					this.flag = obj.tips(this,"请输入数字",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'date' :{
					var reg = /^\d{4}.[0-3][0-9].[0-3][0-9]$/;	//2001/01/01;2001.01.01
					this.flag = obj.tips(this,"请输入日期",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'MAC' :{
					var reg = /[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}/;	//2001/01/01;2001.01.01
					this.flag = obj.tips(this,"请输入正确的MAC地址",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'positive_integer' :{//正整数
					var reg = /^[1-9]([0-9])*/;
					var _val = $(this).val().toString();
					if(_val.indexOf(".")!=-1){
						this.flag = obj.tips(this,"第一位不能为0",reg.test(obj.trim(-1)));	
					}else{
						this.flag = obj.tips(this,"第一位不能为0",reg.test(obj.trim($(this).val())));	
					}
					obj.num.push(this.flag);
					break;		
				}
			}
		}
			
	});
	var check = false;
	for(var i =0; i<obj.num.length; i++){
		if(obj.num[i] == true){
			check = true;	
		}else{
			check = false;
			return check;
		}
	}
	return check;
}
Validator.prototype.trim = function(str){
   return str.replace(/(^\s*)|(\s*$)/g, "");
};

