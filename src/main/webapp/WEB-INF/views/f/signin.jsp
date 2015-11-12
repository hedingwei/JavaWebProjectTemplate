<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Login</title>

        <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/js/plugin/code/code.css" />
        <!--[if lte IE 8]>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-ie.min.css" />
        <![endif]-->

        <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath}/resources/assets/js/html5shiv.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/respond.min.js"></script>
        <![endif]-->
        <style>
            @font-face { 
                font-family: "Open Sans"; /*这里是说明调用来的字体名字*/ 
                src: url('${pageContext.request.contextPath}/resources/assets/font/OpenSans-Regular.ttf'); /*这里是字体文件路径*/ 
            } 
            .login-layout .widget-box {
                background-color: rgba(30, 59, 115, 1);
            }
        </style>
    </head>

    <body class="login-layout" style="background:rgba(30, 59, 115, 0.8) center;">
        <div class="main-container">
            <div class="main-content">
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <div class="login-container">
                            <div class="center">
                                <h3 class="white">
                                    坤腾畅联分布式爬虫平台
                                </h3> 
                            </div>
                            <div class="space-6"></div>
                            <div class="position-relative">
                                <div id="login-box" class="login-box visible widget-box no-border">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <h4 class="header blue lighter bigger">
                                                <i class="icon-coffee green"></i>
                                                管理员登录
                                            </h4>
                                            <div class="space-6"></div>

                                            <c:if test="${not empty error}">
                                                <div class="error alert alert-danger">
                                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                                    <strong></strong>${error}
                                                </div>

                                            </c:if>
                                            <c:if test="${not empty msg}">
                                                <div class="alert alert-success">
                                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                                    <strong>恭喜!</strong> ${msg}
                                                </div>

                                            </c:if>

                                            <form id="form" method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
                                                <fieldset>
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="text" value="" id="UserName" name="username" class="form-control" placeholder="用户名" required/>
                                                            <i class="icon-user"></i>
                                                        </span>
                                                    </label>
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="password" value="" id="Password" name="password" class="form-control" placeholder="密码" required/>
                                                            <i class="icon-lock"></i>
                                                        </span>
                                                    </label>
                                                    <div class="space"></div>
                                                    <p><span id="idcode"></span></p>
                                                    <div class="clearfix">

                                                        <div class="pull-left">
                                                            <div class="ckbox ckbox-primary mt10">
                                                                <input type="checkbox" id="rememberMe" value="1" name="remember-me">
                                                                <label for="rememberMe">记住我</label>
                                                            </div>
                                                        </div>

                                                        <button type="submit" id="login-btn" class="width-35 pull-right btn btn-sm btn-primary">
                                                            <i class="icon-key"></i>
                                                            登录
                                                        </button>
                                                    </div>

                                                    <%--<div class="space-4"></div>--%>
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                                </fieldset>
                                            </form>
                                        </div><!-- /widget-main -->
                                    </div><!-- /widget-body -->
                                </div><!-- /login-box -->
                            </div><!-- /position-relative -->
                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>
        </div><!-- /.main-container -->
        <script>
            if (top != self) {
                top.location = "${pageContext.request.contextPath}/f/index.do";
            }
        </script>
        <!-- basic scripts -->
    </body>
</html>
