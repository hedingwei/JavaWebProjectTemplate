<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人信息编辑</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" type="text/css" media="all"
          href="${pageContext.request.contextPath}/resources/assets/js/date-time/daterangepicker-bs3.css"/>
    <style>
        /*#Userinformation input{width:160px!important;}*/
        #Userinformation input[type="checkbox"] {
            width: auto !important;
        }
        .label_1{margin-top:4px !important;}

        #Userinformation select {
            width: 160px !important;
        }

        #Userinformation textarea {
            width: 160px !important;
        }

        #Userinformation h3.header {
            font-size: 16px;
            margin-left: 20px;
            margin-top: 0px;
            border: none;
        }

        .col-md-offset-3 {
            margin-left: 10% !important;
        }
        .ii{   color: #F00; margin-right: 8px;font-family: "SimSun"; }
        /*密码强度*/
        .pw-strength {clear: both;position: relative;top: 8px;width: 180px;}
        .pw-bar{background: url(${pageContext.request.contextPath}/resources/images/pwd-1.png) no-repeat;height: 14px;overflow: hidden;width: 179px;}
        .pw-bar-on{background:  url(${pageContext.request.contextPath}/resources/images/pwd-2.png) no-repeat; width:0px; height:14px;position: absolute;top: 1px;left: 2px;transition: width .5s ease-in;-moz-transition: width .5s ease-in;-webkit-transition: width .5s ease-in;-o-transition: width .5s ease-in;}
        .pw-weak .pw-defule{ width:0px;}
        .pw-weak .pw-bar-on {width: 60px;}
        .pw-medium .pw-bar-on {width: 120px;}
        .pw-strong .pw-bar-on {width: 179px;}
        .pw-txt {padding-top: 2px;width: 180px;overflow: hidden;}
        .pw-txt span {color: #707070;float: left;font-size: 12px;text-align: center;width: 58px;}
        .mgl5 {margin-left: 5px;}
        .form-horizontal .form-group {margin-left: 0; margin-right: 0;}
        .input-tip{ display:none; font-size: 12px; color: #989898; margin-top: 5px !important;}

    </style>

</head>
<script>
    function check(){
        if(document.getElementById("hidden").value=='true'){
            document.getElementById("form-field-12").checked=true;
        }else{
            document.getElementById("form-field-12").checked=false;
        }
    }

</script>
<body onload="check()">
<div class="main-container">
    <div class="space-6"></div>
    <spring:hasBindErrors name="model">


        <c:if test="${errors.globalErrorCount > 0}">
            全局错误：<br/>
            <c:forEach items="${errors.globalErrors}" var="error">
                <spring:message var="message" code="${error.code}" arguments="${error.arguments}"
                                text="${error.defaultMessage}"/>
                <c:if test="${not empty message}">

                    <div class="alert alert-danger" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                            ${message}
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
    </spring:hasBindErrors>

    <div class="col-xs-12">
        <form:form class="form-horizontal" role="form" commandName="formBean"
                   action="${pageContext.request.contextPath}/f/pub/profile/resetpwd.action.do" method="get">
            <div id="Userinformation">
                <div>
                    <h5 class="lighter green">账号属性</h5>
                </div>

                <div class="row">

                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group  ">
                            <label class="col-sm-4 control-label no-padding-right">用户名:</label>

                            <div class="col-sm-8">
                                <label class="col-xs-10  col-sm-12 no-padding-right label_1"  value="${formBean.username}">${formBean.username}</label>
                                <div class="text-danger"><form:errors path="username"/></div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="username" value="${formBean.username}" >

                </div>

                <div class="hr hr-dotted"></div>

                <div>
                    <h5 class="smaller lighter green">修改密码</h5>
                </div>
                <div class="row" id="basicInfo">
                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right"><i class="ii">*</i>原始密码</label>
                            <div class="col-xs-12 col-sm-8">
                                <input type="password" class="col-xs-12 col-sm-12" id="pwd" name="oldpassword" value="${formBean.oldpassword}" maxlength="16"  >

                                <div class="text-danger" style="margin-top:10px;"><form:errors path="oldpassword"/></div>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right"><i class="ii">*</i>新密码</label>
                            <div class="col-xs-12 col-sm-8">
                                <input type="password" class="col-xs-12 col-sm-12" id="pwd1" name="password" value="${formBean.password}" maxlength="16"  onfocus="showTip(this);" onblur="hideTip(this);" >

                                <div class="input-tip">请输入长度为4-16字母或数字.不包含特殊符号</div>
                                <div id="level" class="pw-strength">
                                    <div class="pw-bar"></div>
                                    <div class="pw-bar-on"></div>
                                    <div class="pw-txt">
                                        <span>弱</span>
                                        <span>中</span>
                                        <span>强</span>
                                    </div>
                                </div>
                                <div class="text-danger" style="margin-top:10px;"><form:errors path="password"/></div>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right"><i class="ii">*</i>确认密码</label>
                            <div class="col-xs-12 col-sm-8">
                                <input type="password" class="col-xs-12 col-sm-12" id="pwd2" name="password_validate" value="${formBean.password_validate}" maxlength="16"  >
                                <div class="text-danger"><form:errors path="password_validate"/></div>
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <input type="submit" value="确定" class="btn btn-xs btn-info"/>
                        &nbsp; &nbsp;
                        <a href="javascript:;" class="btn btn-xs btn-inverse"   onclick="resetAll();">
                            重置
                        </a>
                        &nbsp; &nbsp;
                        <a class="btn btn-xs btn-default removeBreadcrumb" href="${pageContext.request.contextPath}/blank.do" target="content">
                            返回
                        </a>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/date-time/moment.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/date-time/daterangepicker.js"></script>
<script>
    $(function(){
        $('#pwd1').keyup(function () {
            var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
            var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
            var enoughRegex = new RegExp("(?=.{6,}).*", "g");

            if (false == enoughRegex.test($(this).val())) {
                $('#level').removeClass('pw-weak');
                $('#level').removeClass('pw-medium');
                $('#level').removeClass('pw-strong');
                $('#level').addClass(' pw-defule');
                //密码小于六位的时候，密码强度图片都为灰色
            }
            else if (strongRegex.test($(this).val())) {
                $('#level').removeClass('pw-weak');
                $('#level').removeClass('pw-medium');
                $('#level').removeClass('pw-strong');
                $('#level').addClass(' pw-strong');
                //密码为八位及以上并且字母数字特殊字符三项都包括,强度最强
            }
            else if (mediumRegex.test($(this).val())) {
                $('#level').removeClass('pw-weak');
                $('#level').removeClass('pw-medium');
                $('#level').removeClass('pw-strong');
                $('#level').addClass(' pw-medium');
                //密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等
            }
            else {
                $('#level').removeClass('pw-weak');
                $('#level').removeClass('pw-medium');
                $('#level').removeClass('pw-strong');
                $('#level').addClass('pw-weak');
                //如果密码为6为及以下，就算字母、数字、特殊字符三项都包括，强度也是弱的
            }
            return true;
        });
    })

    function showTip(obj)
    {
        $(obj).siblings("div:eq(0)").css("display","block");
    }
    function hideTip(obj)
    {
        $(obj).siblings("div:eq(0)").css("display","none");
    }
    function resetAll()
    {
        $("#basicInfo input").val("");
    }
</script>
</body>
</html>

