<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%--
  Created by IntelliJ IDEA.
  User: hedingwei
  Date: 6/17/15
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tags-search" tagdir="/WEB-INF/tags/search" %>
<%@ taglib prefix="tags-utils" tagdir="/WEB-INF/tags/utils" %>

<html>
<head>
    <title></title>
    <%--<meta http-equiv="refresh" content="30">--%>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- bootstrap 3.0.2 -->
    <link href="${pageContext.request.contextPath}/resources/adminlte/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/adminlte/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="${pageContext.request.contextPath}/resources/adminlte/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/resources/adminlte/css/AdminLTE.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css"/>

    <link href="${pageContext.request.contextPath}/resources/adminlte/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />

    <!-- jQuery 2.0.2 -->
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/resources/adminlte/js/respond.min.js"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/resources/assets/js/Base64.js"></script>

    <style>
        .table{ width:100% !important; font-size: 13px;}
    </style>

</head>
<body class="skin-blue">
<script type="text/javascript">

    var data1 = {
        starttime:{
            name:"启动时间",
            type:"datetime",
            operator:[{label:"等于",name:"e"},{label:"大于等于",name:"ge"},{label:"小于等于",name:"le"},{label:"不等于",name:"ne"}],
            value:{
                type:"select",
                options:[{name:1,label:"l1"},{name:2,label:"l2"},{name:3,label:"l3"}]
            }
        },
        endtime:{
            name:"终止时间",
            type:"datetime",
            operator:[{label:"等于",name:"e"},{label:"大于等于",name:"ge"},{label:"小于等于",name:"le"},{label:"不等于",name:"ne"}],
            value:{
                type:"select",
                options:[{name:1,label:"l1"},{name:2,label:"l2"},{name:3,label:"l3"}]
            }
        },
        startby:{
            name:"启动者",
            type:"string",
            operator:[{label:"等于",name:"e"},{label:"不等于",name:"ne"}],
            value:{
                type:"select",
                options:[{name:1,label:"system"},{name:2,label:"l2"},{name:3,label:"l3"}]
            }
        },
        result:{
            name:"执行结果",
            type:"string",
            operator:[{label:"等于",name:"e"},{label:"不等于",name:"ne"}],
            value:{
                type:"select",
                options:[{name:1,label:"l1"},{name:2,label:"l2"},{name:3,label:"l3"}]
            }
        }
    };

    var base64= new Base64();

    var cols = [
        {"title":"ID", "data": "id"},
        {"title":"启动时间", "data": "startTime"},
        {"title":"终止时间", "data": "endTime"},
        {"title":"结果", "data": "result"},
        {"title":"启动者", "data": "startby"},
        {
            "title":"启动备注",
            "data": "startComment",
            "render": function ( data, type, full, meta ) {
                return base64.decode(data);
            }
        },
        {
            "title":"终止备注",
            "data": "endComment",
            "render": function ( data, type, full, meta ) {
                return base64.decode(data);
            }

        },{
            "title":"操作",
            "data":"taskId",
            "render": function ( data, type, full, meta ) {
                return '<a href="${pageContext.request.contextPath}/f/lttask/manager/init.view.do?taskId='+data+'">查看</a>'
            }
        }
    ];


    function confirm_delete(){
        if (!confirm("确认要删除？")) {
            window.event.returnValue = false;
        }
    }

</script>





<div class="main-container" id="main-container">

    <div class="main-container-inner">

        <div class="container-fluid">

            <div class="row" >
                <div class="col-md-12">

                    <div class="widget-box">
                        <div class="widget-header">

                            <div class="pull-right">
                                <tags-search:btn-setting id="mysearch" pattern="data1">
                                    <tags-search:group>
                                        <tags-search:and>
                                            <tags-search:predicate name="startby" operator="等于" value="system"/>
                                            <tags-search:predicate name="starttime" operator="大于等于" value="2015-07-07"/>

                                        </tags-search:and>
                                    </tags-search:group>
                                </tags-search:btn-setting>
                                <tags-search:btn-search id="xs" tableId="table1" searchId="mysearch" url="/f/lttask/manager/loglist1.json.do?taskId=${taskId}" columns="cols"/>
                            </div>
                        </div><!-- /.box-header -->
                        <div class="widget-body no-padding">
                            <div class="widget-main">

                                <div class="row" >
                                    <div class="alert ">

                                        <b>查询条件：</b>
                                        <tags-search:description searchId="mysearch"/>
                                    </div>
                                </div>

                                <div class="row" >
                                    <div class="col-md-12">

                                        <tags-search:table id="table1" sclass="table table-primary table-bordered table-striped table-hover table-condensed"/>
                                    </div>

                                </div>

                            </div>

                        </div><!-- /.box-body -->
                    </div>

                </div>
            </div>
        </div>

    </div>

</div>




</body>
</html>
