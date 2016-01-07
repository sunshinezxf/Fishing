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
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;">
    <link rel="stylesheet" href="${path.concat('/material/plugins/bootstrap-3.3.5-dist/css/bootstrap.min.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/plugins/weui/weui.min.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/css/fishpond.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/plugins/semantic-ui/semantic.min.css')}">
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-1.11.3.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/bootstrap-3.3.5-dist/js/bootstrap.min.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/plugins/semantic-ui/semantic.min.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/date.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/jweixin-1.0.0.js')}"></script>
    <title>${fishPond.fishPondName}</title>
    <script type="text/javascript">
        wx.config({
            debug: true,
            appId: '${appId}'
        });
    </script>
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


    <p>
        <a id="show_map_sheet"
           href="http://apis.map.qq.com/uri/v1/routeplan?type=drive&to=${fishPond.fishPondName}&tocoord=${fishPond.latitude},${fishPond.longitude}&policy=1&referer=fishing"><span
                class="glyphicon glyphicon-map-marker"></span>&nbsp;${fishPond.fishPondAddress}</a></p>

    <div class="pond-fee">
        <span class="h6">收费&nbsp;</span>
        <span class="h6">${fishPond.fishPondFee}</span>
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
    <footer class="footer blog-footer">
        <div>内容更新时间: <fmt:formatDate value="${fishPond.createAt}" type="date" dateStyle="full"/></div>
    </footer>
    <div class="pond-review cell">

        <div class="ui comments">
            <h4 class="ui header">评论区</h4>

            <div class="ui blue labeled submit icon button"><i class="icon edit"></i>写评论</div>
            <div class="comment">
                <div class="content">
                    <a class="author">Matt</a>

                    <div class="metadata">
                        <span class="date">今天下午 5:42</span>
                    </div>
                    <div class="text">太赞了！</div>
                    <div class="actions">
                        <a class="reply">回复</a>
                    </div>
                </div>
            </div>
            <div class="comment">
                <div class="content">
                    <a class="author">Elliot Fu</a>

                    <div class="metadata">
                        <span class="date">昨天上午12:30</span>
                    </div>
                    <div class="text">
                        <p>This has been very useful for my research. Thanks as well!</p>
                    </div>
                    <div class="actions">
                        <a class="reply">回复</a>
                    </div>
                </div>
                <div class="comments">
                    <div class="comment">
                        <div class="content">
                            <a class="author">Jenny Hess</a>

                            <div class="metadata">
                                <span class="date">刚刚</span>
                            </div>
                            <div class="text">Elliot you are always so right :)</div>
                            <div class="actions">
                                <a class="reply">回复</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="comment">
                <div class="content">
                    <a class="author">Joe Henderson</a>

                    <div class="metadata">
                        <span class="date">5 天以前</span>
                    </div>
                    <div class="text">老兄，这太棒了。非常感谢。</div>
                    <div class="actions">
                        <a class="reply">回复</a>
                    </div>
                </div>
            </div>
            <form class="ui reply form">
                <div class="field">
                    <textarea></textarea>
                </div>
                <div class="ui blue labeled submit icon button"><i class="icon edit"></i>回复</div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
