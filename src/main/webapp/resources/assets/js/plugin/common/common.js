var localhost = "http://192.168.1.211:8080";

//解析url参数
function expurl(val) {
    var uri = window.location.search;
    var re = new RegExp("" + val + "=([^&?]*)", "ig");
    return ((uri.match(re)) ? (decodeURI(uri.match(re)[0].substr(val.length + 1))) : null);
}

//解决IE不支持trim()方法
if (!String.prototype.trim) {
    String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g, '');
    };
}

//日期格式化 (new Date()).format('yyyy-MM-dd hh:mm:ss');
Date.prototype.format = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds(), //毫秒
    }
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
}

function getDate(day) { //获取系统时间前n天的日期
    var zdate = new Date();
    var sdate = zdate.getTime() - (1 * 24 * 60 * 60 * 1000);
    var edate = new Date(sdate - (day * 24 * 60 * 60 * 1000)).format("yyyy-MM-dd");
    return edate;
}

//时间戳转化时间
function formatDate(now) {
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var date = now.getDate();
    if (date < 10) {
        date = "0" + date;
    }
    var hour = now.getHours();
    if (hour < 10) {
        hour = "0" + hour;
    }
    var minute = now.getMinutes();
    if (minute < 10) {
        minute = "0" + minute;
    }
    var second = now.getSeconds();
    if (second < 10) {
        second = "0" + second;
    }
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

//获取今天日期
function gettoday() {
    var d = new Date();
    var vYear = d.getFullYear();
    var vMon = d.getMonth() + 1;
    var vDay = d.getDate();
    if (vMon < 10) {
        vMon = "0" + vMon;
    }
    if (vDay < 10) {
        vDay = "0" + vDay;
    }
    // console.log(vYear+"-"+vMon+"-"+vDay);
    var todayDate = vYear + "-" + vMon + "-" + vDay;
    return todayDate;
}

//获取昨天日期
function getyesterday() {
    var LSTR_ndate=new Date();
    var LSTR_Year=LSTR_ndate.getFullYear();
    var LSTR_Month=LSTR_ndate.getMonth();
    var LSTR_Date=LSTR_ndate.getDate(); //处理
    var uom = new Date(LSTR_Year,LSTR_Month,LSTR_Date);
    uom.setDate(uom.getDate()-1);//取得系统时间的前一天,重点在这里,负数是前几天,正数是后几天
    var LINT_MM=uom.getMonth();
    LINT_MM++;
    var LSTR_MM=LINT_MM > 10?LINT_MM:("0"+LINT_MM)
    var LINT_DD=uom.getDate();
    var LSTR_DD=LINT_DD > 10?LINT_DD:("0"+LINT_DD) //得到最终结果
    uom = uom.getFullYear() + "-" + LSTR_MM + "-" + LSTR_DD;
    return uom;
}


//验证ipv4
function ValidateIPaddress(ip) {
    var ipformat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
    if (ip.match(ipformat)) {
        return true;
    } else {
        return false;
    }
}

//验证ipv6
function vali6(str) {
    if (/^((?=.*::)(?!.*::.+::)(::)?([\dA-F]{1,4}:(:|\b)|){5}|([\dA-F]{1,4}:){6})((([\dA-F]{1,4}((?!\3)::|:\b|$))|(?!\2\3)){2}|(((2[0-4]|1\d|[1-9])?\d|25[0-5])\.?\b){4})$/i.test(str)) {
            return true;
        } else {
            return false;
        }
    }
    //验证特殊字符

function invalidChar(str) {
    if (/[~#^$@%&!*,.(){}=+'"\\:;/]/gi.test(str)) {
            //	alert("false");
            return true;
        } else {
            return false;
        }
    }
    //验证URL地址

function isURL(str) {
    var strRegex = "^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
    var re = new RegExp(strRegex);
    if (re.test(str)) {
        //alert("成功");
        return true;
    } else {
        //alert("失败");
        return false;
    }
}

function isValidURL(url) {
    var RegExp = /^(([\w]+:)?\/\/)?(([\d\w]|%[a-fA-f\d]{2,2})+(:([\d\w]|%[a-fA-f\d]{2,2})+)?@)?([\d\w][-\d\w]{0,253}[\d\w]\.)+[\w]{2,4}(:[\d]+)?(\/([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)*(\?(&?([-+_~.\d\w]|%[a-fA-f\d]{2,2})=?)*)?(#([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)?$/;
    if (RegExp.test(url)) {
        return true;
    } else {
        return false;
    }
}

//验证数字
function isNum(str) {
    if (/^\d+$/gi.test(str)) {
        //	alert("false");
        return true;
    } else {
        return false;
    }
}

//IP地址转数字
function ipToNumbert(ip) {
    var d = ip.split('.');
    var n = d[0] * Math.pow(256, 3);
    n += d[1] * Math.pow(256, 2);
    n += d[2] * 256;
    n += +d[3];
    return n;
}

//全角转半角
function CtoH(obj) {
    var str = obj.value;
    var result = "";
    for (var i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) == 12288) {
            result += String.fromCharCode(str.charCodeAt(i) - 12256);
            continue;
        }
        if (str.charCodeAt(i) > 65280 && str.charCodeAt(i) < 65375)
            result += String.fromCharCode(str.charCodeAt(i) - 65248);
        else result += String.fromCharCode(str.charCodeAt(i));
    }
    obj.value = result;
}

var layer = "<div class='layer' style='display:none;'>";
layer += "    <div style='position:fixed; left:0;top:0; width:100%; height:100%;  background:rgb(0,0,0);filter:alpha(opacity=30); opacity:0.3; z-index:9999; '>";
layer += "    </div>";
layer += "	<div style='width:580px; margin:0 auto; margin-top:10%; background:#eee; border:5px solid #FFF; border-radius:5px; box-shadow:0px 0px 5px; padding:20px 30px; position:fixed; left:0; right:0; top:0; z-index:10000;'>";
layer += "		<p><img src='../images/loading.gif' style='width: 100px; height:100px; margin-left:40%;'></p>";
layer += "		<p style='color:#E3A957; font-weight:bold; padding:10px; text-align:center;'>当前操作的数据量较大，请耐心等候...</p>";
layer += "      <p> 提示：当需要反复查看某段数据时，可以选择导出数据到本地，方便查看。当创建监控对象导入的数据较大量时，等待时间较长需要耐心等候...</p>";
layer += "	</div>";
layer += "</div>"

var layer0 = "<div class='layer' style='display:none;'>";
layer0 += "    <div style='position:fixed; left:0;top:0; width:100%; height:100%;  background:rgb(0,0,0);filter:alpha(opacity=30); opacity:0.3; z-index:9999; '>";
layer0 += "    </div>";
layer0 += "	<div style='width:580px; margin:0 auto; margin-top:10%; background:#eee; border:5px solid #FFF; border-radius:5px; box-shadow:0px 0px 5px; padding:20px 30px; position:fixed; left:0; right:0; top:0; z-index:10000;'>";
layer0 += "		<p><img src='images/loading.gif' style='width: 100px; height:100px; margin-left:40%;'></p>";
layer0 += "		<p style='color:#E3A957; font-weight:bold; padding:10px; text-align:center;'>当前操作的数据量较大，请耐心等候...</p>";
layer0 += "     <p> 提示：当需要反复查看某段数据时，可以选择导出数据到本地，方便查看。当创建监控对象导入的数据较大量时，等待时间较长需要耐心等候...</p>";
layer0 += "	</div>";
layer0 += "</div>"

function HourMinute() {
    var hour = "";
    for (var i = 0; i < 24; i++) {
        if (i < 10) {
            i = "0" + i.toString();
        }
        hour += "<option value='" + i + "'>" + i + "</option>";
    }
    $(".hour", self).append(hour);
    var minute = "";
    for (var i = 0; i < 60; i++) {
        if (i < 10) {
            i = "0" + i.toString();
        }
        minute += "<option value='" + i + "'>" + i + "</option>";
    }
    $(".minute", self).append(minute);
}

//计算输入字符长度
function len(s) {
    var l = 0;
    var a = s.split("");
    for (var i = 0; i < a.length; i++) {
        if (a[i].charCodeAt(0) < 299) {
            l++;
        } else {
            l += 2;
        }
    }
    return l;
}

/*
	AMForm
*/
function AMForm() {
    this.formdata = [];
    this.submit();
    this.submit2();
    this.flag = true;
    this.initParam();
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
/*AMForm.prototype.submit = function(){
	var $this = this;
	var param = {};
	$("*[submit='ok']").click(function(){
		$this.searchOption();
		for(var i=0; i<$this.formdata.length; i++){
			//console.log($this.formdata[i]);
			var key = $this.formdata[i].name;
			var value = $this.formdata[i].value;
			param[key] = value;
			//console.log($this.formdata[i]);
			$this.validate($this.formdata[i].validator,$this.formdata[i].value,$this.formdata[i].valiTip);
			if(!$this.flag){
				return	
			}
		}
		eval($('form').attr('func')+"(param)");
	});
};*/
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
            //console.log($this.formdata[i]);
            $this.validate($this.formdata[i].validator, $this.formdata[i].value, $this.formdata[i].valiTip);
            if (!$this.flag) {
                return
            }
        }
        eval($('form').attr('func') + "(param,$this.formdata)");
    });
};
//提交
AMForm.prototype.submit2 = function() {
    var $this = this;
    var param = {};
    $("*[submit='ok2']").click(function() {
        $this.searchOption();
        for (var i = 0; i < $this.formdata.length; i++) {
            //console.log($this.formdata[i]);
            var key = $this.formdata[i].name;
            var value = $this.formdata[i].value;
            param[key] = value;
            //console.log($this.formdata[i]);
            //$this.validate($this.formdata[i].validator,$this.formdata[i].value,$this.formdata[i].valiTip);
            if (!$this.flag) {
                return
            }
        }
        eval($('form').attr('func') + "(param,$this.formdata)");
    });
};

