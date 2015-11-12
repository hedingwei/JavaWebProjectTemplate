<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ tag import="net.sf.json.JSONObject" %>
<%@ tag import="net.sf.json.JSONArray" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>


<%@ attribute name="var" required="true" rtexprvalue="true" %>



<%
    {
        String m_var = UUID.randomUUID().toString().replace('-','_');
%>


    <jsp:doBody var="_x_y_z" scope="request"/>



    <%

        JSONArray obj = JSONArray.fromObject(request.getAttribute("_x_y_z"));

        request.setAttribute(var,obj);
    %>

<script>
    $(document).ready(function(){
        window['${var}']=<%=request.getAttribute("_x_y_z")%>    ;
    });
</script>



<%
    }
%>