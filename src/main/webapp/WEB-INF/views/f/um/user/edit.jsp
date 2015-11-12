<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
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
                   action="${pageContext.request.contextPath}/f/user/edit.action.do?username=${formBean.username}" method="get">
            <div id="Userinformation">
                <div>
                    <h5 class="lighter green">账号属性</h5>
                </div>

                <div class="row">

                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group  ">
                            <label class="col-sm-4 control-label no-padding-right">用户名:</label>

                            <div class="col-sm-8">
                                <label class="col-xs-10  col-sm-12 no-padding-right label_1"  >${formBean.username}</label>
                                <div class="text-danger"><form:errors path="username"/></div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="username" value="${formBean.username}" >

                </div>

                <div class="hr hr-dotted"></div>

                <div>
                    <h5 class="smaller lighter green">基本信息</h5>
                </div>

                <div class="row" id="basicInfo">


                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-4"><i class="ii">*</i>姓名:</label>

                            <div class="col-sm-8">
                                <input type="text" id="form-field-4" name="name" value="${formBean.name}"  maxlength="32" placeholder=""
                                       class="col-xs-10 col-sm-12" onkeydown="return banInputSapce(event);">

                                <div class="text-danger"><form:errors path="name"/></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-5"><i class="ii">*</i>部门:</label>

                            <div class="col-sm-8">
                                <input type="text" id="form-field-5" name="department" value="${formBean.department}"  maxlength="32"
                                       placeholder="" class="col-xs-10 col-sm-12" onkeydown="return banInputSapce(event);">

                                <div class="text-danger"><form:errors path="department"/></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-6">设定登陆范围:</label>

                            <div class="col-sm-8">
                                <input type="text" id="form-field-6" name="authenticateIP" maxlength="32" <%--minlength="4"--%>
                                       value="${formBean.authenticateIP}" class="col-xs-10 col-sm-12" onkeydown="return banInputSapce(event);">

                                <div class="text-danger"><form:errors path="authenticateIP"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-7">电话:</label>

                            <div class="col-sm-8">
                                <input type="text" id="form-field-7" name="telephone" value="${formBean.telephone}" maxlength="18" <%--minlength="4"--%>
                                       placeholder="" class="col-xs-10 col-sm-12" onkeydown="return banInputSapce(event);">

                                <div class="text-danger"><form:errors path="telephone"/></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-8"><i class="ii">*</i>角色:</label>

                            <div class="col-sm-8">
                                <select name="roleId" id="form-field-8" class="col-xs-10 col-sm-11">
                                    <c:forEach items="${roles}" var="item">
                                        <c:if test="${item.roleId==formBean.roleId}">
                                            <option value=${item.roleId} selected="selected">${item.roleName}</option>
                                        </c:if>
                                        <c:if test="${item.roleId!=formBean.roleId}">
                                            <option value=${item.roleId} >${item.roleName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <div class="text-danger"><form:errors path="roleId"/></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-9">备注:</label>
                            <div class="col-sm-8">
                                <textarea class=" " id="form-field-9" name="comment"  maxlength="128"<%-- minlength="0"--%> value="${formBean.comment}"
                                          rows="3" onkeydown="return banInputSapce(event);">${formBean.comment}</textarea>
                                <div class="text-danger"><form:errors path="comment"/></div>
                            </div>
                        </div>

                    </div>
                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-10"><i class="ii">*</i>Email:</label>

                            <div class="col-sm-8">
                                <input type="text" id="form-field-10" name="email" placeholder="" value="${formBean.email}" maxlength="32"
                                       class="col-xs-10 col-sm-12" onkeydown="return banInputSapce(event);">
                                <div class="text-danger"><form:errors path="email"/></div>
                            </div>
                        </div>
                        <div class="form-group hidden ">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-11"><i class="ii">*</i>地市归属:</label>

                            <div class="col-sm-8">
                                <select name="areaId" id="form-field-11" class="col-xs-10 col-sm-4">
                                    <c:forEach items="${areas}" var="item">
                                        <c:if test="${item.areaId==formBean.areaId}">
                                            <option value=${item.areaId} selected="selected">${item.areaName}</option>
                                        </c:if>
                                        <c:if test="${item.areaId!=formBean.areaId}">
                                            <option value=${item.areaId} >${item.areaName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <div class="text-danger"><form:errors path="areaId"/></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="form-field-12"><%--<i class="ii">*</i>--%>是否启用:</label>

                            <div class="col-sm-8">
                                <input type="checkbox" id="form-field-12" name="status"  />
                                <div class="text-danger"><form:errors path="status"/></div>
                            </div>
                            <input type="hidden" id="hidden" value="${formBean.status}">
                        </div>
                    </div>

                </div>
            </div>

            <div class="row">
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <input type="submit" value="确定" class="btn btn-xs btn-info"/>
                        &nbsp; &nbsp;
                        <a href="javascript:;" class="btn btn-xs btn-inverse" <%--type="reset"--%> onclick="resetAll();">
                            重置
                        </a>
                        &nbsp; &nbsp;
                        <a class="btn btn-xs btn-default removeBreadcrumb" href="${pageContext.request.contextPath}/f/um/user/list.view.do">
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

    function resetAll()
    {
        $("#basicInfo input").val("");
        $("#basicInfo textarea").val("");
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