//验证
AMForm.prototype.validate = function(type, value, tip) {
    var obj = this;
    if (value == "") {
        this.flag = obj.tips(this, (tip == "" || typeof(tip) == 'undefined') ? "输入不能为空" : tip, false);
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
                    this.flag = obj.tips(this, "请输入正确的电话号码", reg.test(value));
                    break;
                }
            case 'Email':
                {
                    var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                    this.flag = obj.tips(this, "请输入正确的Email地址", reg.test(value));
                    break;
                }
            case 'IP':
                {
                    var reg = /^(?:(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){3}(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/;
                    this.flag = obj.tips(this, "请输入正确的IP地址", reg.test(value));
                    break;
                }
            case 'UserName':
                {
                    var reg = /^[0-9a-zA-Z@_.]+$/;
                    this.flag = obj.tips(this, "用户账号不能为特殊字符", reg.test(value));
                    break;
                }
            case 'UserNameCN':
                {
                    var reg = /^[a-zA-Z0-9_()\u4e00-\u9fa5]+$/;
                    this.flag = obj.tips(this, "名称不能为特殊字符", reg.test(value));
                    break;
                }
            case 'UserNameCNList':
                {
                    var reg = /[~#^$@%&!*,.{}=+'"\\:;/]/;
                    var list = value.split("\n");
                    var len = list.length;
                    for (var i = 0; i < len; i++) {
                        this.flag = obj.tips(this, "包含特殊字符", (!reg.test(list[i])));
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
                    this.flag = obj.tips(this, "请输入网址", $(this).val(value));
                    break;
                }
            case 'MobilePhone':
                {
                    var reg = /^1[3|4|5|8]\d{9}$/;
                    this.flag = obj.tips(this, "请输入正确的手机号码", reg.test(value));
                    break;
                }
            case 'QQ':
                {
                    var reg = /^\d{5,11}$/;
                    this.flag = obj.tips(this, "请输入正确的QQ号码", reg.test(value));
                    break;
                }
            case 'number':
                {
                    var reg = /^\d+$/;
                    this.flag = obj.tips(this, "请输入数字", reg.test(value));
                    break;
                }
            case 'date':
                {
                    var reg = /^\d{4}.[0-3][0-9].[0-3][0-9]$/; //2001/01/01;2001.01.01
                    this.flag = obj.tips(this, "请输入日期", reg.test(value));
                    break;
                }
            case 'MAC':
                {
                    var reg = /[A-Fa-f\d]{2}:[A-Fa-f\d]{2}:[A-Fa-f\d]{2}:[A-Fa-f\d]{2}:[A-Fa-f\d]{2}:[A-Fa-f\d]{2}/; //2001/01/01;2001.01.01
                    this.flag = obj.tips(this, "请输入正确的MAC地址", reg.test(value));
                    break;
                }
            case 'positive_integer':
                { //正整数
                    var reg = /^[1-9]([0-9])*/;
                    var _val = $(this).val().toString();
                    if (_val.indexOf(".") != -1) {
                        this.flag = obj.tips(this, "第一位不能为0", reg.test(obj.trim(-1)));
                    } else {
                        this.flag = obj.tips(this, "第一位不能为0", reg.test(value));
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

AMForm.prototype.initParam = function() {
    var $this = this;
    $this.isInit = false;
    $("*[amparam]").each(function(i) {
        //$this.formdata[i]={name:$(this).attr('searchOption'),value:$this.type($(this)),obj:$(this)};
        var val = $(this).attr('amparam');
        var uriVal = $this.expurl(val);
        if (uriVal != null) {
            $this.initValBytype($(this), uriVal);
            $this.isInit = true;
        };
    });
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
                    obj.attr("checked", "checked");
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
AMForm.prototype.tips = function(obj, title, regvalue) {
    //var cont = $("<span class='tooltip'>"+title+"</span>");	
    if (regvalue == false) {
        alert(title);
        //var bordercss = $(obj).css("border");
        //$(obj).css("border","1px solid red");

        //$(document).one("click",{},function(e){
        //	$(obj).css("border",bordercss);
        //});
        return false;
    } else {
        return true;
    }

}
