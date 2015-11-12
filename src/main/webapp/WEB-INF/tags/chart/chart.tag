<%@ tag import="java.util.UUID" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="type" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="title" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="categories" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="srcdata" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="height" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="ytitle" required="true" rtexprvalue="true" description="" %>
<%@ attribute name="tooltipvalueSuffix" required="true" rtexprvalue="true" description="" %>

<%@ tag pageEncoding="utf-8" %>
<%--
    type类型：line/bar/spline/column/area/areaspline
--%>
<%
    String _ruuid = UUID.randomUUID().toString().replace('-', '_');
%>


    <div id="<%=_ruuid%>" class="flotChart" style="height:${height};">
        <script type="text/javascript">

            (function(){

                $("#<%=_ruuid%>").highcharts({
                    chart:{
                        type:'${type}'
                    },
                    title: {
                        text: '${title}'
                    },
                    subtitle: {
                        text: ''
                    },
                    credits: {
                        enabled: false
                    },
                    xAxis: {
                        categories: ${categories}
                    },
                    yAxis: {
                        title: {
                            text: '${ytitle}'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        valueSuffix: '${tooltipvalueSuffix}'
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: ${srcdata}
                });

            })();



        </script>
    </div>










