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
        .form-actions{margin-top: 0; padding: 10px; margin-bottom: 0; padding: 10px;}
        .mgl5 {margin-left: 5px;}
        .form-horizontal .form-group {margin-left: 0; margin-right: 0;}
        .ii{   color: #F00; margin-right: 8px;font-family: "SimSun"; }
        .input-tip{ display:none; font-size: 12px; color: #989898;}
    </style>
</head>
<body>
<div class="main-container">

    <div class="">

        <div class="col-sm-12">

            <div class="space-6"></div>

            <form:form class="form-horizontal" role="form" name="domainform" commandName="formBean" action="${pageContext.request.contextPath}/f/um/domain/add.action.do" method="get">

                <div>
                    <h5 class="lighter green">新增 > <span style="color: darkgreen">${pname}</span></h5>
                </div>

                <div class="row">

                    <%--<input type="text" class="col-xs-12 col-sm-6 hide" id="" name="" value="${pid}">--%>
                    <input type="text" class="col-xs-12 col-sm-6 hide" id="pid" name="pid" value="${formBean.pid}">
                    <%--<input type="text" class="col-xs-12 col-sm-6 hide" id="" name="" value="${pname}">--%>
                    <input type="text" class="col-xs-12 col-sm-6 hide" id="pname" name="pname" value="${formBean.pname}">
                   <%-- <input type="text" class="col-xs-12 col-sm-6 hide" id="" name="" value="${pcode}">--%>
                    <input type="text" class="col-xs-12 col-sm-6 hide" id="pcode" name="pcode" value="${formBean.pcode}">

                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right"><i class="ii">*</i>区域名:</label>
                            <div class="col-xs-12 col-sm-8">

                                <input type="text" class="col-xs-10 col-sm-12"  maxlength="32" id="name" name="name" value="${formBean.name}" onkeydown="return banInputSapce(event);"  onfocus="showTip(this);" onblur="hideTip(this);">


                                <div class="input-tip">请输入以中文或字母开头,可包含数字、下划线,不包含特殊符号,长度为2-32的字符串</div>
                                <div class="text-danger"><form:errors path="name"/></div>
                            </div>

                        </div>
                    </div>

                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right"><i class="ii">*</i>区域IP范围:</label>
                            <div class="col-xs-12 col-sm-8">

                                <input type="text" class="col-xs-10 col-sm-12" id="iPRange" maxlength="32" name="iPRange" value="${formBean.iPRange}" onkeydown="return banInputSapce(event);">

                                <div class="text-danger"><form:errors path="iPRange"/></div>
                            </div>

                        </div>
                    </div>

                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right">区域描述:</label>
                            <div class="col-xs-6 col-sm-8">

                                <textarea id="description" name="description" class="col-xs-10 col-sm-12" class="input-xlarge"  maxlength="128" value="${formBean.description}" onkeydown="return banInputSapce(event);"></textarea>

                                <div class="text-danger"><form:errors path="description"/></div>
                            </div>

                        </div>
                    </div>

                </div>

                <div class="hr hr-dotted"></div>

                <div>
                    <h5 class="lighter green">对应CRM信息</h5>
                </div>



                <div class="row">



                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right"><i class="ii">*</i>对应数据库规则:</label>
                            <div class="col-xs-12 col-sm-8">

                                <select class="col-xs-10 col-sm-12"  id="crmColumn" name="crmColumn" value="${formBean.crmColumn}" >
                                    <c:forEach items="${fields}" var="item">
                                        <option>${item.t_field}</option>
                                    </c:forEach>
                                </select>

                                <div class="text-danger"><form:errors path="crmColumn"/></div>
                            </div>

                        </div>
                    </div>

                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right"><i class="ii">*</i>对应值:</label>
                            <div class="col-xs-12 col-sm-8">

                                <input type="text" class="col-xs-10 col-sm-12" id="crmCondition"  name="crmCondition" value="${formBean.crmCondition}" onkeydown="return banInputSapce(event);" onfocus="showTip(this);" onblur="hideTip(this);">


                                <div class="input-tip">请输入不含特殊字符的字符串</div>
                                <div class="text-danger"><form:errors path="crmCondition"/></div>



                            </div>

                        </div>
                    </div>



                </div>


                <div class="row">
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-sm-offset-3 col-md-9 col-sm-9">
                            <button type="submit" class="btn btn-xs btn-info">创建</button>

                            &nbsp; &nbsp;
                            <a href="${pageContext.request.contextPath}/f/um/domain/list.view.do" class="btn btn-default btn-xs removeBreadcrumb">
                                返回
                            </a>
                        </div>
                    </div>
                </div>


            </form:form>

        </div>


    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>
<script>
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