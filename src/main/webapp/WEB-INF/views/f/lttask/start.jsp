<%--
  Created by IntelliJ IDEA.
  User: hedingwei
  Date: 6/20/15
  Time: 1:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css"/>

    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery-2.0.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/typeahead-bs2.min.js"></script>


    <script src="${pageContext.request.contextPath}/resources/assets/js/fuelux/fuelux.wizard.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/additional-methods.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.maskedinput.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/select2.min.js"></script>


    <script src="${pageContext.request.contextPath}/resources/assets/js/ace-elements.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/ace.min.js"></script>


    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>

</head>
<body class="skin-blue">


<div class="main-container" id="main-container">

        <div class="page-content">


                    <div class="row">
                        <div class="col-md-6">
                            <!-- Danger box -->
                            <div class="panel">
                                <div class="panel-header">
                                    <h3 class="panel-title">启动任务确认</h3>

                                </div>
                                <div class="panel-body">
                                    <p>
                                        启动该任务需要填写必要的启动备注，请注明启动的原因。
                                    </p>
                                    <form:form action="${pageContext.request.contextPath}/f/lttask/manager/start.action.do?taskId=${param.taskId}" commandName="startBean">
                                        <p><form:textarea id="editor1"  path="message" cssClass="textarea" placeholder="请在此填写启动该任务的原因" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"/></p>
                                        <p><form:errors path="message"/></p>
                                        <p><input type="submit" class="btn btn-danger btn-sm pull-right" value="启动"/>
                                            <a href="${pageContext.request.contextPath}/f/lttask/manager/init.view.do" class="btn btn-primary btn-sm pull-right removeBreadcrumb">取消</a>
                                        </p>
                                    </form:form>
                                    <p>操作人：${requestScope.x_m_username}</p>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->


        </div>

</div>




</body>
</html>
