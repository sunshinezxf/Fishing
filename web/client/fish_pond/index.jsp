<%--
  Created by IntelliJ IDEA.
  User: sunshine
  Date: 1/24/16
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/semantic-ui/2.1.8/semantic.min.css"/>
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/semantic-ui/2.1.8/semantic.min.js"></script>
    <script type="text/javascript" src="${path.concat('/material/js/date.js')}"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <title>搜索页面</title>
</head>
<body>
<div class="container-fluid">
    <div class="ui relaxed divided list">
        <div class="item">
            <img class="ui image img-rounded" height="50px" width="50px"
                 src="http://www.njuat.com/material/upload/20151210/THelfflr82.jpg"/>

            <div class="content">
                <a class="header">
                    禄口晨虹钓场
                </a>

                <div class="description">
                    <div style="margin-bottom: 0.2em">
                        <span class="label label-info">鲫鱼</span>
                        <span class="label label-info">鲫鱼</span>
                        <span class="label label-info">鲫鱼</span>
                        <span style="margin-left: 2.3em">1.2km</span>
                    </div>

                    <p>南京市鼓楼区</p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="ui image img-rounded" height="50px" width="50px"
                 src="http://www.njuat.com/material/upload/20151210/THelfflr82.jpg"/>

            <div class="content">
                <a class="header">
                    禄口晨虹钓场
                </a>

                <div class="description">
                    <div style="margin-bottom: 0.2em">
                        <span class="label label-info">鲫鱼</span>
                        <span class="label label-info">鲫鱼</span>
                        <span class="label label-info">鲫鱼</span>
                        <span style="margin-left: 2.3em">1.2km</span>
                    </div>
                    <p>南京市鼓楼区</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
