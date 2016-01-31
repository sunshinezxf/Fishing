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
            var from = new qq.maps.LatLng(30.93, 116.35421264);
            var to = new qq.maps.LatLng(latitude, longitude);
            var distance = (qq.maps.geometry.spherical.computeDistanceBetween(from, to) / 1000).toFixed(1);
            return distance;
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <ul class="ui relaxed divided list media-list">
        <li class="item media">
            <div class="media-left">
                <img class="media-object img-rounded" height="55px" width="55px"
                     src="http://www.njuat.com/material/upload/20151210/THelfflr82.jpg"/>
            </div>
            <div class="media-body">
                <a class="header">
                    禄口晨虹钓场
                </a>

                <div class="description">
                    <div style="margin-top: 0.2em; margin-bottom: 0.3em">
                        <span class="label label-info">鲫鱼</span>
                        <span class="label label-info">鲫鱼</span>
                        <span class="label label-info">鲫鱼</span>
                    </div>
                    <label>南京市鼓楼区</label>
                </div>
            </div>
            <div class="media-right">
                <span>1.2km</span>
            </div>
        </li>
        <li class="item media">
            <div class="media-left">
                <img class="media-object img-rounded" height="55px" width="55px"
                     src="http://www.njuat.com/material/upload/20151210/THelfflr82.jpg"/>
            </div>
            <div class="media-body">
                <a class="header">
                    禄口晨虹钓场
                </a>

                <div class="description">
                    <div style="margin-top: 0.2em; margin-bottom: 0.3em">
                        <span class="label label-info">鲫鱼</span>
                        <span class="label label-info">鲫鱼</span>
                        <span class="label label-info">鲫鱼</span>
                    </div>
                    <label>南京市鼓楼区</label>
                </div>
            </div>
            <div class="media-right">
                <span id="distance"><script type="text/javascript">distance(116.353454, 39.996059)</script></span>
            </div>
        </li>
    </ul>
    <p>钓粉: ${fishFan.fishFanId},经度:${fishFan.longitude}, 纬度:${fishFan.latitude}</p>
</div>
</body>
</html>
