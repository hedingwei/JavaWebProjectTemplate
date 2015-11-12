<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>

<%@ attribute name="id" required="true" rtexprvalue="true" %>

<%@ attribute name="searchId" required="true" rtexprvalue="true" %>

<%
    {
        String m_var = UUID.randomUUID().toString().replace('-','_');
%>



<script>




    $(document).ready(function(){


        var h_${id} = $('._holder_aext_${id}').html();

        $('._holder_aext_${id}').remove();

        $('div[mtag=_s_action_ext_${searchId}').append(h_${id});



    });
</script>

<div class="_holder_aext_${id}" style="display: none">

    <jsp:doBody/>
</div>







<%
    }
%>