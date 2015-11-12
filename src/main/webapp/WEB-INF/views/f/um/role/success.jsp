<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>创建成功</title>
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
        #basicTable {width: 100%!important;}
        .form-group {margin-bottom: 5px;}
      .widget-body .table{border-bottom: 1px solid #e5e5e5 !important;}
      .col-sm-7{margin:0 auto !important; margin-left:310px !important;}
    </style>
</head>
<body>

   <div class="main-container">
    <div class="page-content">
        <div class="row">
            <div class="col-sm-7">
                <div class="widget-box transparent">
                     <!-- <div class="widget-header widget-header-flat">
                        <h2 class="lighter" style="color:green;">
							<i class="icon-ok green"></i>
								<b>创建成功！</b>
						</h2>
						     <!  <div class="widget-header widget-header-flat">
                        <h4 class="lighter">
                            帐号属性
                        </h4>
    </div> -->
    <div class="alert alert-block alert-success">

                                            <p class="bigger-230">
                                                <strong class="">
                                                    <i class="icon-ok"></i>
                                                    创建成功!
                                                </strong>
                                                
                                            </p>

                                        </div>

    </div> 

                    <div class="widget-body">
                        <div class="widget-main no-padding">

                            <table class="table table-bordered table-striped">
                                <thead class="thin-border-bottom" style="border-top: 1px solid #e5e5e5;">
                                <tr>
                                    <th width="250" >
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
                                    <td>角色名</td>
                                    <td>
                                        ${model.name}
                                    </td>
                                </tr>
                                <tr>
                                    <td>备注</td>
                                    <td>
                                        ${model.description}
                                    </td>
                                </tr>
                                <tr>
                                    <td>业务权限-报表查看</td>
                                    <td>
                                       
                                    </td>
                                </tr>

                                <tr>
                                    <td>业务权限-配置管理</td>
                                    <td>
                                       
                                    </td>
                                </tr>

                                <tr>
                                    <td>业务权限-报表查看</td>
                                    <td>
                                       
                                    </td>
                                </tr>
                                
                                </tbody>
                            </table>
                            <div style="margin: 10px;">
                            <a type="button" class="btn btn-sm bnt-danger" href="${pageContext.request.contextPath}/f/um/role/list.view.do">返回</a>
                        </div>
                      
                    </div>


<!--       <div class="widget-header widget-header-flat">
                        <h4 class="lighter">
                            基本信息
                        </h4>
    </div> -->
     

                       
                    
                           
                     
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>