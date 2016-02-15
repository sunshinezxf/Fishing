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
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${path.concat('/material/css/fishpond.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/css/comment.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/plugins/summernote-master/dist/summernote.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/plugins/Font-Awesome-master/css/font-awesome.min.css')}"/>
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-migrate-1.2.1.min.js')}"></script>
    <script type="text/javascript"
            src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/summernote-master/dist/summernote.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/summernote-master/lang/summernote-zh-CN.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/date.js')}"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
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
            var fishPondId = "${fishPond.fishPondId}";
            var openId = "${openId}";

            $("#cancel-fishpond-comment").click(function () {
                $("#fishpond-comment").fadeOut();
                $("#comment-fishpond").fadeIn();
                $("#comment-list").fadeIn();
            });

            function load_comment() {
                var comment_list_url = "${path.concat('/comment/')}" + fishPondId;
                $.get(comment_list_url, function (result) {
                    $("#comment-list").empty();
                    var content_html = "";
                    for (var i = 0; i < result.length; i++) {
                        content_html += "<div class=\"comment\">"; //comment div start
                        content_html += "<div class=\"content\">"; //content div start
                        content_html += "<a class=\"author\">";
                        if (result[i].wechat == '' || result[i].wechat == null) {
                            content_html += "游客";
                        } else if (result[i].wechat == openId) {
                            content_html += "我";
                        } else {
                            content_html += "钓友";
                        }
                        content_html += "</a>";
                        content_html += "<div class=\"metadata\">"; //metadata div start
                        content_html += "<span class=\"date\">";
                        content_html += (new Date(result[i].createAt)).format("yyyy-MM-dd hh:mm:ss");
                        content_html += "</span>";
                        content_html += "</div>"; //metadata div end
                        content_html += "<div class=\"text\">"; //text div start
                        content_html += result[i].comment;
                        content_html += "</div>"; //text div end
                        if (result[i].wechat != openId) {
                            content_html += "<div class=\"actions\">"; //action div start
                            content_html += "<a class=\"reply comment-list-item\" onclick='modal(\"" + result[i].commentId + "\")'>回复";
                            content_html += "</a>";
                            content_html += "</div>"; //action div end
                        }
                        content_html += "</div>"; //content div end
                        if (result[i].commentList.length > 0) {
                            content_html += "<div class=\"comments\">"; //comments div start
                            for (var j = 0; j < result[i].commentList.length; j++) {
                                content_html += "<div class=\"comment\">"; //comment div start
                                content_html += "<a class=\"author\">";
                                if (result[i].commentList[j].wechat == '' || result[i].commentList[j].wechat == null) {
                                    content_html += "游客";
                                } else if (result[i].commentList[j].wechat == openId) {
                                    content_html += "我";
                                } else {
                                    content_html += "钓友";
                                }
                                content_html += "</a>";
                                content_html += "<div class=\"metadata\">"; //metadata div start
                                content_html += "<span class=\"date\">";
                                content_html += (new Date(result[i].commentList[j].createAt)).format("yyyy-MM-dd hh:mm:ss");
                                content_html += "</span>";
                                content_html += "</div>"; //metadata div end
                                content_html += "<div class=\"text\">"; //text div start
                                content_html += result[i].commentList[j].comment;
                                content_html += "</div>"; //text div end
                                if (result[i].commentList[j].wechat != openId) {
                                    content_html += "<div class=\"actions\">"; //action div start
                                    content_html += "<a class=\"reply comment-list-item\" onclick='modal(\"" + result[i].commentId + "\")'>回复";
                                    content_html += "</a>";
                                    content_html += "</div>"; //action div end
                                }
                                content_html += "</div>" //comment div end
                            }
                            content_html += "</div>"; //comments div end
                        }
                        content_html += "</div>"; //comment div end
                    }
                    $("#comment-list").append(content_html);
                });
            }

            load_comment();

            $("#submit-fishpond-comment").click(function () {
                var url = "${path.concat('/comment/create')}";
                var comment = $("#summernote").code();
                var parent = $("#insert-parent").val();
                $("#summernote").code("");
                $("#fishpond-comment").fadeOut();
                $("#comment-fishpond").fadeIn();
                $("#comment-list").fadeIn();
                $.post(url, {
                    openId: openId,
                    fishPondId: fishPondId,
                    comment: comment,
                    parentId: parent
                }, function () {
                    setTimeout(load_comment(), 1000);
                });
            });
        });

        function modal(parent_id) {
            $("#insert-parent").attr("value", parent_id);
            $("#comment-fishpond").fadeOut();
            $("#comment-list").fadeOut();
            $("#fishpond-comment").fadeIn();
        }
    </script>
