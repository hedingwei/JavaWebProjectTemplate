<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>坤腾畅联分布式爬虫平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css"/>
    <script type="text/javascript">

    </script>
    <style>
        .slimScrollDiv {width: 250px; float: left;}
        #sidebar {width: 250px!important;}
        .navbar {
            background: none repeat scroll 0 0 rgba(30, 59, 115, 0.8)!important;
        }
        .ace-nav>li.grey>a {
            background-color: rgba(126, 156, 155, 0);
        }
        .ace-nav>li.grey>a:hover, .ace-nav>li.grey>a:focus {
            background-color: rgba(126, 156, 155, 0.3);
        }

    </style>
</head>


<body style="overflow-y:hidden;">
<div class="navbar navbar-default" id="navbar" >

    <div class="navbar-container" id="navbar-container" >
        <div class="navbar-header pull-left" >
            <a href="#" class="navbar-brand">
                <small style="font-family: microsoft yahei; font-size:70%; ">
                    <!--<i class="icon-leaf"></i>-->
                    <img src="${pageContext.request.contextPath}/resources/assets/images/x.png" style="height:30px;" />
                    坤腾畅联分布式爬虫平台
                </small>
            </a><!-- /.brand -->
        </div>
        <!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="grey">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo"
                             src="${pageContext.request.contextPath}/resources/assets/avatars/user.png"
                             alt="Admin's Photo"/>
                                <span class="user-info">
                                    <small>欢迎光临</small>
                                    <strong id="username">${current}</strong>
                                </span>
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

                        <li>
                            <a href="${pageContext.request.contextPath}/f/profile/edit.view.do" target="content" onclick="breadcrumb_init(this);">
                                <i class="icon-user"></i>
                                个人信息
                            </a>
                        </li>
                        <li class="divider"></li>


                        <li>
                            <a href="${pageContext.request.contextPath}/f/pub/profile/resetpwd.view.do" target="content" onclick="breadcrumb_init(this);">
                                <i class="icon-edit"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>


                        <li>
                            <a href="${pageContext.request.contextPath}/j_spring_security_logout">
                                <i class="icon-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- /.ace-nav -->
        </div>
        <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">

        </div>

        <div class="main-content">
            <div class="breadcrumbs">
                <ul id="breadcrumbs" class="breadcrumb">
                    <li>当前位置：</li>
                    <li></li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <iframe src="" name="content" id="content" width="100%"  style="border: none;"></iframe>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->
    </div>
    <!-- /.main-container-inner -->
    <%--${nav}--%>
    <%--<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">--%>
    <%--<i class="icon-double-angle-up icon-only bigger-110"></i>--%>
    <%--</a>--%>
</div>
<!-- /.main-container -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery_index.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/typeahead-bs2.min.js"></script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>

<!-- index -->
<%--<script src="${pageContext.request.contextPath}/f/navigation.do"></script>--%>
<script src="${pageContext.request.contextPath}/resources/assets/js/gnsuite.js"></script>
<!--<script src="assets/js/gnsuite_1.js"></script>-->

<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>
<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/ace.min.js"></script>


<script>

    /* 获取iframe可视区域高度 */
    function refreshContentHeight(){
        var frame = document.getElementById('content');
        var contentHeight = $(window).height() - 90;
//                win = frame.contentWindow,
//                doc = win.document,
//                html = doc.documentElement,
//                body = doc.body;
//        frame.setAttribute('height', 0);
//        var height = Math.max(body.scrollHeight, html.scrollHeight) + 50;
        frame.setAttribute('height', contentHeight);
    }




    $(function () {

        var name = window.location.hash;
        $.nav.initNav({
            containerSelector: $(".sidebar"),
            breadcrumbSelector: $("#breadcrumbs"),
            targetFrame:"content",
            ulClass: ["nav nav-list","submenu","submenu"],
            data: ${nav}
        });

        $("#sidebar").slimScroll({
            height:$(window).height()-10 + "px"
        });

//        refreshContentHeight();
        $(window).resize(function(){
            refreshContentHeight();
            $("#sidebar").css("height",$(window).height()-10 +"px");
            $(".slimScrollDiv").css("height",$(window).height()-10 +"px");

        });
        $("#content").load(function(){
            refreshContentHeight();

        });


    });

    function logout() {
        var flag = window.confirm("确认退出？");
        if (flag) {
            $.post("user/login_out", {user: $("#username").text()}, function (data) {
                window.location.assign("");
            });
        }
    }

    function  breadcrumb_init(object)
    {
        var obj = [{
            name: $(object).text(),
            href: $(object).attr("href"),
            target: $(object).attr("target")
        }];
        $.nav.initBreadcrumb(obj);

    }

    if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
            var hashLocation = location.hash;
            var hashSplit = hashLocation.split("#!/");
            var hashName = hashSplit[1];
            if (hashName !== '') {
                var hash = window.location.hash;
                if (hash === '') {
                    //alert("Back button isn't supported. You are leaving this application on next clicking the back button");
                }
            }
        });
        window.history.pushState('forward', null, './#');
    }
</script>

</body>
</html>