<%--
  Created by IntelliJ IDEA.
  User: sunshine
  Date: 15/8/13
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="${path.concat('/material/plugins/bootstrap-3.3.5-dist/css/bootstrap.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/css/signin.css')}"/>
    <title>登录</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${path.concat('/index')}">Diaoyu114&nbsp;管理平台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${path.concat('/login')}">登录</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <form class="form-signin" id="lg-form">
        <h2 class="form-signin-heading">
            登录
        </h2>

        <div class="form-group has-feedback">
            <label for="account_email" class="sr-only">
                Email address
            </label>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            <input type="email" class="form-control" id="account_email" name="email"
                   placeholder="邮箱" required="" autocomplete="off">
        </div>
        <div class="form-group has-feedback">
            <label for="account_password" class="sr-only">
                Password
            </label>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <input type="password" class="form-control" name="password"
                   id="account_password" placeholder="密码" required="" autocomplete="off">
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox">
                记住我
            </label>
        </div>
        <button type="submit" class="btn btn-lg btn-primary btn-block btn-info" id="lgbtn" disabled="disabled">登录
        </button>
    </form>
</div>
</body>
<script type="text/javascript"
        src="${path.concat('/material/plugins/jquery/jquery-1.11.3.min.js')}"></script>
<script type="text/javascript"
        src="${path.concat('/material/plugins/jquery/jquery-migrate-1.2.1.min.js')}"></script>
<script type="text/javascript"
        src="${path.concat('/material/plugins/bootstrap-3.3.5-dist/js/bootstrap.js')}"></script>
<script type="text/javascript" src="${path.concat("/material/js/validate.js")}"></script>
<script>
    $(document).ready(function () {
        $("#account_email").bind("input propertychange", function () {
            if (login_validate()) {
                $("#lgbtn").removeAttr("disabled");
            } else {
                $("#lgbtn").attr("disabled", "disabled");
            }
        });
        $("#account_password").bind("input propertychange", function () {
            if (login_validate()) {
                $("#lgbtn").removeAttr("disabled");
            } else {
                $("#lgbtn").attr("disabled", "disabled");
            }
        });
        $("#lgbtn").click(function () {
            $("#lg-form").attr("action", "${path.concat('/login')}");
            $("#lg-form").attr("method", "post");
            $("#lg-form").submit();
        })
    });
</script>
</html>
