<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="am" uri="http://pm.ambimmort.com/app/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/pagination" %>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/plugin/datepicker/datepicker.css">
	<style type="text/css">
		.form-actions{margin-top: 0; padding: 10px; margin-bottom: 0;}
		.mgl5 {margin-left: 5px;}
		.model-info1{ border-bottom: solid 1px #EEEEEE;}
		.model-info2{  margin-top:10px; border-bottom: 1px solid #EEEEEE;}
		#basicTable {width: 100%!important;}
		#basicTable_model tr td {word-break: break-all; word-wrap: break-word;}
		.modal{overflow-y: auto;}
	</style>
	<script src="${pageContext.request.contextPath}/resources/assets/js/Base64.js"></script>
</head>
<body>
<div class="main-container">
	<div class="tabbable">
		<form:form id="form" action="${pageContext.request.contextPath}/f/system/logs.do" method="get">
			<div class="">
				<div class="clearfix form-actions">

					<label>起始日期：</label>
					<input type="text" class="input-medium datepicker" id="date-start" name="startDate" value="${dateBean.startDate}" placeholder="" />

					&nbsp;
					<label>终止日期：</label>
					<input type="text" class="input-medium datepicker" id="date-end" name="endDate" value="${dateBean.endDate}" placeholder=""/>

					&nbsp;
					<input type="submit" class="btn btn-primary btn-xs" id="search" value="查询" />

				</div>
				<div id="" class="dataTables_wrapper">
					<table id="basicTable" class="table-primary table-bordered table-striped table-hover table-condensed">
						<thead>
						<tr>
							<td>ID</td>
							<td>时间</td>
							<td>操作区域</td>
							<td>子模块</td>
							<td>操作员</td>
							<td>IP</td>
							<td>cookies</td>
							<td>动作</td>
							<td>状态</td>
							<td>详情</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.id}</td>
								<td>${item.time}</td>
								<td>${item.areaname}</td>
								<td>${item.submodular}</td>
								<td>${item.username}</td>
								<td>${item.ip}</td>
								<td>${item.cookie}</td>
								<td>${item.action}</td>
								<td>${item.result}</td>
								<td><a class="mgl5" data-toggle="modal" data-target="#Modal" title="查看"
									   onclick="show_model('${item.action}','${item.result}','${item.newDetail}','${item.oldDetail}','${item.reason}')" ><i class="icon icon-search"></i> 查看详情</a></td>

							</tr>
						</c:forEach>
						</tbody>
					</table>
					<pagination:pagination targetForm="form" />
					<!-- 模态框（Modal） -->
					<div class="modal fade" id="Modal" tabindex="-1" role="dialog"
						 aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close"
											data-dismiss="modal" aria-hidden="true">
										&times;
									</button>
									<h4 class="modal-title" id="myModalLabel">
										详情描述
									</h4>
								</div>
								<div class="modal-body">


								</div>

							</div><!-- /.modal-content -->
						</div><!-- /.modal -->
					</div>

				</div>

					<%--</div>--%>

			</div>
		</form:form>
	</div>

</div>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugin/datepicker/bootstrap-datepicker.js"></script>

<script>

	function show_model(action,result,newDetail,oldDetail,reason) {
		console.log("newDetail: "+newDetail);
		console.log("oldDetail: "+oldDetail);
		console.log("reason: "+reason);
		console.log("action: "+action);
		var b=new Base64();


		var cont = '';
		if(result == "成功"){
			cont += "<table id='basicTable_model' class='table table-primary table-bordered table-striped table-condensed'>";
			cont += "	<tr>";
			cont += "		<td style='width: 40px;'>"+action+"</td>";
			cont += "<td >"+ b.decode(newDetail)+"</td>";
			cont += "	</tr>";
			if(oldDetail != ""){
				cont += "	<tr>";
				cont += "		<td style='width: 40px;'>旧值</td>";
				cont += "		<td>"+ b.decode(oldDetail)+"</td>";
				cont += "	</tr>";
			}
			cont += "</table>";
		}else{


			cont += "<table id='basicTable_model' class='table table-primary table-bordered table-striped table-condensed'>";
			cont += "	<tr>";
			cont += "		<td style='width: 40px;'>"+action+"</td>";
			cont += "<td >"+ b.decode(newDetail)+"</td>";
			cont += "	</tr>";
			cont += "	<tr>";
			cont += "		<td  style='width: 40px;'>失败原因</td>";
			cont += "		<td>"+ b.decode(reason)+"</td>";
			/* cont += "		<td>失败原因："+ b.decode(reason)+"</td>";*/
			cont += "	</tr>";
			cont += "</table>";
		}

		$(".modal-body").html(cont);
		$("#Model").show();

	}
	$(function(){
		var startDate, endDate = "";

		$("#date-start").datepicker({
			format:'yyyy/mm/dd',
			autoclose: true,
			todayHighlight: true,
			initialDate: new Date()
		}).on("changeDate",function(ev){
			$('#date-end').datepicker('setStartDate', $("#date-start").val());

		});

		$("#date-end").datepicker({
			format:'yyyy/mm/dd',
			autoclose: true,
			todayHighlight: true,
			initialDate: new Date()
		}).on("changeDate",function(ev){
			$('#date-start').datepicker('setEndDate', $("#date-end").val());

		});

	});

</script>
</body>
</html>