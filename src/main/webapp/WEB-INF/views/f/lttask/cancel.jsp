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
    <!-- basic styles -->
    <%--<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css"/>--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css"/>--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css"/>--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css"/>--%>
    <%--<script src="${pageContext.request.contextPath}/resources/assets/js/ace-extra.min.js"></script>--%>

    <!-- jQuery 2.0.2 -->
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- AdminLTE App -->



    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- bootstrap 3.0.2 -->
    <link href="${pageContext.request.contextPath}/resources/adminlte/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/adminlte/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="${pageContext.request.contextPath}/resources/adminlte/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/resources/adminlte/css/AdminLTE.css" rel="stylesheet" type="text/css" />

    <link href="${pageContext.request.contextPath}/resources/adminlte/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/respond.min.js"></script>
    <![endif]-->


</head>
<body class="skin-blue">


<div class="main-container" id="main-container">

    <div class="main-container-inner">

        <div class="container-fluid">

            <div class="row">
                <!-- Main content -->
                <section class="content">

                    <div class="row">
                        <div class="col-md-6">
                            <!-- Danger box -->
                            <div class="box box-solid bg-red">
                                <div class="box-header">
                                    <h3 class="box-title">人工终止任务需要确认</h3>

                                </div>
                                <div class="box-body">
                                    <p>
                                        人工终止该任务需要填写必要的启动备注，请注明启动的原因。
                                    </p>
                                    <form:form action="${pageContext.request.contextPath}/f/lttask/manager/cancel.action.do?taskId=${param.taskId}" commandName="startBean">
                                        <p><form:textarea id="editor1"  path="message" cssClass="textarea" placeholder="请在此填写终止该任务的原因" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"/></p>
                                        <p><form:errors path="message"/></p>
                                        <p><input type="submit" class="btn btn-danger btn-sm pull-right" value="终止"/>
                                            <a href="${pageContext.request.contextPath}/f/lttask/manager/status.view.do?taskId=${param.taskId}" class="btn btn-primary btn-sm pull-right">取消</a>
                                        </p>
                                    </form:form>
                                    <p>操作人：${requestScope.x_m_username}</p>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->

                </section><!-- /.content -->
            </div>
        </div>
    </div>
</div>




</body>
</html>
