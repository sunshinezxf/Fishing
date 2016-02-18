<%--
  Created by IntelliJ IDEA.
  User: sunshine
  Date: 2/15/16
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${path.concat('/material/plugins/dropload/dropload.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/css/mobilepage.css')}"/>
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${path.concat('/material/plugins/dropload/dropload.min.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/date.js')}"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <title>即时渔讯</title>
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
                    title: '即时渔讯', // 分享标题
                    desc: '', // 分享描述
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
                    title: '即时渔讯',
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
</head>
<body>
<div class="content">
    <div class="screening">
        <div class="lists"></div>
    </div>
</div>
</body>
<script>
    var counter = 0;
    var num = 5;
    var pageStart = 0, pageEnd = 0;

    $(function () {
        // dropload
        $('.content').dropload({
            scrollArea: window,
            domUp: {
                domClass: 'dropload-up',
                domRefresh: '<div class="dropload-refresh">↓下拉刷新</div>',
                domUpdate: '<div class="dropload-update">↑释放更新</div>',
                domLoad: '<div class="dropload-load"><span class="loading"></span>加载中</div>'
            },
            domDown: {
                domClass: 'dropload-down',
                domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
                domLoad: '<div class="dropload-load"><span class="loading"></span>加载中</div>',
                domNoData: '<div class="dropload-noData">没有更多了</div>'
            },
            loadUpFn: function (me) {
                $('.lists').empty();
                counter = 0;
                pageStart = 0;
                $.ajax({
                    type: 'POST',
                    url: "${path.concat('/comment/topic')}",
                    data: {
                        start: pageStart,
                        length: num,
                        params: {}
                    },
                    dataType: 'json',
                    success: function (result) {
                        var content = '';
                        var total = result.total;
                        if (total == 0) {
                            me.lock();
                            me.noData();
                            me.resetload();
                            return;
                        }
                        var length = result.data.length;
                        var data = result.data;
                        counter++;
                        pageEnd = num * counter;
                        pageStart = pageEnd;
                        var reg = /(<\s*img[^>]*)class[=\s\"\']+[^\"\']*[\"\']?([^>]*>)/gi;
                        for (var i = 0; i < length; i++) {
                            var description = data[i].comment.replace(reg, "<div class='col-xs-2 col-md-2' style='padding-left: 0; margin: 0 0.5em'>$1 class='img-rounded' $2</div>");
                            var url = "http://www.njuat.com/fishzone/" + data[i].fishPond.fishPondId;
                            content += "<a class='item opacity' href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=" + encodeURIComponent(url) + "&response_type=code&scope=snsapi_base&state=view#wechat_redirect'>"
                                    + "<div class='media'>"
                                    + "<div class='media-left'>"
                                    + "<h4 class='media-heading label label-info' style='font-size: 100%; line-height: 2'>渔讯</h4>&nbsp;&nbsp;"
                                    + "</div>"
                                    + "<div class='media-body'>"
                                    + "<h4 class='media-heading'>"
                                    + data[i].fishPond.fishPondName
                                    + "<small style='margin-left: 2em'><i class='glyphicon glyphicon-calendar'></i>"
                                    + (new Date(data[i].createAt)).format("yyyy-MM-dd")
                                    + "</small>"
                                    + "</h4>渔讯概览"
                                    + description
                                    + "</div>"
                                    + "<div class='media-right'>"
                                    + "<span class='date'>&gt;&gt;</span>"
                                    + "</div>"
                                    + "</div>"
                                    + "</a>";
                            if ((counter - 1) * num + i + 1 >= total) {
                                // 锁定
                                me.lock();
                                // 无数据
                                me.noData();
                                break;
                            }
                        }
                        // 为了测试，延迟1秒加载
                        setTimeout(function () {
                            $('.lists').append(content);
                            // 每次数据加载完，必须重置
                            me.resetload();
                        }, 500);
                    },
                    error: function (xhr, type) {
                        // 即使加载出错，也得重置
                        me.resetload();
                    }
                });
            },
            loadDownFn: function (me) {
                $.ajax({
                    type: 'POST',
                    url: "${path.concat('/comment/topic')}",
                    data: {
                        start: pageStart,
                        length: num,
                        params: {}
                    },
                    dataType: 'json',
                    success: function (result) {
                        var content = '';
                        var total = result.total;
                        if (total == 0) {
                            me.lock();
                            me.noData();
                            me.resetload();
                            return;
                        }
                        var length = result.data.length;
                        var data = result.data;
                        counter++;
                        pageEnd = num * counter;
                        pageStart = pageEnd;
                        var reg = /(<\s*img[^>]*)class[=\s\"\']+[^\"\']*[\"\']?([^>]*>)/gi;
                        for (var i = 0; i < length; i++) {
                            var description = data[i].comment.replace(reg, "<div class='col-xs-2 col-md-2' style='padding-left: 0; margin: 0 0.5em'>$1 class='img-rounded' $2</div>");
                            var url = "http://www.njuat.com/fishzone/" + data[i].fishPond.fishPondId;
                            content += "<a class='item opacity' href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=" + encodeURIComponent(url) + "&response_type=code&scope=snsapi_base&state=view#wechat_redirect'>"
                                    + "<div class='media'>"
                                    + "<div class='media-left'>"
                                    + "<h4 class='media-heading label label-info' style='font-size: 100%; line-height: 2'>渔讯</h4>&nbsp;&nbsp;"
                                    + "</div>"
                                    + "<div class='media-body'>"
                                    + "<h4 class='media-heading'>"
                                    + data[i].fishPond.fishPondName
                                    + "<small style='margin-left: 2em'><i class='glyphicon glyphicon-calendar'></i>"
                                    + (new Date(data[i].createAt)).format("yyyy-MM-dd")
                                    + "</small>"
                                    + "</h4>渔讯概览"
                                    + description
                                    + "</div>"
                                    + "<div class='media-right'>"
                                    + "<span class='date'>&gt;&gt;</span>"
                                    + "</div>"
                                    + "</div>"
                                    + "</a>";
                            if ((counter - 1) * num + i + 1 >= total) {
                                // 锁定
                                me.lock();
                                // 无数据
                                me.noData();
                                break;
                            }
                        }
                        // 为了测试，延迟1秒加载
                        setTimeout(function () {
                            $('.lists').append(content);
                            // 每次数据加载完，必须重置
                            me.resetload();
                        }, 500);
                    },
                    error: function (xhr, type) {
                        // 即使加载出错，也得重置
                        me.resetload();
                    }
                });
            }
        });
    });
</script>
</html>
