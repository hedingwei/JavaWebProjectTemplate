<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>nisp3</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css" />
	<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>

</head>

<body>
<div class="main-container">
	<div class="page-content">
		<div class="row">
			<div class="">
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							系统配置
						</h4>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">

							<table class="table table-bordered table-striped">
								<thead class="thin-border-bottom">
								<tr>
									<th width="300">
										<i class="icon-caret-right blue"></i>
										键（key）
									</th>
									<th>
										<i class="icon-caret-right blue"></i>
										值（value）
									</th>
								</tr>
								</thead>
								<tbody id="confgTable">
								<tr>
									<td>物料服务器地址设置</td>
									<td>
										<input type="text" class="input-xlarge config-txt" id="server_port" name="" key="server.port" />
									</td>
								</tr>
								</tbody>
							</table>
						</div>
						<div style="margin: 10px;">
							<button type="button" class="btn btn-sm bnt-danger" onclick="undo();">取消</button>
							<button type="button" class="btn btn-sm btn-primary" onclick="save();">保存</button>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/ace.min.js"></script>
</body>
</html>