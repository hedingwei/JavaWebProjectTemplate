<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>

<%@ attribute name="taskId" required="true" rtexprvalue="true" description="no need to add a '#'"%>
<%@ attribute name="frequency" required="true" rtexprvalue="true" %>
<%@ attribute name="modelVar" required="false" rtexprvalue="true" %>

<%
    {
        String id = UUID.randomUUID().toString();
        id = id.replace('-','_');
%>

<div id="_am_tag_task_progress_<%=id%>" atag="_task-progress">
    <input atag="id" value="<%=id%>" hidden="hidden">
    <input atag="taskId" value="${taskId}" hidden="hidden">
    <input atag="onFinished" value="" hidden="hidden">
    <input atag="onProgress" value="" hidden="hidden">
    <input atag="taskModel" value="" hidden="hidden">

<c:if test="${param._task_isFinishedSuccessfully}">
    <p>任务已成功执行完毕</p>
    <%
            {
            AbstractTask task = ((LTTaskManager) Application.getBean("longTimeTaskRunner")).getLastTask(taskId);
    %>
        <c:if test="${modelVar==null}">
            <%
                request.setAttribute("_task_model",task.getModel());
            %>
        </c:if>

    <c:if test="${modelVar!=null}">
        <%
            request.setAttribute(modelVar,task.getModel());
        %>
    </c:if>

    <%
        }
    %>

    <div class="progress">
        <div id="t_progress_id_${taskId}_<%=id%>" class="progress-bar  progress-striped active progress-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
            <span id="t_progress_span_id_${taskId}_<%=id%>" class="label-transparent">100%</span>
        </div>
    </div>
    <jsp:doBody/>

    <script type="text/javascript">
        $(document).ready(function(){

            (function(){
                var tname = $("#_am_tag_task_progress_<%=id%>").find("input[atag='onFinished']").val();
                if(tname!=null && tname.length !=0 ){
                    window[tname](response);
                }

            })();
        });

    </script>



</c:if>

<c:if test="${param._task_isFinishedSuccessfully==null||(!param._task_isFinishedSuccessfully)}">
    <p>任务正在进行中，请稍后</p>
    <div class="progress">
        <div id="t_progress_id_${taskId}_<%=id%>" class="progress-bar  progress-striped active progress-primary" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
            <span id="t_progress_span_id_${taskId}_<%=id%>" class="label-transparent">0%</span>
        </div>
    </div>
    <jsp:doBody/>

</c:if>



<script type="text/javascript">

    function isFinished(){
        return ${(param._task_isFinishedSuccessfully!=null&&param._task_isFinishedSuccessfully)||(param._task_isFinishedFailed!=null)}
    }

    function getTaskInfo()
    {

        $.post('${pageContext.request.contextPath}/f/lttask/manager/task.json.do',
                {lttid:'${taskId}'},
                function(response) {
                    if (response.hasTask == false) {
                        console.log("notask");
                    } else {
                        $('#t_progress_id_${taskId}_<%=id%>').attr("aria-valuenow",response.progress);
                        $('#t_progress_id_${taskId}_<%=id%>').attr("style","width:"+response.progress+"%;");
                        $('#t_progress_span_id_${taskId}_<%=id%>').html(response.progress+"%");

                        (function(){
                            var tname = $("#_am_tag_task_progress_<%=id%>").find("input[atag='onProgress']").val();
                            if(tname!=null && tname.length !=0 ){
                                window[tname](response);
                            }

                        })();

                        if(response.status=='success'&&response.progress==100){
                                window.location.href = window.location.href+"&_task_isFinishedSuccessfully=true"
                        }else if(response.status=='cancelled'||response.status=='failed'){
                            window.location.href = window.location.href+"&_task_isFinishedSuccessfully=false&&_task_isFinishedFailed=true"
                        }

                    }
                },
                'json');
    }
    $(document).ready(function() {
        if(!isFinished()){
            window.setInterval(getTaskInfo, ${frequency});
        }

    });
</script>

</div>

<%
    }
%>