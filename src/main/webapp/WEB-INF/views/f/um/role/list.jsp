﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/datatables/css/dataTables.bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/datatables/css/dataTables.responsive.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/datatables/css/style.datatables.css">
	<style type="text/css">
		.form-actions{margin-top: 0; padding: 0px; margin-bottom: 0;}
		.btn{float:right !important; margin-right: 20px !important;}
		.mgl5 {margin-left: 5px;}

	</style>
</head>
<body>
<div class="main-container">
	<div class="">
		<!-- <h3 class="header smaller lighter blue">角色管理</h3> -->
		<div class="">
			<div class="clearfix form-actions">
				<a id="add_role_btn" class="btn btn-success btn-xs appendBreadcrumb" href="${pageContext.request.contextPath}/f/um/role/add.view.do">创建角色</a>
			</div>
		</div>
		<div class="">
			<div id="" class="dataTables_wrapper">
				<table id="basicTable" class="table table-primary table-bordered table-striped table-hover table-condensed">
					<thead>
					<tr>
						<td>角色名称</td>
						<td>创建者</td>
						<td>备注</td>
						<td>操作</td>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="item">

						<tr>
							<input class="hidden" type="text" value="${item.username}">
							<input class="hidden" type="text" value="${item.t_code}">
							<td>${item.name}</td>
							<td>${item.creater}</td>
							<td>${item.description}</td>

							<td>
								<a class="mgl5 appendBreadcrumb" title="查看用户权限" href="${pageContext.request.contextPath}/f/um/role/view.view.do?id=${item.id}"><i class="icon icon-search"></i> 查看用户权限 </a>
								<c:if test="${item.username==item.creater || item.t_code=='100000000' }"> <a class="mgl5 appendBreadcrumb" title="编辑" href="${pageContext.request.contextPath}/f/um/role/edit.view.do?id=${item.id}" ><i class="icon icon-edit"></i> 编辑 </a></c:if>
								<c:if test="${item.username!=item.creater && item.t_code!='100000000'}"> <a class="mgl5 appendBreadcrumb " title="编辑" href="${pageContext.request.contextPath}/f/um/role/edit.view.do?id=${item.id}" onclick="EditUnable(this);return false;"><i class="icon icon-edit"></i> 编辑 </a></c:if>
								<c:if test="${item.username==item.creater || item.t_code=='100000000'}"><a class="mgl5" href="${pageContext.request.contextPath}/f/um/role/delete.action.do?id=${item.id}" title="删除" onclick='del(this);return false'><i class="icon icon-trash"></i> 删除 </a></c:if>
								<c:if test="${item.username!=item.creater && item.t_code!='100000000'}"><a class="mgl5" href="${pageContext.request.contextPath}/f/um/role/delete.action.do?id=${item.id}" title="删除" onclick="EditUnable(this);return false;"><i class="icon icon-trash"></i> 删除 </a></c:if>
							</td>

						</tr>
					</c:forEach>
					</tbody>

				</table>
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

<script>

	function EditUnable(obj){
			alert("您不是该角色的创建者，不能对该角色进行编辑和删除！");
		$(obj).css("color","#ccc");
	}


	$(function(){

		$('#basicTable').dataTable({
			"bPaginate":true,
			"bSort": false,
			"bLengthChange": false,
			"bFilter": false,
			"language": {
				"url": "${pageContext.request.contextPath}/resources/assets/js/Chinese.json"
			},
			"bProcessing": true
		});

	});


	function del(obj){
		var c=confirm("确定要删除该角色吗？");
		if(c){
			window.location=$(obj).attr("href");
		}
	}
</script>
</body>
</html>