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
    <link rel="stylesheet" href="${path.concat('/material/plugins/dropload/dropload.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/css/mobilepage.css')}"/>
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${path.concat('/material/plugins/dropload/dropload.min.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/date.js')}"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script charset="utf-8"
            src="http://map.qq.com/api/js?v=2.exp&libraries=geometry&key=6GEBZ-AL5CV-Y4WPU-U7TKP-HAEB3-4ABX3"></script>
    <title>搜索钓场</title>
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
                    title: '搜索页面', // 分享标题
                    desc: '欢迎使用南京钓场搜索', // 分享描述
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
                    title: '搜索页面',
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
        function distance(longitude, latitude) {
            var from, to;
            to = new qq.maps.LatLng(latitude, longitude);
            if (${fishFan != null && fishFan.longitude != null && fishFan.latitude != null}) {
                from = new qq.maps.LatLng("${fishFan.latitude}", "${fishFan.longitude}");
                var distance = (qq.maps.geometry.spherical.computeDistanceBetween(from, to) / 1000).toFixed(1);
                return "距离约: " + distance + "km";
            } else {
                return "";
            }
        }
        $(document).ready(function () {

        });
    </script>
</head>
<body>
<div class="content">
    <div class="lists">
    </div>
</div>
</body>
<script>
    $(function () {
        var counter = 0;
        var num = 8;
        var pageStart = 0, pageEnd = 0;
        // dropload
        $('.content').dropload({
            scrollArea: window,
            loadDownFn: function (me) {
                $.ajax({
                    type: 'POST',
                    url: "${path.concat('/fishzone/index')}",
                    data: {start: pageStart, length: num},
                    dataType: 'json',
                    success: function (result) {
                        var content = '';
                        var total = result.total;
                        var length = result.data.length;
                        var data = result.data;
                        counter++;
                        pageEnd = num * counter;
                        pageStart = pageEnd;
                        for (var i = 0; i < length; i++) {
                            var url = "http://www.njuat.com/fishzone/" + data[i].fishPondId;
                            content += "<a class='item opacity' href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=" + encodeURIComponent(url) + "&response_type=code&scope=snsapi_base&state=view#wechat_redirect'>"
                                    + "<div class='media'>"
                                    + "<div class='media-left'>"
                                    + "<img class='img-rounded' src='${path}" + data[i].thumbnail + "' />"
                                    + "</div>"
                                    + "<div class='media-body'>"
                                    + "<h3>" + data[i].fishPondName + "</h3>"
                                    + "<div style='margin-top: 0.2em; margin-bottom: 0.3em'>";
                            var fishes = data[i].fishes;
                            for (var f = 0; f < fishes.length; f++) {
                                content += "<span class='label label-info'>" + fishes[f].fishName + "</span> "
                            }
                            content += "</div>"
                                    + "<label>南京市鼓楼区</label>"
                                    + "</div>"
                                    + "<div class='media-right'>"
                                    + "<span class='date'>" + distance(data[i].longitude, data[i].latitude) + "</span>"
                                    + "</div>"
                                    + '</div>'
                                    + '</a>';
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
