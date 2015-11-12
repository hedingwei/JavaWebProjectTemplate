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
		.formx{ margin-top: 15px !important;}
		.ii{ color: #F00; margin-right: 8px;font-family: "SimSun"; }
	</style>
</head>
<body>
<div class="main-container">
	<div class="col-xs-12">
		<div class="space-6"></div>
		<form:form role="form" name="AddRoleBean" commandName="formBean" action="${pageContext.request.contextPath}/f/um/role/edit.action.do" method="post">
			<div class="row">
				<input type="hidden" name="id" value="${formBean.id}"/>
				<div class="col-xs-4">
					<div class="form-group">
						<label class="col-lg-3 col-md-3 col-sm-3 control-label no-padding-right bolder blue" for="form-field-4" ><i class="ii">*</i>角色名称</label>

						<div class="col-lg-9 col-md-9 col-sm-9">
							<input class="input-sm" type="text" name="name" value="${formBean.name}" id="form-field-4"   maxlength="32" onkeydown="return banInputSapce(event);"  >
							<div class="text-danger">${msg}<form:errors path="name"/></div>

						</div>
					</div><br/>
					<div class="form-group formx">
						<label class="col-lg-3 col-md-3 col-sm-3 control-label no-padding-right bolder blue" for="form-field-4" >备注</label>
						<div class="col-lg-9 col-md-9 col-sm-9">
							<textarea   name="description"   rows="9" style=" resize: vertical;"  maxlength="128" onkeydown="return banInputSapce(event);"  value="${formBean.description}" >${formBean.description}</textarea>
						</div>
					</div>
				</div>


				<div class="col-xs-5">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right bolder blue" for="form-field-4" ><i class="ii">*</i>业务权限<br><%--<span style="color:red">${fun}</span>--%></label>

						<div class="col-lg-11 col-md-8 col-sm-9 ">
							<ul id="treeDemo" class="ztree"></ul>
							<input name="selectedFunctions" class="input-sm hide" type="text" id="hideForm" placeholder="">
							<div class="text-danger"><form:errors path="selectedFunctions"/></div>
						</div>

					</div>
				</div>

				<div class="row hide">
					<div class="col-xs-5">
						<div class="form-group">
							<div class="col-lg-11 col-md-8 col-sm-9 ">
								<input name="newFunctionsJson" class="input-sm" type="text" id="hideNewJson" placeholder="">
							</div>
						</div>
					</div>
				</div>

			</div>

			<div class="row">
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
							<%--<button type="submit"  id="btn"  class="btn btn-xs btn-info">确定</button>--%>
						<input type="submit"  id="btn" class="btn btn-xs btn-info" value="确定"/>
						&nbsp; &nbsp;
						<a class="btn btn-xs btn-default removeBreadcrumb" href="${pageContext.request.contextPath}/f/um/role/list.view.do">
							返回
						</a>
					</div>
				</div>
			</div>
		</form:form>
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
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/jquery.ztree.excheck-3.5.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/tree/jquery.ztree.exhide-3.5.min.js"></script>

<SCRIPT type="text/javascript">

	function setCheckFalse(oldJsonArray){

		$.each(oldJsonArray,function(index,element){
			element.checked = false;
			element.open = false;
			if(element.children.length != 0){
				setCheckFalse(element.children);
			}
		});
		return oldJsonArray;
	}

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

		/*var pattern = /\b\.do/;*/

		$.each(selectedNodes,function(i,e){

			/*if(pattern.test(e)){*/

				var targetNode = zTreeObj.getNodeByParam("path", e, null);

				if(targetNode.children.length==0){
				
					/* 让节点选中 */
					zTreeObj.checkNode(targetNode, true, true);

					/* 让选中节点的父节点展开 */
					var parentNode = targetNode.getParentNode();
					zTreeObj.expandNode(parentNode, true, false, true);
				
			}
			/*}*/

		});


		$("#btn").click(function () {
			var nodes = zTreeObj.getCheckedNodes();
			var uiSelectedFunctions = new Array();
			
			for(var node in nodes){
				if(nodes[node].path!=null)
				{
					uiSelectedFunctions.push(nodes[node].path);
					
				}
			}
			console.log(uiSelectedFunctions.join(","));

			$("#hideForm").val(uiSelectedFunctions.join(","));

			/* 获取当前用户权限jsonArray ---start---*/
			var unCheckedNodes = zTreeObj.getCheckedNodes(false);

			for (var i=0, l=unCheckedNodes.length; i < l; i++) {
				zTreeObj.removeNode(unCheckedNodes[i]);
			}

			var newNodes = setCheckFalse(zTreeObj.getNodes());

			$("#hideNewJson").val(JSON.stringify(newNodes));

//			console.log(newNodes);
			console.log(JSON.stringify(newNodes));

			/* 获取当前用户权限jsonArray ---end---*/

			document.roleForm.submit();
		});
	});

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
	//-->
</SCRIPT>
</body>
</html>


