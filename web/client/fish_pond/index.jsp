<%--
  Created by IntelliJ IDEA.
  User: sunshine
  Date: 1/24/16
  Time: 22:00
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
    <link rel="stylesheet" href="${path.concat('/material/css/navigate.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/plugins/dropload/dropload.css')}"/>
    <link rel="stylesheet" href="${path.concat('/material/css/mobilepage.css')}"/>
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${path.concat('/material/js/navigate.js')}"></script>
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

        function init_district_list(obj) {
            cityId = obj;
            hidedistrict();
            var require_district_url = "${path.concat('/division/')}" + obj + "/district";
            $.get(require_district_url, function (result) {
                var district_container = $(".region-district");
                district_container.empty();
                if (result.responseCode == "RESPONSE_OK") {
                    var data = result.data;
                    var district = document.createElement("li");
                    district.innerHTML = "全市";
                    district.setAttribute("onclick", "city_wide()");
                    district_container.append(district);
                    for (var i = 0; i < data.length; i++) {
                        var district = document.createElement("li");
                        district.innerHTML = data[i].districtName;
                        district.setAttribute("onclick", "district_wide('" + data[i].districtId + "')");
                        district_container.append(district);
                    }
                }
                showdistrict();
            });

        }

        function init_city_list(obj) {
            provinceId = obj;
            hidecity();
            var require_city_url = "${path.concat('/division/')}" + obj + "/city";
            $.get(require_city_url, function (result) {
                var city_container = $(".region-city");
                city_container.empty();
                if (result.responseCode == "RESPONSE_OK") {
                    var data = result.data;
                    var city = document.createElement("li");
                    city.innerHTML = "全省";
                    city.setAttribute("onclick", "province_wide()");
                    city_container.append(city);
                    for (var i = 0; i < data.length; i++) {
                        var city = document.createElement("li");
                        city.setAttribute("onclick", "init_district_list('" + data[i].cityId + "')");
                        city.innerHTML = data[i].cityName;
                        city_container.append(city);
                    }
                }
                showcity();
            });
        }

        function nation_wide() {
            provinceId = '';
            cityId = '';
            districtId = '';
            $(".lists").hide();
            hidepondeject();
            hidefisheject();
            if ($(".region-eject").hasClass('display')) {
                $(".lists").show();
                hideregioneject();
            }
            list_reload();
        }

        function province_wide() {
            cityId = '';
            districtId = '';
            $(".lists").hide();
            hidepondeject();
            hidefisheject();
            if ($(".region-eject").hasClass('display')) {
                $(".lists").show();
                hideregioneject();
            }
            list_reload();
        }

        function city_wide() {
            districtId = '';
            $(".lists").hide();
            hidepondeject();
            hidefisheject();
            if ($(".region-eject").hasClass('display')) {
                $(".lists").show();
                hideregioneject();
            }
            list_reload();
        }

        function district_wide(obj) {
            districtId = obj;
            $(".lists").hide();
            hidepondeject();
            hidefisheject();
            if ($(".region-eject").hasClass('display')) {
                $(".lists").show();
                hideregioneject();
            }
            list_reload();
        }

        $(document).ready(function () {
            var province_container = $(".region-province");
            province_container.empty();
            var require_province_url = "${path.concat('/division/province')}";
            $.get(require_province_url, function (result) {
                if (result.responseCode == "RESPONSE_OK") {
                    var data = result.data;
                    var province = document.createElement("li");
                    province.innerHTML = "所有地区";
                    province.setAttribute("onclick", "nation_wide()");
                    province_container.append(province);
                    for (var i = 0; i < data.length; i++) {
                        var province = document.createElement("li");
                        province.setAttribute("onclick", "init_city_list('" + data[i].provinceId + "')");
                        province.innerHTML = data[i].provinceName;
                        province_container.append(province);
                    }
                }
            });
            var pond_container = $(".pond-type");
            pond_container.empty();
            var require_pond_url = "${path.concat('/zonetype/list')}";
            $.get(require_pond_url, function (result) {
                if (result.responseCode == "RESPONSE_OK") {
                    var data = result.data;
                    var pond = document.createElement("li");
                    pond.innerHTML = "所有类型";
                    pond_container.append(pond);
                    for (var i = 0; i < data.length; i++) {
                        var pond = document.createElement("li");
                        pond.innerHTML = data[i].pondTypeName;
                        pond_container.append(pond);
                    }
                }
            });
            var fish_container = $(".fish-type");
            fish_container.empty();
            var require_fish_url = "${path.concat('/fishtype/list')}";
            $.get(require_fish_url, function (result) {
                if (result.responseCode == "RESPONSE_OK") {
                    var data = result.data;
                    var fish = document.createElement("li");
                    fish.innerHTML = "所有鱼种";
                    fish_container.append(fish);
                    for (var i = 0; i < data.length; i++) {
                        var fish = document.createElement("li");
                        fish.innerHTML = data[i].fishName;
                        fish_container.append(fish);
                    }
                }
            });
            $(".region").click(function () {
                $(".lists").hide();
                hidepondeject();
                hidefisheject();
                if ($(".region-eject").hasClass('display')) {
                    $(".lists").show();
                    hideregioneject();
                } else {
                    $("#region-prompt").attr("class", "glyphicon glyphicon-chevron-up small");
                    $('.region-eject').addClass('display');
                }
            });

            $(".pond").click(function () {
                $(".lists").hide();
                hideregioneject();
                hidefisheject();
                if ($(".pond-eject").hasClass('display')) {
                    $(".lists").show();
                    hidepondeject();
                } else {
                    $("#pond-prompt").attr("class", "glyphicon glyphicon-chevron-up small");
                    $('.pond-eject').addClass('display');
                }
            });
            $(".fish").click(function () {
                $(".lists").hide();
                hideregioneject();
                hidepondeject();
                if ($(".fish-eject").hasClass('display')) {
                    $(".lists").show();
                    hidefisheject();
                } else {
                    $("#fish-prompt").attr("class", "glyphicon glyphicon-chevron-up small");
                    $('.fish-eject').addClass('display');
                }
            });
        });
    </script>
