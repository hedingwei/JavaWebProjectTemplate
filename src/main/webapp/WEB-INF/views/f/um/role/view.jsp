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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/zTreeStyle.css">
	<style type="text/css">
		.main-container label{font-size: 12px;}
		.col-md-offset-3 {margin-left: 8%!important;}
		.ztree li span.button.chk.checkbox_false_disable {background-position:0 0 !important;}
		.ztree li span.button.chk.checkbox_true_disable {background-position:-14px 0px !important;}
		.formx{ margin-top: 15px !important;}
	</style>
</head>
<body>
<div class="main-container">
	<div class="col-xs-12">

		<div class="space-8"></div>
		<div class="row">
			<div class="col-xs-4">
				<div class="form-group">
					<label class="col-lg-3 col-md-3 col-sm-3 control-label no-padding-right bolder blue" for="form-field-4" >角色名称</label>
					<div class="col-lg-9 col-md-9 col-sm-9">
						<input class="input-sm" type="text" id="form-field-4" value="${name}" placeholder="Default inpit" disabled>
						<div class="space-4"></div>
					</div>
				</div><br/>
				<div class="form-group formx">
					<label class="col-lg-3 col-md-3 col-sm-3 control-label no-padding-right bolder blue" for="form-field-4" >备注</label>
					<div class="col-lg-9 col-md-9 col-sm-9">
						<textarea placeholder="Default inpit"  rows="9" style=" resize: vertical;" readonly="readonly">${description}</textarea>
						<div class="space-4"></div>
					</div>
				</div>
			</div>
			<div class="col-xs-5">

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right bolder blue" for="form-field-4" >业务权限</label>
					<div class="col-lg-11 col-md-8 col-sm-9 ">


						<ul id="treeDemo" class="ztree"></ul>

						<input type="text" id="selected" class="hide" value="${json}">
					</div>
				</div>
			</div>



		</div>

		<div class="row">
			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">

					<a class="btn btn-xs btn-info removeBreadcrumb" href="${pageContext.request.contextPath}/f/um/role/list.view.do">
						返回
					</a>
				</div>
			</div>
		</div>
	</div>
	<%--<input type="text" name="" id="fun" value="${function}">&lt;%&ndash;<input type="text" name="" id="allfun" value="${json}">&ndash;%&gt;
	<textarea id="allfun" cols="4" > </textarea>--%>

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
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/jquery.ztree.excheck-3.5.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/jquery.ztree.exhide-3.5.min.js"></script>


<SCRIPT type="text/javascript">

	$(document).ready(function(){

		var zNodes = ${functions};
		var selectedString = "${function}";
		var selectedNodes = selectedString.split(",");

		var setting = {
			view:{
				showIcon:false
			},
			check: {
				enable:true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		
		$.each(selectedNodes,function(i,e){

				var targetNode = zTreeObj.getNodeByParam("path", e, null);

				if(targetNode.children.length==0){

						/* 让节点选中 */
						zTreeObj.checkNode(targetNode, true, true);

						/* 让选中节点的父节点展开 */
						var parentNode = targetNode.getParentNode();
						zTreeObj.expandNode(parentNode, true, false, true);
				   
			    }

		});

		/* 隐藏未check选中的节点 */
		var uncheckedNodes = zTreeObj.getCheckedNodes(false);
		zTreeObj.hideNodes(uncheckedNodes);

		/* 默认所有节点不可手动勾选 */
		var treeNodes = zTreeObj.transformToArray(zTreeObj.getNodes());
		for(var i= 0,l=treeNodes.length;i<l;i++){
			zTreeObj.setChkDisabled(treeNodes[i],true);
		}


	});
	//-->
</SCRIPT>
</body>
</html>