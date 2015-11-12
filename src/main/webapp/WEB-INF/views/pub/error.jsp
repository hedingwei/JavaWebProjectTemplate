<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>操作失败</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<meta http-equiv="Refresh" content="5; url=${pageContext.request.contextPath}${redirectURL}" />--%>
    <!-- basic styles -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/datatables/css/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/datatables/css/dataTables.responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/datatables/css/style.datatables.css">
    <style type="text/css">
        .form-actions{margin-top: 0; padding: 10px; margin-bottom: 0;}
        .mgl5 {margin-left: 5px;}
        #basicTable {width: 100%!important;}
        .form-group {margin-bottom: 5px;}
        .page-content{padding:15px 20px 20px !important;}
        .alert{margin-bottom:0px !important;}
        .widget-body{ padding:10px !important;}
        .widget-body .table{border-bottom: 1px solid #e5e5e5 !important; }
        .col-sm-7{margin:0 auto !important; margin-left:0px !important;}
    </style>
</head>
<body>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
<div class="main-container">
    <div class="page-content">
        <div class="row">
            <div class="col-sm-7">
                <div class="widget-box transparent">

                    <div class="alert alert-block alert-success">

                        <p class="bigger-230">
                            <strong class="">
                                <i class="icon-info-sign"> 操作失败！</i>
                            </strong>

                        </p>

                    </div>

                </div>

                <div class="widget-body">
                    <div class="widget-main no-padding">

                        <p class="bigger-180">
                            <strong class="">
                                ${message}
                            </strong>

                        </p>

                        <div style="margin: 10px;">
                            <%--<input type="hidden" id="url" value="${pageContext.request.contextPath}${redirectURL}")/>--%>
                            <%--<button type="button" class="btn btn-sm bnt-danger" onclick="undo();">返回</button>--%>
                            <a class="btn btn-xs btn-default removeBreadcrumb1" href="${pageContext.request.contextPath}${redirectURL}">
                                返回
                            </a>
                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>

</div>
</div>
</body>
</html>