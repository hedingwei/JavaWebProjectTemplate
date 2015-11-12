<%@ tag import="java.util.UUID" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="number" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="hasunit" required="true" rtexprvalue="true"  description="" %>


<%@ tag pageEncoding="utf-8" %>

<%
    String _ruuid = UUID.randomUUID().toString().replace('-', '_');

%>

<label id="<%=_ruuid%>" value="${number}" >${number}</label>
<script type="text/javascript">



    $(function(){
        var hasunit="${hasunit}";
        var number=$("#<%=_ruuid%>").html();
        var str=null;
        var kb=1024;
        var mb=kb*1024;
        var gb=mb*1024;
        var tb=gb*1024;
        var pb=tb*1024;



        if("true"==("${hasunit}")){
            if(number.match(/[^0-9]+/g)==null) {
                number = parseFloat(number).toFixed(2);
                if (number < 1000) {
                    str = number + "B"
                }
                else if (number >= 1000 && number < kb * 1000) {
                    number = (number / kb).toFixed(2);
                    str = number + "KB"
                } else if (number >= kb * 1000 && number < mb * 1000) {
                    number = (number / mb).toFixed(2);
                    str = number + "MB"
                } else if (number >= mb * 1000 && number < gb * 1000) {
                    number = (number / gb).toFixed(2);
                    str = number + "GB"
                } else if (number >= gb * 1000 && number < tb * 1000) {
                    number = (number / tb).toFixed(2);
                    str = number + "TB"
                } else {
                    number = (number / pb).toFixed(2);
                    str = number + "PB"
                }
                $("#<%=_ruuid%>").html(str);

            }else{
                var num=number.replace(/[^0-9]+/g,"");
                var unit=number.replace(/[0-9]/g,"").toUpperCase();
                str=num+unit;
                $("#<%=_ruuid%>").html(str);
            }

        }else{
            var num=number.replace(/[^0-9]+/g,"");
            var unit=number.replace(/[0-9]/g,"").toUpperCase();
            /*alert("num:"+num);
            alert("unit:"+unit);*/
            if(unit=="") {
                str=num;

            }else if(unit=="B"){
                str = num;
            }else if(unit=="KB"){
                str=num*kb;

            }else if(unit=="MB") {
                str=num*mb;
            }else if(unit=="GB"){
                str=num*gb;

            }else if(unit=="TB"){
                str=num*tb;

            }else if(unit=="PB"){
                str=num*pb;

            }
            $("#<%=_ruuid%>").html(str);

        }



    });


</script>
















