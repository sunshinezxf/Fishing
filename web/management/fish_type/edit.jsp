<%--
  Created by IntelliJ IDEA.
  User: sunshine
  Date: 12/20/15
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="${path.concat('/material/plugins/bootstrap-3.3.5-dist/css/bootstrap.css')}"/>
    <link rel="stylesheet" type="text/css"
          href="${path.concat('/material/plugins/Font-Awesome-master/css/font-awesome.css')}"/>
    <link rel="stylesheet" type="text/css"
          href="${path.concat('/material/css/dashboard.css')}"/>
    <link rel="stylesheet" type="text/css" href="${path.concat('/material/css/customize.css')}"/>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-1.11.3.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-migrate-1.2.1.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/bootstrap-3.3.5-dist/js/bootstrap.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/dashboard.js')}"></script>
    <title>修改鱼种</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">Diaoyu114&nbsp;管理平台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${path.concat('/logout')}">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
