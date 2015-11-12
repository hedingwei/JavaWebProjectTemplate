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

    <div class="main-container-inner">

        <div class="page-content">

            <c:if test="${((!isRunning)&&(!isUnRegisteredTask))}">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="alert alert-info alert-dismissable">
                            <i class="fa fa-info"></i>
                            <b>该任务当前未执行, 如果需要可以
                                <a  href="${pageContext.request.contextPath}/f/lttask/manager/start.view.do?taskId=${param.taskId}" class="appendBreadcrumb">手动触发执行</a>,
                                也可以返回<a  href="${pageContext.request.contextPath}/f/lttask/manager/list.view.do" class="appendBreadcrumb">长时任务管理中心</a>
                            </b>

                        </div>
                    </div>

                </div>
            </c:if>

            <c:if test="${((!isRunning)&&(isUnRegisteredTask))}">
                <div class="row">
                    <div class="col-md-10">
                        <div class="alert alert-info alert-dismissable">
                            <i class="fa fa-info"></i>
                            <b>该任务当前未执行, 如果需要可以
                                    <a  href="${pageContext.request.contextPath}/f/lttask/manager/start.view.do?taskId=${param.taskId}" class="appendBreadcrumb">手动触发执行</a>,
                                也可以返回<a  href="${pageContext.request.contextPath}/f/lttask/manager/list.view.do" class="appendBreadcrumb">长时任务管理中心</a>
                            </b>

                        </div>
                    </div>

                </div>
            </c:if>

            <c:if test="${!hasHistoryTask}">
                <div class="row">
                    <div class="col-sm-12 col-lg-offset-0">
                        <div class="alert alert-info alert-dismissable">
                            <i class="fa fa-info"></i>
                            <b>该任务还没有被执行过, 如果需要可以
                                <a  href="${pageContext.request.contextPath}/f/lttask/manager/start.view.do?taskId=${param.taskId}" class="appendBreadcrumb">手动触发执行</a>,
                                也可以返回<a  href="${pageContext.request.contextPath}/f/lttask/manager/list.view.do" class="appendBreadcrumb">长时任务管理中心</a>
                            </b>
                        </div>
                    </div>

                </div>
            </c:if>

            <c:if test="${isUnRegisteredTask}">
                <div class="row">
                    <div class="col-sm-12 col-lg-offset-0">
                        <div class="alert alert-danger alert-dismissable">
                            <i class="fa fa-info"></i>
                            <b>注意：该任务不是一个注册了的合法任务。
                            </b>
                        </div>
                    </div>

                </div>
            </c:if>

            <c:if test="${hasHistoryTask&&(param.isUnRegisteredTask==null)}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel ">
                            <div class="panel-heading">
                                <h5 class="panel-title">最近一次执行日志：${task_model.name}</h5>
                                <div class="pull-left">
                                    <c:if test="${task_model.activator=='system'}">
                                        <p>启动者:系统任务调度器  启动时间: <am:timestamp-format timestamp="${task_model.startTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                                        <br/>
                                    </c:if>
                                    <c:if test="${task_model.activator!='system'}">
                                        <p>启动人:${task_model.activator} 启动时间:<am:timestamp-format timestamp="${task_model.startTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                                        <br/>
                                    </c:if>
                                </div>
                                
                            </div>
                            <div class="box-body">




                                <c:if test="${(task_model.context.subtaskSequence!=null)&&(task_model.context.subtaskSequence.size()!=0)}">
                                    <div class="row">

                                        <div class="col-md-12">
                                            <div class="accordion-style1 panel-group">
                                                <h5>分步执行情况详情：</h5>
                                                <c:forEach items="${task_model.context.subtaskModels}" var="subtask" varStatus="step">

                                                    <c:if test="${subtask.status=='success'}">

                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">
                                                                <div class="panel-title">
                                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse${step.index+1}" class="accordion-toggle">
                                                                            ${step.index+1}. ${subtask.name} 成功完成
                                                                    </a>
                                                                </div>

                                                            </div>
                                                            <div id="collapse${step.index+1}" class="panel-collapse collapse">
                                                                <div class="box-body">
                                                                    <p>任务描述：${subtask.description}</p>
                                                                    <p>任务状态：执行完毕</p>

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

                                                    <c:if test="${subtask.status=='ready'}">
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">
                                                                <div class="panel-title">
                                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse${step.index+1}" class="accordion-toggle">
                                                                            ${step.index+1}. ${subtask.name} 未执行
                                                                    </a>
                                                                </div>

                                                            </div>
                                                            <div id="collapse${step.index+1}" class="panel-collapse collapse">
                                                                <div class="box-body">
                                                                    <p>任务描述：${subtask.description}</p>

                                                                    <c:if test="${subtask.status=='ready'}">
                                                                        <p>任务状态：未执行</p>
                                                                    </c:if>

                                                                    <p>任务开始时间：<am:timestamp-format timestamp="${subtask.startTime}" format="yyyy-MM-dd HH:mm:ss"/></p>

                                                                    <p>任务结束时间：<am:timestamp-format timestamp="${subtask.stopTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                                                                    <p>任务是否被取消：${subtask.cancelled}</p>
                                                                    <p>任务被取消原因：<am:decode-base64 var="${subtask.cancelReason}"/> </p>
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
                                                    <c:if test="${subtask.status=='cancelled'}">
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">
                                                                <div class="panel-title">
                                                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion"  href="#collapse${step.index+1}" >
                                                                            ${step.index+1}. ${subtask.name} 执行失败
                                                                    </a>
                                                                </div>

                                                            </div>
                                                            <div id="collapse${step.index+1}" class="panel-collapse collapse">
                                                                <div class="box-body">
                                                                    <p>任务描述：${subtask.description}</p>

                                                                    <c:if test="${subtask.status=='cancelled'}">
                                                                        <p>任务状态：执行失败</p>
                                                                    </c:if>
                                                                    <p>任务开始时间：<am:timestamp-format timestamp="${subtask.startTime}" format="yyyy-MM-dd HH:mm:ss"/></p>

                                                                    <p>任务结束时间：<am:timestamp-format timestamp="${subtask.stopTime}" format="yyyy-MM-dd HH:mm:ss"/></p>
                                                                    <p>任务是否被取消：${subtask.cancelled}</p>
                                                                    <p>任务被取消原因：<am:decode-base64 var="${subtask.cancelReason}"/> </p>
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
                                            </div>
                                        </div>

                                    </div>
                                </c:if>

                            </div><!-- /.box-body -->
                        </div><!-- /.box -->
                    </div>
                </div>
            </c:if>



        </div>

    </div>

</div>




</body>
</html>
