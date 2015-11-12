<%@ tag import="java.util.UUID" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="format" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="autoclose" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="todayBtn" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="pickerPosition" required="true" rtexprvalue="true" description="" %>


<%@ tag pageEncoding="utf-8" %>

<%
    String _ruuid = UUID.randomUUID().toString().replace('-', '_');
%>

<input type="text" class="input-medium datepicker" id="<%=_ruuid%>"  />


<script type="text/javascript">

            (function(){

                $("#<%=_ruuid%>").datepicker(
                    {format:"${format}"},
                    {autoclose: ${autoclose}},
                    {todayBtn: ${todayBtn}},
                    {pickerPosition:"${pickerPosition}"}
            
                    );
            })();

</script>





   
        









