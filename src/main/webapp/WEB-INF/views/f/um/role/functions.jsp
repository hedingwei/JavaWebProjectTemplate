<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css"/>

    <script src="${pageContext.request.contextPath}/resources/assets/js/ace-extra.min.js"></script>
    <style>
        .flotChart {
            height: 300px;
        }
    </style>

</head>

<body>

<div class="main-container" id="main-container">

    <div class="main-container-inner">

        <div class="">

            <div class="page-content">

                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <div class="row">

                            <div class="col-sm-12">
                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-flat">
                                        <h4 class="lighter">
                                            <i class="icon-signal"></i>
                                            为角色分配功能
                                        </h4>

                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="icon-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main padding-4">
                                            <div class="dataTables_wrapper">
                                                <form>

                                                    <ul>
                                                        <li>控制台 <input type="checkbox"/></li>

                                                        <li>
                                                            流量统计业务 <input type="checkbox"/>
                                                            <ul>
                                                                <li>策略管理 <input type="checkbox"/></li>
                                                                <li>统计报表 <input type="checkbox"/>

                                                                    <ul>
                                                                        <li>互联网应用分析 <input type="checkbox"/></li>
                                                                        <li>互联网内容分析 <input type="checkbox"/>


                                                                        </li>

                                                                    </ul>
                                                                </li>

                                                            </ul>
                                                        </li>

                                                        <li>1-N业务 <input type="checkbox"/></li>


                                                        <li>信息推送业务 <input type="checkbox"/></li>
                                                        <li>用户管理 <input type="checkbox"/>
                                                            <ul>
                                                                <li>用户一览 <input type="checkbox"/></li>
                                                                <li>角色管理 <input type="checkbox"/></li>
                                                                <li>分域管理 <input type="checkbox"/></li>
                                                                <li>操作日志 <input type="checkbox"/></li>
                                                            </ul>
                                                        </li>

                                                    </ul>

                                                    <input type="button" name="ddd" value="更新"/>
                                                </form>
                                            </div>
                                        </div>
                                        <!-- /widget-main -->
                                    </div>
                                    <!-- /widget-body -->
                                </div>
                                <!-- /widget-box -->
                            </div>
                        </div>

                        <!-- <div class="hr hr32 hr-dotted"></div> -->

                        <!-- PAGE CONTENT ENDS -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->

    </div>
    <!-- /.main-container-inner -->
</div>
<!-- /.main-container -->

<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>

<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/typeahead-bs2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.bootstrap.js"></script>

<script src="${pageContext.request.contextPath}/resources/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    jQuery(function ($) {



        <%--$('#basicTable').dataTable({--%>
        <%--"bSort": false,--%>
        <%--// "bLengthChange": false,--%>
        <%--"bFilter": false,--%>
        <%--"language": {--%>
        <%--"url": "${pageContext.request.contextPath}/resources/assets/js/Chinese.json"--%>
        <%--},--%>
        <%--"bProcessing": true,--%>
        <%--"sAjaxSource": "data/actions",--%>
        <%--"aoColumns":[--%>
        <%--{"sTitle": "用户名","width":"100px"},--%>
        <%--{"sTitle": "IP","width":"100px"},--%>
        <%--{"sTitle": "操作模块","width":"100px"},--%>
        <%--{"sTitle": "操作描述"},--%>
        <%--{"sTitle": "时间","width":"100px"}--%>
        <%--]--%>
        <%--});--%>

    });

</script>
</body>
</html>

