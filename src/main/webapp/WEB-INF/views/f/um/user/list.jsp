<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户列表</title>
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
		.table{ width:100% !important;}
		.form-actions{margin-top: 0; padding: 0px; margin-bottom: 0;}
		/*.mgl5 {margin-left: 5px;}*/
		.btn{float:right !important; margin-right: 20px !important;}
	</style>
</head>
<body>
<script>
	function check(){
		if(document.getElementById("hidden").value=='true'){
			document.getElementById("form-field-12").checked=true;
		}else{
			document.getElementById("form-field-12").checked=false;
		}
	}

</script>

<div class="main-container" onload="check">
	<div class="">
		<!-- <h3 class="header smaller lighter blue">角色管理</h3> -->
		<div class="">
			<div class="clearfix form-actions">
				<a class="btn btn-success btn-xs appendBreadcrumb" href="${pageContext.request.contextPath}/f/um/user/add.view.do">新增用户</a>
			</div>
		</div>
		<div class="">
			<div id="" class="dataTables_wrapper">
				<table id="basicTable" class="table table-primary table-bordered table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <td>用户名</td>
                            <td>姓名</td>
                            <td>角色</td>
                            <td>区域</td>
                            <td>部门</td>
                            <td>邮件</td>
                            <td>是否启用</td>
                            <td>操作</td>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="item">

                        <tr>
                            <td>${item.username}</td>
                            <td>${item.name}</td>
                            <td>${item.roleName}</td>
                            <td>${item.areaName}</td>
                            <td>${item.department}</td>
                            <td>${item.email}</td>
                            <td><c:if test="${!item.status}">否</c:if>
								 <c:if test="${item.status}">是</c:if></td>
                            <td>
								<a class="mgl5 appendBreadcrumb once"  title="编辑" href="${pageContext.request.contextPath}/f/um/user/edit.view.do?username=${item.username}"><i class="icon icon-edit"></i> 编辑 </a>
								<a class="mgl5"  href="${pageContext.request.contextPath}/f/user/delete.action.do?username=${item.username}" title="删除" onclick='del(this);return false'><i class="icon icon-trash"></i> 删除 </a>
								<a class="mgl5 appendBreadcrumb"   title="密码重置" href="${pageContext.request.contextPath}/f/um/user/resetPassword.view.do?username=${item.username}"><i class="icon icon-edit"></i> 重置密码 </a>
								<c:if test="${!item.status}"><a class="start" title="启用" href="${pageContext.request.contextPath}/f/user/setStatus.action.do?username=${item.username}&&areaId=${item.areaId}&&flag=true"><i class="icon icon-play"></i> 启用 </a></c:if>
								<c:if test="${item.status}"><a class="stop" title="停止" href="${pageContext.request.contextPath}/f/user/setStatus.action.do?username=${item.username}&&areaId=${item.areaId}&&flag=false"><i class="icon icon-stop"></i> 停止 </a></c:if>
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

	function del(obj) {
		var c=confirm("确定要删除该用户吗？");
		if(c) {
			window.location=$(obj).attr("href");
		}
	}


	var hrefa="";

	$(".once").one("click",function(){

	    hrefa=$(this).attr('href');
	    return true;

	});


	$('.once').click(function(){
		/*alert($(this).attr('href'));*/
		setTimeout(function(){$(this).attr('href', hrefa);alert("3秒后删除样式！");alert($(this).attr('href'));},3000);//延迟3秒后，把判断样式删除。
		 return true;
		});


	$(function(){
	});

</script>


</body>
</html>