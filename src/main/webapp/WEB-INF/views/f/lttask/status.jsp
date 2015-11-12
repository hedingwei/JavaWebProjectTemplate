<%--
  Created by IntelliJ IDEA.
  User: hedingwei
  Date: 6/17/15
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <%--<c:if test="${isRunning}">--%>
    <%--<meta http-equiv="refresh" content="2">--%>
    <%--</c:if>--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>



    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- bootstrap 3.0.2 -->
    <%--<link href="${pageContext.request.contextPath}/resources/adminlte/css/bootstrap.min.css" rel="stylesheet" type="text/css" />--%>
    <%--<!-- font Awesome -->--%>
    <%--<link href="${pageContext.request.contextPath}/resources/adminlte/css/font-awesome.min.css" rel="stylesheet" type="text/css" />--%>
    <%--<!-- Ionicons -->--%>
    <%--<link href="${pageContext.request.contextPath}/resources/adminlte/css/ionicons.min.css" rel="stylesheet" type="text/css" />--%>
    <%--<!-- Theme style -->--%>
    <%--<link href="${pageContext.request.contextPath}/resources/adminlte/css/AdminLTE.css" rel="stylesheet" type="text/css" />--%>

    <%--<link href="${pageContext.request.contextPath}/resources/adminlte/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />--%>

    <!-- jQuery 2.0.2 -->
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/bootstrap.min.js" type="text/javascript"></script>


    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css">




    <script type="application/javascript">


        function getModeldata(){
            $.post("${pageContext.request.contextPath}/f/lttask/manager/status.json.do",{taskId:'${param.get("taskId")}'},function(data){
                if(data.hasTask){
                    var main_desc = data.task_model.name+"总体进度: "+data.task_model.progress+"%   <div class=\"box-tools pull-right\"> 已经执行:"+(data.task_model.elapseTime/1000).toFixed(0)+"秒       预计剩余时间:"+(data.task_model.remainTime/1000).toFixed(0)+'秒  <a  href="${pageContext.request.contextPath}/f/lttask/manager/cancel.view.do?taskId=${param.taskId}">取消</a></div>';
                    $("#main_desc").html(main_desc);
                    updateProgress("progress_main",data.task_model);
                    var subtasks = data.task_model.context.subtaskModels;
                    for(var index in subtasks){
                        console.log(index);
                        updateProgress("progress_"+subtasks[index].id,subtasks[index]);
                    }
                }else{
                    window.location.reload();
                }
            });
        }

        function updateProgress(progressId,task_model){
            $("#"+progressId).find("div[ptag=m1]").attr("aria-valuenow",task_model.progress);
            $("#"+progressId).find("div[ptag=m1]").attr("style","width:"+task_model.progress+"%;");
            $("#"+progressId).find("span[ptag=m2]").html(task_model.progress+"% 完成");
        }

        $(document).ready(function(){
            setInterval(getModeldata,1000);
        });
    </script>
</head>
<body>




<div class="main-container" id="main-container">

    <div class="main-container-inner">

        <div class="container-fluid">
            <row>
                <div class="col-md-12">
                    <div class="widget-box transparent">
                        <div class="widget-header widget-header-flat">
                            <h4 class="lighter">

                                <p id="main_desc">${task_model.name}总体进度: ${task_model.progress}%

                                </p>

                            </h4>
                            <div id="progress_main" class="progress">
                                <div ptag="m1" class="progress-bar xs progress-striped active" role="progressbar" aria-valuenow="${task_model.progress}" aria-valuemin="0" aria-valuemax="100" style="width: ${task_model.progress}%;">
                                    <span ptag="m2" class="label-transparent">${task_model.progress}% 完成</span>
                                </div>
                            </div>

                        </div>
                        <div class="widget-body">
                            <div class="widget-main padding-4">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <c:if test="${(task_model.context.subtaskSequence!=null)&&(task_model.context.subtaskSequence.size()!=0)}">
                                            <p>该任务存在${task_model.context.subtaskSequence.size()}个子任务</p>
                                            <ol>
                                                <c:forEach items="${task_model.context.subtaskModels}" var="subtask">

                                                    <li>
                                                        <p>任务名称：${subtask.name}</p>

                                                        <p>任务描述：${subtask.description}</p>

                                                        <div id="progress_${subtask.id}" class="progress">
                                                            <div ptag="m1" class="progress-bar  progress-striped active progress-warning" role="progressbar" aria-valuenow="${subtask.progress}" aria-valuemin="0" aria-valuemax="100" style="width: ${subtask.progress}%;">
                                                                <span ptag="m2" class="label-transparent">${subtask.progress}% 完成</span>
                                                            </div>
                                                        </div></p>
                                                    </li>
                                                </c:forEach>
                                            </ol>
                                        </c:if>

                                    </div>
                                </div>

                            </div>
                            <!-- /widget-main -->
                        </div>
                        <!-- /widget-body -->
                    </div>
                </div>
            </row>


        </div>

    </div>
</div>


</body>

</html>