</head>
<body>
<div class="content">
    <div class="screening">
        <ul>
            <li class="region">
                <strong>地区</strong>
                &nbsp;<span id="region-prompt" class="glyphicon glyphicon-chevron-down small" aria-hidden="true"></span>
            </li>
            <li class="pond"><strong>类型</strong>
                &nbsp;<span id="pond-prompt" class="glyphicon glyphicon-chevron-down small" aria-hidden="true"></span>
            </li>
            <li class="fish"><strong>鱼种</strong>
                &nbsp;<span id="fish-prompt" class="glyphicon glyphicon-chevron-down small" aria-hidden="true"></span>
            </li>
        </ul>
    </div>
    <div class="region-eject">
        <ul id="region-province" class="region-province">
        </ul>
        <ul class="region-city"></ul>
        <ul class="region-district"></ul>
    </div>
    <div class="pond-eject">
        <ul class="pond-type"></ul>
    </div>
    <div class="fish-eject">
        <ul class="fish-type"></ul>
    </div>
    <div class="lists" style="padding-top: 4em">
    </div>
</div>
</body>
<script>
    var counter = 0;
    var num = 8;
    var pageStart = 0, pageEnd = 0;
    var provinceId = '';
    var cityId = '';
    var districtId = '';

    function list_reload() {
        var timer;
        clearTimeout(timer);
        timer = setTimeout(function () {
            $('.lists').empty();
            $('.dropload-down').remove();
            counter = 0;
            pageStart = 0;
            pageEnd = 0;
            $('.content').dropload({
                scrollArea: window,
                loadDownFn: function (me) {
                    $.ajax({
                        type: 'POST',
                        url: "${path.concat('/fishzone/index')}",
                        data: {
                            start: pageStart,
                            length: num,
                            params: {provinceId: provinceId, cityId: cityId, districtId: districtId}
                        },
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
                                    content += "<span class='label label-info'>" + fishes[f].fishName + "</span> ";
                                }
                                content += "</div>";
                                if (data[i].district != null) {
                                    content += "<label>";
                                    content += data[i].district.city.province.provinceName + data[i].district.city.cityName + data[i].district.districtName;
                                    content += "</label>";
                                }
                                content += "</div>"
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
    }

    $(function () {
        // dropload
        $('.content').dropload({
            scrollArea: window,
            loadDownFn: function (me) {
                $.ajax({
                    type: 'POST',
                    url: "${path.concat('/fishzone/index')}",
                    data: {
                        start: pageStart,
                        length: num,
                        params: {provinceId: provinceId, cityId: cityId, districtId: districtId}
                    },
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
                                content += "<span class='label label-info'>" + fishes[f].fishName + "</span> ";
                            }
                            content += "</div>";
                            if (data[i].district != null) {
                                content += "<label>";
                                content += data[i].district.city.province.provinceName + data[i].district.city.cityName + data[i].district.districtName;
                                content += "</label>";
                            }
                            content += "</div>"
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
