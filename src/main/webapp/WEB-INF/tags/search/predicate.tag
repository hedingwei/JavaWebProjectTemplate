<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>

<%@ attribute name="name" required="true" rtexprvalue="true" description="no need to add a '#'"%>

<%@ attribute name="value" required="false" rtexprvalue="true" description="no need to add a '#'"%>

<%@ attribute name="operator" required="false" rtexprvalue="true" description="no need to add a '#'"%>

<%
    {

    String xid = UUID.randomUUID().toString().replace('-','_');


%>


<tr id="<%=xid%>" style="vertical-align:middle" mtag="_s_condition_row">
    <td id="_t_name_<%=xid%>" mtag="fieldLabel" style="vertical-align:middle">

    </td>
    <td style="vertical-align:middle">
        <select id="_t_select_<%=xid%>">
        </select>
    </td >
    <td style="vertical-align:middle">
        <input id="_t_input_<%=xid%>" type="text" name="${name}" value="${value}"/>
    </td>
    <td style="vertical-align:middle">
        <a mtag="condition_remove"><i class="fa fa-fw fa-minus-square-o "></i></a>
    </td>
</tr>

<script>

    $(document).ready(function(){
        (function(){
            var idx = '${name}';

            var strVar = "";

            var ans = $("#_t_name_<%=xid%>").parent().parent().parent().parent().parent().parent().parent().parent();

            var x = $(ans).attr("mtag");
            var y = ans.find("div[mtag=_s_selections]").attr("id");

            var searchId = $("#<%=xid%>").closest("div[mtag=_s_searchbox]").attr("mtagId");
            var _pattern = window[ window[searchId].pattern];


            if(x==y){

                console.log(idx);
                console.log(_pattern);

                <%--if('datetime'==_pattern[idx]["type"]){--%>
                    <%--$("#_t_input_<%=xid%>").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});--%>
                <%--}--%>
                $('#_t_name_<%=xid%>').html(_pattern[idx].name);
                _pattern[idx].operator.forEach(function(entry){
                    if(entry.label=='${operator}'){
                        strVar += "            <option value='"+entry.name+"' selected>"+entry.label+"<\/option>";
                    }else{
                        strVar += "            <option value='"+entry.name+"'>"+entry.label+"<\/option>";
                    }

                });

                $('#_t_select_<%=xid%>').html(strVar);


            }
        })();
    });


</script>

<%
    }
%>