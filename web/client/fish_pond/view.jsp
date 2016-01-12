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
    <script type="text/javascript" src="${path.concat('/material/plugins/timeago/jquery.timeago.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/jweixin-1.0.0.js')}"></script>
    <title>${fishPond.fishPondName}</title>
    <c:if test="${not empty configuration}">
        <script type="text/javascript">
            wx.config({
                debug: false,
                appId: '${appId}',
                timestamp: '${configuration.timestamp}',
                nonceStr: '${configuration.nonceStr}',
                signature: '${configuration.signature}',
                jsApiList: [
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage'
                ]
            });

            // 2. 分享接口
            // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
            wx.ready(function () {
                wx.onMenuShareAppMessage({
                    title: '${fishPond.fishPondName}', // 分享标题
                    desc: '${fishPond.fishPondAddress}', // 分享描述
                    link: '${configuration.shareLink}', // 分享链接
                    imgUrl: '', // 分享图标
                    type: '', // 分享类型,music、video或link，不填默认为link
                    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                    success: function () {

                    },
                    cancel: function () {

                    }
                });
                // 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
                wx.onMenuShareTimeline({
                    title: '${fishPond.fishPondName}',
                    link: '${configuration.shareLink}',
                    imgUrl: '',
                    trigger: function (res) {
                    },
                    success: function (res) {

                    },
                    cancel: function (res) {

                    },
                    fail: function (res) {

                    }
                });
            });
        </script>
    </c:if>
    <script type="text/javascript">

        $(document).ready(function () {
            $(".comment-fishpond").attr("data-toggle", "modal");
            $(".comment-fishpond").attr("data-target", "#myModal");

            $(".comment-fishpond").click(function () {

            });

            var fishPondId = "${fishPond.fishPondId}";
            var openId = "${openId}";

            function load_comment() {
                var comment_list_url = "${path.concat('/comment/')}" + fishPondId;
                $.get(comment_list_url, function (result) {
                    $("#comment-list").empty();
                    var content_html = "";
                    for (var i = 0; i < result.length; i++) {
                        content_html += "<div class=\"comment\">";
                        content_html += "<div class=\"content\">";
                        content_html += "<a class=\"author\">";
                        if (result[i].wechat == openId) {
                            content_html += "我";
                        } else {
                            content_html += "钓友";
                        }
                        content_html += "</a>";
                        content_html += "<div class=\"metadata\">";
                        content_html += "<span class=\"date\">";
                        content_html += (new Date(result[i].createAt)).format("yyyy-MM-dd hh:mm:ss");
                        content_html += "</div>";
                        content_html += "<div class=\"text\">";
                        content_html += result[i].comment;
                        content_html += "</div>";
                        content_html += "<div class=\"actions\">";
                        content_html += "</div>";
                        content_html += "</div>";
                        content_html += "</div>";
                    }
                    $("#comment-list").append(content_html);
                });
            }

            load_comment();

            $("#comment-fishpond").click(function () {
                $("#form-insert-parent").attr("value", "");
                $("#comment-content").attr("placeholder", "说说你对此钓场的看法吧");
                $("#comment-content").val("");

            });

            $("#submit-fishpond-comment").click(function () {
                var url = "${path.concat('/comment/create')}";
                var comment = $("#comment-content").val();
                var parent = $("#insert-parent").val();
                $.post(url, {
                    openId: openId,
                    fishPondId: fishPondId,
                    comment: comment,
                    parentId: parent
                }, function (result) {
                    load_comment();
                });
            });
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

            <div id="comment-fishpond" class="ui blue labeled submit icon button" data-toggle="modal"
                 data-target="#myModal"><i class="icon edit"></i>
                评论
            </div>

            <div id="comment-list" style="margin-top: 0.5em; margin-bottom: 0.5em;"></div>

        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    评论
                </h4>
            </div>
            <div class="modal-body">
                <form id="insert-comment-form" class="ui reply form">
                    <input type="hidden" id="insert-parent" name="parentId" value=""/>

                    <div class="field">
                        <textarea id="comment-content"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">取消
                </button>
                <button type="button" id="submit-fishpond-comment" class="btn btn-primary" data-dismiss="modal">
                    评论
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
</body>
</html>
