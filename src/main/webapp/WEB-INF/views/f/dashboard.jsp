<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="chart" tagdir="/WEB-INF/tags/chart" %>
<%@ taglib prefix="datepicker" tagdir="/WEB-INF/tags/datepicker" %>
<%@ taglib prefix="datetimepicker" tagdir="/WEB-INF/tags/datetimepicker" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>dashboard</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/plugin/datepicker/datepicker.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/plugin/bootstrap-datetimepicker/bootstrap-datetimepicker.css"/>

		<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/highcharts.js"></script>
		<script src="${pageContext.request.contextPath}/resources/assets/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/plugin/datepicker/bootstrap-datepicker.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/plugin/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>

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
								用户主页
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->



		<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/assets/js/typeahead-bs2.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>

		<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.bootstrap.js"></script>

		<script src="${pageContext.request.contextPath}/resources/assets/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">

			$(function(){

				$('#basicTable').dataTable({
	                "bSort": false,
	                // "bLengthChange": false,
	                "bFilter": false,
	                "language": {
	                    "url": "${pageContext.request.contextPath}/resources/assets/js/Chinese.json"
	                },
	                "bProcessing": true,
	                "sAjaxSource": "data/actions",
	                "aoColumns":[
	                    {"sTitle": "用户名","width":"100px"},
	                    {"sTitle": "IP","width":"100px"},
	                    {"sTitle": "操作模块","width":"100px"},
	                    {"sTitle": "操作描述"},
	                    {"sTitle": "时间","width":"100px"}
	                ]
	            });

			});
			
		</script>
	</body>
</html>

