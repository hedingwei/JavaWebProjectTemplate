<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>



<%
        {
        String id = UUID.randomUUID().toString().replace('-','_');
        %>


<td grouptag="<%=id%>">
<table class="table" mtag="_s_and_table">
    <tbody>
        <jsp:doBody/>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><a mtag="condition_add" data-toggle="modal" data-target="#myModal"><i class="fa fa-fw fa-plus-square-o"></i></a></td>
        </tr>
    </tbody>
</table>
</td>
<td grouptag="<%=id%>" style="vertical-align:middle">
<a mtag="group_remove" grouptag="<%=id%>" ><i class="fa fa-fw fa-minus-square "></i></a>

</td>

<%
        }
        %>
