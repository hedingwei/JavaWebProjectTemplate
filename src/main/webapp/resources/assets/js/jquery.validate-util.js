jQuery.validator.addMethod("ip", function (value, element) {
    return this.optional(element) || /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(value);
}, "请输入正确的IP地址");

jQuery.validator.addMethod("ascii", function (value, element) {
    return this.optional(element) || !/[^\w\d_\.\n@]+/.test(value);
}, "输入不允许出现非法字符");

jQuery.validator.addMethod("numbers", function (value, element) {
    var flag = true;
    $(element).val(parseInt(value));
    if(0>parseInt(value)||parseInt(value)>255){
        flag = false;
    }
    return this.optional(element) || flag;
}, "请输入0~255之间的数字");
jQuery.validator.addMethod("length32", function (value, element) {
    var flag = (value.length<=32)?true:false;
    return this.optional(element) || flag;
}, "密码位数不得大于八位");
// jQuery.validator.addMethod("dateValidator", function (value, element) {
//     var startTime = js_strto_time(tr_time($(".R_StartTime").val()));
//     var endTime = js_strto_time(tr_time($(".R_EndTime").val()));
//     return this.optional(element) || startTime <= endTime;
// }, "结束时间必须在起始时间之后");
// jQuery.validator.addMethod("time", function (value, element) {
//     var startTime = js_strto_time(tr_time($(".M_StartTime").val()));
//     var endTime = js_strto_time(tr_time($(".M_EndTime").val()));
//     return this.optional(element) || startTime <= endTime;
// }, "结束时间必须在起始时间之后");

jQuery.extend(jQuery.validator.messages, {
    required: "该项不能为空!",
    number: "该字段不是一个数字",
    email:"请输入正确的邮箱地址"
});


