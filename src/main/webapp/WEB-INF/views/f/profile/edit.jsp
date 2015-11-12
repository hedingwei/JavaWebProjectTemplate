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
                   action="${pageContext.request.contextPath}/f/profile/edit.action.do" method="post">
            <div id="Userinformation">
                <div>
                    <h5 class="lighter green">账号属性</h5>
                </div>

                <div class="row">

                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group  ">
                            <label class="col-sm-4 control-label no-padding-right">用户名:</label>

                            <div class="col-sm-8">
                                <label class="col-xs-10  col-sm-12 no-padding-right label_1" name="username" >${formBean.username}</label>
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

                <div class="row" id="basicInfo1">


                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label no-padding-right" for="name"><i class="ii">*</i>姓名:</label>

                            <div class="col-sm-8">
                                <input type="text" id="name" name="name" value="${formBean.name}"  maxlength="32"
                                       class="col-xs-10 col-sm-12" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">

                                <div class="text-danger"><form:errors path="name"/></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="department"><i class="ii">*</i>部门:</label>

                            <div class="col-sm-8">
                                <input type="text" id="department" name="department" value="${formBean.department}"  maxlength="32"
                                       class="col-xs-10 col-sm-12" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">

                                <div class="text-danger"><form:errors path="department"/></div>
                            </div>
                        </div>

                    </div>
                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="telephone">电话:</label>

                            <div class="col-sm-8">
                                <input type="text" id="telephone" name="telephone" value="${formBean.telephone}" maxlength="18"
                                       placeholder="" class="col-xs-10 col-sm-12" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">

                                <div class="text-danger"><form:errors path="telephone"/></div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" for="comment">备注:</label>
                            <div class="col-sm-8">
                                <textarea class=" " id="comment" name="comment" maxlength="128"   value="${formBean.comment}"
                                          rows="3" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">${formBean.comment}</textarea>
                                <div class="text-danger"><form:errors path="comment"/></div>
                            </div>
                        </div>

                    </div>
                    <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label no-padding-right" for="email"><i class="ii">*</i>Email:</label>

                            <div class="col-sm-8">
                                <input type="text" id="email" name="email" placeholder="" value="${formBean.email}"  maxlength="32"
                                       class="col-xs-10 col-sm-12" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
                                <div class="text-danger"><form:errors path="email"/></div>
                            </div>
                        </div>

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
    function resetAll()
    {
        $("#basicInfo1  input").val("");
        $("#basicInfo1  textarea").val("");
    }
</script>
</body>
</html>

