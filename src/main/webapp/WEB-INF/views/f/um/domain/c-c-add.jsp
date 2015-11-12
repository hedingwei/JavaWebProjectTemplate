<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- basic styles -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/zTreeStyle.css">
    <style type="text/css">
        .form-actions{margin-top: 0; padding: 10px; margin-bottom: 0; padding: 10px;}
        .mgl5 {margin-left: 5px;}
        .form-horizontal .form-group {margin-left: 0; margin-right: 0;}
    </style>
</head>
<body>
<div class="main-container">

    <div class="">
        <%--<h3 class="header smaller lighter blue">海南区域</h3>--%>
        <div class="">
            <div class="space-6"></div>
            <%--<div class="clearfix form-actions">--%>
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-xs-12 col-sm-3 no-padding-right">子区域名:</label>
                    <div class="col-xs-12 col-sm-9">
                        <div class="clearfix">
                            <input type="text" class="col-xs-12 col-sm-6 valid" id="" name="" maxlength="32" onkeydown="return banInputSapce(event);">
                        </div>
                        <div class="text-danger">This field is required.</div>
                    </div>

                </div>

                <div class="form-group">
                    <label class="control-label col-xs-12 col-sm-3 no-padding-right">子区域描述:</label>
                    <div class="col-xs-12 col-sm-9">
                        <div class="clearfix">
                            <textarea id="comment" name="comment" class="input-xlarge" maxlength="128" onkeydown="return banInputSapce(event);"></textarea>
                        </div>
                        <div class="text-danger">This field is required.</div>
                    </div>

                </div>

                <div class="col-md-offset-3 col-sm-offset-3 col-md-9 col-sm-9">
                    <button type="submit" class="btn btn-xs btn-info">创建</button>

                    &nbsp; &nbsp; &nbsp;
                    <a href="${pageContext.request.contextPath}/f/domain/c-c-list.do" class="btn btn-default btn-xs removeBreadcrumb">
                        返回
                    </a>
                </div>

            </form>

            <%--</div>--%>
        </div>


    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate-util.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/jquery.ztree.core-3.5.js"></script>

<script>

    function banInputSapce(e)
    {
        var keynum;
        if(window.event) // IE
        {
            keynum = e.keyCode
        }
        else if(e.which) // Netscape/Firefox/Opera
        {
            keynum = e.which
        }
        if(keynum == 32){
            return false;
        }
        return true;
    }
</script>
</body>
</html>