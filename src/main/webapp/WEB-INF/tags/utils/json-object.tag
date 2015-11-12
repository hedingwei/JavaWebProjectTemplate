<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ tag import="net.sf.json.JSONObject" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>


<%@ attribute name="var" required="true" rtexprvalue="true" %>


    <jsp:doBody var="_x_y_z" scope="request"/>
    <%
        JSONObject obj = JSONObject.fromObject(request.getAttribute("_x_y_z"));
        request.setAttribute(var,obj);
    %>
    <script>
        $(document).ready(function(){
            window['${var}']=<%=request.getAttribute("_x_y_z")%> ;
        });
    </script>


