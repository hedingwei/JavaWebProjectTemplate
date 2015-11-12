<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%--
  Created by IntelliJ IDEA.
  User: hedingwei
  Date: 6/17/15
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
    <%--<meta http-equiv="refresh" content="30">--%>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

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

    <style type="text/css">
        .table{ width:100% !important; font-size: 13px;}
        .table thead>tr>th, .table tbody>tr>th, .table tfoot>tr>th, .table thead>tr>td, .table tbody>tr>td, .table tfoot>tr>td{
            vertical-align: middle;
        }
        .form-actions{margin-top: 0; padding: 0px; margin-bottom: 0;}
        /*.mgl5 {margin-left: 5px;}*/
        .btn{float:right !important; margin-right: 20px !important;}
    </style>
</head>
<body class="skin-blue">


<div class="main-container" id="main-container">

    <div class="main-container-inner">

        <div class="page-content">

            <div class="row">
                <div class="col-md-12">
                    <div class="widget-box transparent">
                        <div class="widget-header">
                            <h5 class="panel-title">长时任务管理中心</h5>
                        </div><!-- /.panel-header -->
                        <div class="widget-body">

                            <div class="widget-main">

                                <table class="table table-primary table-bordered table-striped table-hover table-condensed">
                                    <thead class="thin-border-bottom">
                                    <tr>
                                        <th style="width: 10px">#</th>

                                        <th><i class="icon-caret-right green"></i> 任务</th>
                                        <th><i class="icon-caret-right green"></i> 总执行次数</th>
                                        <th><i class="icon-caret-right green"></i> 当前状态</th>
                                        <th><i class="icon-caret-right green"></i> 当前进度</th>
                                        <th><i class="icon-caret-right green"></i> 计划任务</th>
                                        <th><i class="icon-caret-right green"></i> 下次执行时间</th>

                                        <th style="width: 60px"><i class="icon-caret-right green"></i> 操作</th>
                                    </tr>
                                    </thead>
                                <tbody>
                                <c:forEach items="${lttasks}" varStatus="i" var="taskBean">
                                    <tr>
                                        <td>${i.index+1}.</td>

                                        <td> <a href="${pageContext.request.contextPath}/f/lttask/manager/init.view.do?taskId=${taskBean.taskId}">${taskBean.name}</a></td>
                                        <td>${taskBean.totalTimes}</td>
                                        <td><span class="badge bg-red">${taskBean.status}</span></td>
                                        <td>
                                            <div class="progress progress-nimi active">
                                                <div class="progress-bar progress-bar-primary" style="width: ${taskBean.progress}%"></div>
                                            </div>
                                        </td>
                                        <td><span class="badge badge-yellow">${taskBean.cron}</span></td>
                                        <td><am:timestamp-format timestamp="${taskBean.nextScheduledTime}" format="yyyy-MM-dd HH:mm:ss"/></td>


                                        <td>
                                            <a href="${pageContext.request.contextPath}/f/lttask/manager/loglist.view.do?taskId=${taskBean.taskId}" class="appendBreadcrumb">日志</a></td>
                                    </tr>

                                </c:forEach>

                                </tbody>
                            </table>

                            </div>
                        </div><!-- /.panel-body -->
                    </div>
                </div>

            </div>
        </div>

    </div>

</div>




</body>
</html>