</head>
<body>
<div id="fishpond" class="container">
    <c:if test="${not empty fishPond.thumbnail}">
        <div class="row">
            <img src="${path}${fishPond.thumbnail}" class="img-responsive" width="100%" height="auto"/>
        </div>
    </c:if>
    <h4>
        ${fishPond.fishPondName}
        <c:if test="${not empty fishPond.contractor.name}">
            (
            <small><span class="glyphicon glyphicon-user"></span>${fishPond.contractor.name}</small>
            )
        </c:if>
    </h4>
    <p>
        <a id="show_map_sheet"
           href="http://apis.map.qq.com/uri/v1/routeplan?type=drive&to=${fishPond.fishPondName}&tocoord=${fishPond.latitude},${fishPond.longitude}&policy=1&referer=fishing"><span
                class="glyphicon glyphicon-map-marker"></span>&nbsp;${fishPond.fishPondAddress}</a></p>
    <c:if test="${not empty fishPond.fishPondFee}">
        <div class="pond-fee">
            <span class="h6">收费&nbsp;</span>
            <span class="h6">${fishPond.fishPondFee}</span>
        </div>
    </c:if>
    <c:if test="${not empty fishPond.pondTypes}">
        <div class="pond-type">
            <span class="h6">类型&nbsp;</span>
            <c:if test="${not empty fishPond.pondTypes}">
                <c:forEach var="item" items="${fishPond.pondTypes}" varStatus="no">
                    <span class="label label-warning">${item.pondTypeName}</span>
                </c:forEach>
            </c:if>
        </div>
    </c:if>
    <c:if test="${not empty fishPond.fishes}">
        <div class="pond-feature">
            <span class="h6">特色&nbsp;</span>
            <c:if test="${not empty fishPond.fishes}">
                <c:forEach var="item" items="${fishPond.fishes}" varStatus="no">
                    <span class="label label-info">${item.fishName}</span>
                </c:forEach>
            </c:if>
        </div>
    </c:if>
    <c:if test="${not empty fishPond.introduction}">
        <div class="pond-introduction">
            <label class="h6">简介&nbsp;</label>

            <div class="alert alert-info introduction">${fishPond.introduction}</div>
        </div>
    </c:if>
    <footer class="footer blog-footer">
        <div>内容更新时间: <fmt:formatDate value="${fishPond.createAt}" type="date" dateStyle="full"/></div>
    </footer>
    <div class="pond-review cell">

        <div class="ui comments">
            <h4 class="ui header">评论区</h4>

            <button type="button" id="comment-fishpond" class="btn btn-primary" onclick="modal()">评论
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <div id="comment-list" style="margin-top: 0.5em; margin-bottom: 0.5em;"></div>
            <div id="fishpond-comment" class="row container">
                <div id="insert-comment-form" class="ui reply form">
                    <input type="hidden" id="insert-parent" name="parentId" value=""/>

                    <div id="summernote"></div>
                    <div class="form-inline" style="margin-top: 0.3em">
                        <button type="button" id="cancel-fishpond-comment" class="btn btn-success">取消</button>
                        <button type="button" id="submit-fishpond-comment" class="btn btn-primary">发表</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        $("#fishpond-comment").hide();
        $("#summernote").summernote({
            lang: "zh-CN",
            height: 200,
            toolbar: [['insert', ['picture']]],
            onImageUpload: function (files, editor, welEditable) {
                upload_image(files[0], editor, welEditable);
            }
        });
    });

    function upload_image(file, editor, welEditable) {
        var url = "${path.concat('/picture/upload')}";
        var data = new FormData();
        data.append("picture", file);
        $.ajax({
            data: data,
            type: "POST",
            url: url,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result.responseCode == "RESPONSE_OK") {
                    var url = "${path}" + result.data;
                    $('#summernote').summernote('editor.insertImage', url);
                }
            }
        });
    }
</script>
</html>
