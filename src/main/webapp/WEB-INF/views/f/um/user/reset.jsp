<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- basic styles -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/zTreeStyle.css">
    <style type="text/css">
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
        .ii{   color: #F00; margin-right: 8px;font-family: "SimSun"; }
        .label_1{margin-top:4px !important;}
        .input-tip{ display:none; font-size: 12px; color: #989898; margin-top: 5px !important;}
    </style>
</head>
<body>
<div class="main-container">

    <div class="">

        <div class="">

            <div class="space-6"></div>

            <form:form class="form-horizontal" name="resetform" commandName="formBean" action="${pageContext.request.contextPath}/f/user/resetPassword.action.do" method="get">
                <input type="hidden" name="username" value="${formBean.username}">

                <input type="text" class="col-xs-12 col-sm-6 hide" id="id" name="id" value="">

                <div class="form-group" style="margin-bottom:30px;">
                    <label class="control-label col-xs-12 col-sm-3 no-padding-right">用户名:</label>

                    <div class="col-sm-8">
                        <label class="col-xs-12 col-sm-6 label_1">${formBean.username}</label>
                        <div class="text-danger"></div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-xs-12 col-sm-3 no-padding-right"><i class="ii">*</i>新密码</label>
                    <div class="col-xs-12 col-sm-9">
                        <div class="clearfix">
                            <input type="password" class="col-xs-12 col-sm-6" id="pwd1" name="password" value="${formBean.password}" maxlength="16"  onkeydown="return banInputSapce(event);" onfocus="showTip(this);" onblur="hideTip(this);" >
                            <div class="input-tip">请输入长度为4-16字母或数字.不包含特殊符号</div>
                        </div>
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
                    <label class="control-label col-xs-12 col-sm-3 no-padding-right"><i class="ii">*</i>确认密码</label>
                    <div class="col-xs-12 col-sm-9">
                        <div class="clearfix">
                            <input type="password" class="col-xs-12 col-sm-6" id="pwd2" name="password_validate" value="${formBean.password_validate}" maxlength="16"  onkeydown="return banInputSapce(event);">
                        </div>
                        <div class="text-danger"><form:errors path="password_validate"/></div>
                    </div>

                </div>

                <div class="col-md-offset-3 col-sm-offset-3 col-md-9 col-sm-9">
                    <button type="submit" class="btn btn-xs btn-info">确定</button>

                    &nbsp; &nbsp;
                    <a href="${pageContext.request.contextPath}/f/um/user/list.view.do" class="btn btn-default btn-xs removeBreadcrumb">
                        返回
                    </a>
                </div>

            </form:form>

        </div>


    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate-util.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/jquery.ztree.core-3.5.js"></script>

<script type="text/javascript">
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

    function banInputSapce(e)
    {
        var keynum;
        if(window.event) // IE
        {
            keynum = e.keyCode
        }
        else if(e.which) // Netscape/Firefox/Opera
        {
            keynum = e.which
        }
        if(keynum == 32){
            return false;
        }
        return true;
    }

</script>
</body>
</html>