<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>操作日志列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	</style>
</head>
<body>
<div class="main-container">
	<div class="tabbable">
		<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
			<li>
				<a href="${pageContext.request.contextPath}/f/user/logs.do">用户管理记录</a>
			</li>

			<li class="active">
				<a href="javascript:;">角色管理记录</a>
			</li>

			<li>
				<a href="${pageContext.request.contextPath}/f/domain/logs.do">分域管理记录</a>
			</li>
		</ul>

		<div class="">
			<div id="home4" class="tab-pane in active">
				<div id="" class="dataTables_wrapper">
					<table id="basicTable" class="table table-primary table-bordered table-striped table-hover table-condensed">
						<thead>
						<tr>
							<td>操作用户名</td>
							<td>操作时间</td>
							<td>动作</td>
							<td>操作对象</td>
						</tr>
						</thead>
						<tbody>

						<tr>
							<td>admin</td>
							<td>2015-05-05 15:55</td>
							<td>编辑</td>
							<td>角色1</td>
						</tr>

						</tbody>

					</table>
				</div>

			</div>

		</div>

	</div>

</div>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/datatables/js/dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/datatables/js/dataTables.responsive.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate-util.js"></script>




<%--<script>--%>
	<%--$(function(){--%>

		<%--$('#basicTable').dataTable({--%>
			<%--"bSort": false,--%>
			<%--"bLengthChange": false,--%>
			<%--"bFilter": false,--%>
			<%--"language": {--%>
				<%--"url": "${pageContext.request.contextPath}/resources/assets/js/Chinese.json"--%>
			<%--},--%>
			<%--"bProcessing": true,--%>
			<%--"sAjaxSource": "${pageContext.request.contextPath}/resources/assets/data/user",--%>
			<%--"aoColumns":[--%>
				<%--{"sTitle": "用户名"},--%>
				<%--{"sTitle": "姓名"},--%>
				<%--{"sTitle": "角色"},--%>
				<%--{"sTitle": "区域"},--%>
				<%--{"sTitle": "部门"},--%>
				<%--{"sTitle": "email"},--%>
				<%--{--%>
					<%--"sTitle": "操作",--%>
					<%--"targets": [6],--%>
					<%--"render": function(data,type,full,meta){--%>
						<%--var actionStr = '';--%>
							<%--actionStr += '<a class="mgl5" title="编辑" href="${pageContext.request.contextPath}/f/user/edit.do"><i class="icon icon-edit"></i> </a> ';--%>
							<%--actionStr += '<a class="mgl5" href="javascript:;" title="删除"><i class="icon icon-trash"></i> </a>'--%>
						<%--return actionStr;--%>
					<%--}--%>
				<%--}--%>
			<%--]--%>
		<%--});--%>

	<%--});--%>
<%--</script>--%>

</body>
</html>