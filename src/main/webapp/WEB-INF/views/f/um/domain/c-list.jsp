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
        </style>
    </head>
    <body>
        <div class="main-container">
            
            <div class="">
                <!-- <h3 class="header smaller lighter blue">区域管理</h3> -->
                <div class="">
                    <div class="clearfix form-actions">
                        <form class="form-inline">

                            <%--<div class="form-group">--%>
                                <%--<label>区域名:</label>--%>
                                <%--<input type="text" class="" id="" placeholder="">--%>
                                <%--<span class="text-danger">验证信息...</span>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label>区域描述:</label>--%>
                                <%--<input type="text" class="" id="" placeholder="">--%>
                                <%--<span class="text-danger">验证信息...</span>--%>
                            <%--</div>--%>

                            <a href="${pageContext.request.contextPath}/f/domain/c-add.do" class="btn btn-xs btn-info">创建</a>

                        </form>

                    </div>
                </div>

                <div class="">
                    <div id="" class="dataTables_wrapper">
                        <table id="basicTable" class="table table-primary table-bordered table-striped table-hover table-condensed dataTable">
                            <thead>
                            <tr>
                                <td>区域名</td>
                                <td>区域描述</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <td>三亚市</td>
                                <td>三亚市描述信息</td>
                                <td>
                                    <a class="mgl5" title="增加子区域" href="${pageContext.request.contextPath}/f/domain/c-add.do"><i class="icon icon-edit"></i> 修改区域名 </a>
                                    <a class="mgl5" title="查看子区域" href="${pageContext.request.contextPath}/f/domain/c-c-list.do"><i class="icon icon-search"></i> 查看子区域 </a>
                                    <a class="mgl5" href="javascript:;" title="删除区域"><i class="icon icon-trash"></i> 删除区域 </a>
                                </td>
                            </tr>

                            </tbody>

                        </table>
                    </div>
                </div>

                <%--<div class="">--%>
                    <%--<div class="table-header">--%>
                        <%--<b>海南省</b> 子区域列表--%>

                        <%--<div class="pull-right" style="margin-right: 5px;">--%>
                            <%--<a href="${pageContext.request.contextPath}/f/domain/list.do" class="btn btn-inverse btn-xs">--%>
                                <%--返回--%>
                            <%--</a>--%>

                        <%--</div>--%>


                    <%--</div>--%>
                    <%--<div id="" class="dataTables_wrapper">--%>
                        <%--<table id="basicTable" class="table table-primary table-bordered table-striped table-hover table-condensed dataTable">--%>
                            <%--<thead>--%>
                            <%--<tr>--%>
                                <%--<td>子区域名</td>--%>
                                <%--<td>子区域描述</td>--%>
                                <%--<td>操作</td>--%>
                            <%--</tr>--%>
                            <%--</thead>--%>
                            <%--<tbody>--%>

                                <%--<tr>--%>
                                    <%--<td>三亚市</td>--%>
                                    <%--<td>三亚市描述信息</td>--%>
                                    <%--<td>--%>
                                        <%--<a class="mgl5" title="增加子区域" href="${pageContext.request.contextPath}/f/domain/c-add.do"><i class="icon icon-plus"></i> 添加子区域 </a>--%>
                                        <%--<a class="mgl5" title="查看子区域" href="${pageContext.request.contextPath}/f/domain/c-c-list.do"><i class="icon icon-search"></i> 查看子区域 </a>--%>
                                        <%--<a class="mgl5" href="javascript:;" title="删除区域"><i class="icon icon-trash"></i> 删除区域 </a>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>


                            <%--</tbody>--%>

                        <%--</table>--%>
                    <%--</div>--%>
                <%--</div>--%>

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

        <SCRIPT type="text/javascript">

        
        </SCRIPT>
    </body>
</html>