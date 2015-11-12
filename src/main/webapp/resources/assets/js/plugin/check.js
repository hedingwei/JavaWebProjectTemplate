// JavaScript Document


function Validator(){
	this.flag = true;
	this.num=[];
	this.init();
}

				


Validator.prototype.tips = function(obj, title, regvalue){
	var cont = $("<a href='#' class='tooltip'>"+title+"</a>");	
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
	
	$("input[validator]").unbind('blur').blur(function(){
		var t = $(this).attr("validator");
		switch(t){
			case 'Phone' :{
				
				var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
				this.flag = obj.tips(this,"电话号码输入有误",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'Email' :{
				var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
				this.flag = obj.tips(this,"Email输入有误",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'Ip' :{
				var reg = /^(?:(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){3}(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/;
				this.flag = obj.tips(this,"IP输入有误",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'UserName' :{
				var reg = /^\w+$/;
				this.flag = obj.tips(this,"用户名输入有误",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'UserNameCN' :{
				var reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
				this.flag = obj.tips(this,"名称输入有误不能为特殊字符",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'MobilePhone' :{
				var reg = /^1[3|4|5|8]\d{9}$/ ;
				this.flag = obj.tips(this,"手机号码输入有误",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'QQ' :{
				var reg = /^\d{5,11}$/;
				this.flag = obj.tips(this,"QQ号码输入有误",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'number' :{
				var reg = /^[0-9]*[1-9][0-9]*$/;
				this.flag = obj.tips(this,"输入有误",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'URL' :{
				var reg = /[a-zA-z]+:\/\/[^s]*/;
				this.flag = obj.tips(this,"请输入正确的URL地址",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'number2' :{//判断0与正整数
				var reg = /^\d+$/ ;
				this.flag = obj.tips(this,"请输入正确的数字",reg.test(obj.trim($(this).val())));
				break;		
			}
			case 'number3' : {
				var reg = /^\d+%?$/;
				this.flag = obj.tips(this,"请输入正确的格式", reg.test(obj.trim($(this).val())));
			}
		}
	});	

};

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
		if($(this).val() != ""){
			var t = $(this).attr("validator");
			switch(t){
				case 'Phone' :{
					
					var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
					this.flag = obj.tips(this,"电话号码输入有误",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'Email' :{
					var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
					this.flag = obj.tips(this,"Email输入有误",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'Ip' :{
					var reg = /^(?:(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){3}(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/;
					this.flag = obj.tips(this,"IP输入有误",reg.test(obj.trim($(this).val())));
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
					this.flag = obj.tips(this,"名称输入有误不能为特殊字符",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'MobilePhone' :{
					var reg = /^1[3|4|5|8]\d{9}$/ ;
					this.flag = obj.tips(this,"手机号码输入有误",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'QQ' :{
					var reg = /^\d{5,11}$/;
					this.flag = obj.tips(this,"QQ号码输入有误",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'number' :{
					var reg = /^[0-9]*[1-9][0-9]*$/;
					this.flag = obj.tips(this,"输入有误",reg.test(obj.trim($(this).val())));
					obj.num.push(this.flag);
					break;		
				}
				case 'URL' :{
					var reg = /[a-zA-z]+:\/\/[^s]*/;
					this.flag = obj.tips(this,"请输入正确的URL地址",reg.test(obj.trim($(this).val())));
					break;		
				}
				case 'number2' :{//判断0与正整数
					var reg = /^\d+$/ ;
					this.flag = obj.tips(this,"请输入正确的数字",reg.test(obj.trim($(this).val())));
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

