<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ tag pageEncoding="utf-8" %>


<%@ attribute name="when" required="true" rtexprvalue="true" %>


<%
    {
        String id=UUID.randomUUID().toString();
        id = id.replace('-','_');
%>

<div id="_block_<%=id%>">
    <textarea id="_hidden_script_<%=id%>" hidden="hidden" ><jsp:doBody/></textarea>

    <script type="text/javascript">
        var fstr = 'var _task_callback_<%=id%>=function(data){'+$('#_hidden_script_<%=id%>').val()+';};';
        eval(fstr);
        var ttagId = $("#_block_<%=id%>").parent().find("input[atag='id']").val();
        var ttaskId = $("#_block_<%=id%>").parent().find("input[atag='taskId']").val();

        $("#_block_<%=id%>").parent().find('input[atag=${when}]').attr("value","_task_callback_<%=id%>");

    </script>
</div>

<%
    }
%>

