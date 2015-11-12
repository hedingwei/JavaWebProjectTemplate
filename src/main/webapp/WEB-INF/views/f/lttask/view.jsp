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

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>



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

    <!-- jQuery 2.0.2 -->
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/bootstrap.min.js" type="text/javascript"></script>

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
                <div class="col-sm-6">
                    <div class="box box-solid ">
                        <div class="box-header">
                            <h3 class="box-title">长时任务日志:${task_model.name}

                                <c:if test="${task_model.status=='success'}">
                                    <i class="fa fa-fw fa-check"></i>
                                </c:if>

                                <c:if test="${task_model.status!='success'}">
                                    <i class="fa fa-fw fa-warning"></i>
                                </c:if>


                            </h3>


                        </div>
                        <div class="box-body">
                            <c:if test="${task_model.activator=='system'}">
                                <p>启动人:系统任务调度器  启动时间: <am:timestamp-format timestamp="${task_model.startTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                            </c:if>

                            <c:if test="${task_model.activator!='system'}">
                                <p>启动人:${task_model.activator} 启动时间:<am:timestamp-format timestamp="${task_model.startTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                            </c:if>

                            <c:if test="${task_model.status!='success'}">
                                <div class="alert alert-danger alert-dismissable">
                                    <i class="fa fa-ban"></i>

                                    <b>警告</b>
                                    <p>
                                    任务执行失败
                                    <c:if test="${task_model.canceller!='system'}">
                                        (任务被${task_model.canceller}在<am:timestamp-format timestamp="${task_model.stopTime}" format="yyyy-MM-dd HH:mm:ss"/>人为终止)
                                    </c:if>

                                    <c:if test="${task_model.canceller=='system'}">
                                        (任务被任务调度器在<am:timestamp-format timestamp="${task_model.stopTime}" format="yyyy-MM-dd HH:mm:ss"/>终止)
                                    </c:if>
                                </p>
                                </div>

                                <div class="alert alert-info alert-dismissable">
                                    <i class="fa fa-info"></i>
                                    <b>失败原因</b>
                                    <p>终止原因：<am:decode-base64 var="${task_model.cancelReason}"/></p>
                                </div>
                            </c:if>

                            <c:if test="${task_model.status=='success'}">

                                <div class="alert alert-success alert-dismissable">
                                    <i class="fa fa-check"></i>
                                    <b>执行成功</b> 该任务在<am:timestamp-format timestamp="${task_model.stopTime}" format="yyyy-MM-dd HH:mm:ss"/>成功执行完毕。
                                </div>
                            </c:if>

                            <p>${task_model.description}</p>
                            <%--<p>该任务存在子任务，任务数为：${task_model.context.subtaskSequence.size()}</p>--%>

                            <div class="box">
                                <%--<div class="box-header ">--%>
                                    <%--<h3 class="box-title">任务分步一览</h3>--%>
                                <%--</div><!-- /.box-header -->--%>
                                <div class="box-body no-padding">

                                    <c:if test="${task_model.context.subtaskSequence.size()!=0}">


                                        <table class="table table-condensed">
                                            <tbody><tr>
                                                <th style="width: 10px">#</th>
                                                <th>步骤</th>
                                                <th>执行进度</th>
                                                <th style="width: 40px">百分比</th>
                                            </tr>

                                            <c:forEach items="${task_model.context.subtaskModels}" var="subtask" varStatus="status">
                                                <tr>
                                                    <td>${status.index+1}</td>
                                                    <td>${subtask.name}</td>
                                                    <td>
                                                        <div class="progress xs progress-striped active">
                                                            <div class="progress-bar progress-bar-success" style="width: ${subtask.progress}%"></div>
                                                        </div>
                                                    </td>
                                                    <td><span class="badge bg-red">${subtask.progress}%</span></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </c:if>
                                </div><!-- /.box-body -->
                            </div>

                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div>
                <div class="col-sm-6">
                    <div class="box box-solid">
                        <div class="box-header">
                            <h3 class="box-title">任务日志详单</h3>
                        </div>
                        <div class="box-body">
                            <div class="box-group" id="accordion">
                                <c:if test="${task_model.logs.size()!=0}">
                                    <div class="panel box box-primary ">
                                        <div class="box-header">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapsed">
                                                主任务日志
                                            </a>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse collapse" style="height: 0px;">
                                            <div class="box-body">
                                                <ol>
                                                    <c:forEach items="${task_model.logs}" var="log">
                                                        <li>
                                                            <p><am:decode-base64 var="${log}"/></p>
                                                        </li>
                                                    </c:forEach>
                                                </ol>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>


                                <c:if test="${task_model.context.subtaskSequence.size()!=0}">

                                        <c:forEach items="${task_model.context.subtaskModels}" var="subtask" varStatus="step">

                                            <c:if test="${subtask.status=='success'}">

                                                <div class="panel box box-success box-solid">
                                                    <div class="box-header">
                                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${step.index+1}" class="collapsed">
                                                                ${step.index+1}. ${subtask.name} 成功完成
                                                        </a>
                                                    </div>
                                                    <div id="collapse${step.index+1}" class="panel-collapse collapse">
                                                        <div class="box-body">
                                                            <p>任务描述：${subtask.description}</p>
                                                            <p>任务状态：${subtask.status}</p>
                                                            <p>任务开始时间：<am:timestamp-format timestamp="${subtask.startTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                                                            <p>任务结束时间：<am:timestamp-format timestamp="${subtask.stopTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                                                            <p>任务是否被取消：${subtask.cancelled}</p>
                                                            <p>任务被取消原因：<am:decode-base64 var="${subtask.cancelReason}"/></p>
                                                            <p>任务被谁启动：${subtask.activator}</p>
                                                            <p>任务被谁终止：${subtask.canceller}</p>
                                                            <p>任务执行ID：${subtask.id}</p>
                                                            <p>任务日志：
                                                            <ol>
                                                                <c:forEach items="${subtask.logs}" var="log">
                                                                    <li>
                                                                        <p><am:decode-base64 var="${log}"/></p>
                                                                    </li>
                                                                </c:forEach>
                                                            </ol>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>

                                            <c:if test="${subtask.status!='success'}">
                                                <div class="panel box box-danger box-solid">
                                                    <div class="box-header">
                                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${step.index+1}" class="collapsed">
                                                                ${step.index+1}. ${subtask.name} 执行失败
                                                        </a>
                                                    </div>
                                                    <div id="collapse${step.index+1}" class="panel-collapse collapse">
                                                        <div class="box-body">
                                                            <p>任务描述：${subtask.description}</p>
                                                            <p>任务状态：${subtask.status}</p>
                                                            <p>任务开始时间：<am:timestamp-format timestamp="${subtask.startTime}" format="yyyy-MM-dd HH:mm:ss"/></p>

                                                            <p>任务结束时间：<am:timestamp-format timestamp="${subtask.stopTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                                                            <p>任务是否被取消：${subtask.cancelled}</p>
                                                            <p>任务被取消原因：<am:decode-base64 var="${subtask.cancelReason}"/></p>
                                                            <p>任务被谁启动：${subtask.activator}</p>
                                                            <p>任务被谁终止：${subtask.canceller}</p>
                                                            <p>任务执行ID：${subtask.id}</p>
                                                            <p>任务日志：
                                                            <ol>
                                                                <c:forEach items="${subtask.logs}" var="log">
                                                                    <li>
                                                                        <p><am:decode-base64 var="${log}"/></p>

                                                                    </li>
                                                                </c:forEach>
                                                            </ol>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <!-- /widget-box -->
                </div>
            </div>
        </div>

    </div>

</div>



</body>
</html>
