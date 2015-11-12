<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>

<%@ attribute name="id" required="true" rtexprvalue="true" %>
<%@ attribute name="sclass" required="false" rtexprvalue="true" %>



<c:if test="${sclass==null}">
    <div id="dtwrapper_${id}">
        <table id="${id}" class="table table-primary table-bordered table-striped table-hover table-condensed">
        </table>
    </div>
</c:if>


<c:if test="${sclass!=null}">
    <div id="dtwrapper_${id}">
        <table id="${id}" class="${sclass}">

        </table>
    </div>
</c:if>


