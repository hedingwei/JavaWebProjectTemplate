<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>

<%@ attribute name="id" required="false" rtexprvalue="true" %>

<%@ attribute name="searchId" required="true" rtexprvalue="true" %>


<%
    {
        String m_var = UUID.randomUUID().toString().replace('-','_');
%>

<c:if test="${id==null}">
    <div id="<%=m_var%>" mtag="search_dscription_${searchId}">
    </div>
</c:if>

<c:if test="${id!=null}">
    <div id="${id}" mtag="search_dscription_${searchId}">
    </div>
</c:if>

<%
    }
%>