<%--
  Created by IntelliJ IDEA.
  User: sunshine
  Date: 12/10/15
  Time: 18:12
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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${path.concat('/material/plugins/bootstrap-3.3.5-dist/css/bootstrap.min.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/plugins/weui/weui.min.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/css/fishpond.css')}"/>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-1.11.3.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/bootstrap-3.3.5-dist/js/bootstrap.min.js')}"></script>
    <title>${fishPond.fishPondName}</title>
</head>
<body ontouchstart>
<div class="container">
    <div class="row">
        <img src="${path}${fishPond.thumbnail}" class="img-responsive" width="100%" height="auto"/>
    </div>
    <h4>
        ${fishPond.fishPondName}
        (
        <small><span class="glyphicon glyphicon-user"></span>${fishPond.contractor.name}</small>
        )
    </h4>


    <p><span class="glyphicon glyphicon-map-marker"></span>&nbsp;${fishPond.fishPondAddress}</p>

    <div class="pond-fee">
        <span class="h6">收费&nbsp;</span>
        <span class="h6">${fishPond.fishPondFee}元/斤</span>
    </div>

    <div class="pond-type">
        <span class="h6">类型&nbsp;</span>
        <c:if test="${not empty fishPond.pondTypes}">
            <c:forEach var="item" items="${fishPond.pondTypes}" varStatus="no">
                <span class="label label-warning">${item.pondTypeName}</span>
            </c:forEach>
        </c:if>
    </div>

    <div class="pond-feature">
        <span class="h6">特色&nbsp;</span>
        <c:if test="${not empty fishPond.fishes}">
            <c:forEach var="item" items="${fishPond.fishes}" varStatus="no">
                <span class="label label-info">${item.fishName}</span>
            </c:forEach>
        </c:if>
    </div>

    <div class="pond-introduction">
        <label class="h6">简介&nbsp;</label>

        <div class="alert alert-info introduction">${fishPond.introduction}</div>
    </div>

</div>
</body>
</html>
