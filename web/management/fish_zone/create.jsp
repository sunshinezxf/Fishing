<%--
  Created by IntelliJ IDEA.
  User: sunshine
  Date: 11/18/15
  Time: 20:49
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
          href="${path.concat('/material/plugins/summernote-master/dist/summernote.css')}"/>
    <link rel="stylesheet" type="text/css"
          href="${path.concat('/material/css/dashboard.css')}"/>
    <link rel="stylesheet" type="text/css" href="${path.concat('/material/css/customize.css')}"/>
    <link rel="stylesheet" type="text/css"
          href="${path.concat('/material/plugins/bootstrap-fileinput/fileinput.css')}"/>
    <link rel="stylesheet" type="text/css"
          href="${path.concat('/material/plugins/bootstrap-fileupload/fileupload.css')}"/>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-1.11.3.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-migrate-1.2.1.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/bootstrap-3.3.5-dist/js/bootstrap.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/summernote-master/dist/summernote.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/summernote-master/lang/summernote-zh-CN.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/plugins/bootstrap-fileinput/fileinput.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/bootstrap-fileupload/fileupload.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/dashboard.js')}"></script>
    <title>添加钓场</title>
    <script>
        $(function () {
            $("#fish-type-management").collapse('hide');
            $("#fish-zone-management").collapse('show');
            $("#fish-man-management").collapse('hide');
            $("#location-management").collapse('hide');
        });
        $(document).ready(function () {
            $("#pond-introduction").summernote({
                lang: "zh-CN",
                height: 280
            });

            $("#confirm-zone").click(function (e) {
                var introduction = $("#pond-introduction").code();
                $("#pond-introduction").attr("value", introduction);
                console.debug(introduction);

                //1st step, verify input
                console.debug($("#fish-zone-name").val());
                console.debug($("#fish-pond-manager").val());
                console.debug($("#zone-longitude").val());
                console.debug($("#zone-latitude").val());
                //2nd step, construct the form
                var url = "${path.concat('/fishzone/create')}"
                $("#insert-fishzone-form").attr("action", url);
                $("#insert-fishzone-form").attr("method", "post");
                $("#insert-fishzone-form").attr("enctype", "multipart/form-data");

                //3rd step, submit the form
                $("#insert-fishzone-form").submit();
            });
        });
    </script>
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
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar" id="accordion" aria-multiselectable="true">
                <li><a href="${path.concat('/dashboard')}"><i class="fa fa-desktop"></i> 首页</a></li>
                <li><a data-toggle="collapse" data-parent="#accordion" href="#fish-type-management"><i
                        class="fa fa-book"></i> 鱼种管理<i
                        class="pull-right fa fa-caret-down"></i></a>
                    <ul id="fish-type-management" class="nav nav-collapse collapse">
                        <li><a class="sub-nav" href="${path.concat('/fishtype/create')}"><i class="fa fa-edit"></i>
                            添加鱼种</a>
                        </li>
                        <li><a class="sub-nav" href="${path.concat('/fishtype/overview')}"><i class="fa fa-tasks"></i>
                            鱼种概览</a>
                        </li>
                    </ul>
                </li>
                <li><a data-toggle="collapse" data-parent="#accordion" href="#fish-zone-management"><i
                        class="fa fa-book"></i> 钓场管理<span class="sr-only">(current)</span><i
                        class="pull-right fa fa-caret-down"></i></a>
                    <ul id="fish-zone-management" class="nav nav-collapse collapse">
                        <li><a class="sub-nav" href="${path.concat('/fishzone/create')}"><i class="fa fa-edit"></i>
                            添加钓场</a>
                        </li>
                        <li><a class="sub-nav" href="${path.concat('/fishzone/overview')}"><i class="fa fa-tasks"></i>
                            钓场概览</a>
                        </li>
                        <li><a class="sub-nav" href="${path.concat('/zonetype/create')}"><i class="fa fa-edit"></i>
                            添加类型</a>
                        </li>
                        <li><a class="sub-nav" href="${path.concat('/zonetype/overview')}"><i class="fa fa-tasks"></i>
                            类型概览</a>
                        </li>
                    </ul>
                </li>
                <li><a data-toggle="collapse" data-parent="#accordion" href="#fish-man-management"><i
                        class="fa fa-book"></i> 承包管理<i
                        class="pull-right fa fa-caret-down"></i></a>
                    <ul id="fish-man-management" class="nav nav-collapse collapse">
                        <li><a class="sub-nav" href="${path.concat('/fishman/create')}"><i class="fa fa-edit"></i>
                            添加承包人</a>
                        </li>
                        <li><a class="sub-nav" href="${path.concat('/fishman/overview')}"><i class="fa fa-tasks"></i>
                            承包人概览</a>
                        </li>
                    </ul>
                </li>
                <li><a data-toggle="collapse" data-parent="#accordion" href="#location-management"><i
                        class="fa fa-book"></i> 地址管理<i
                        class="pull-right fa fa-caret-down"></i></a>
                    <ul id="location-management" class="nav nav-collapse collapse">
                        <li><a class="sub-nav" href="${path.concat('/location/overview')}"><i class="fa fa-tasks"></i>
                            地址概览</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <div class="row">
            <div class="col-md-12 col-lg-12">
                <h1><i class="fa fa-home"></i> 钓场管理 </h1>
                <ol class="breadcrumb">
                    <li><a href="${path.concat('/dashboard')}">首页</a></li>
                    <li><a data-toggle="collapse" data-parent="#accordion" href="#fish-zone-management">钓场管理</a></li>
                    <li class="active">添加钓场</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 col-lg-12">
                <hr/>
                <form id="insert-fishzone-form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="fish-zone-name">鱼塘名称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="fish-zone-name" name="pondName"
                                   placeholder="钓场名称" required="" autocomplete="off"/>
                        </div>
                        <button type="button" class="btn btn-success btn-group-sm col-sm-1 control-box">检测</button>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="zone-contractor-name">承包人姓名</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zone-contractor-name" name="contractorName"
                                   placeholder="承包人姓名" autocomplete="off"/>
                        </div>
                        <label class="col-sm-2 control-label" for="zone-contractor-phone">承包人电话</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zone-contractor-phone" name="contractorPhone"
                                   placeholder="承包人电话" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="zone-longitude">经度</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zone-longitude" name="longitude"
                                   placeholder="经度" autocomplete="off"/>
                        </div>
                        <label class="col-sm-2 control-label" for="zone-latitude">纬度</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zone-latitude" name="latitude"
                                   placeholder="纬度" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="fish-zone-address">地址简介</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="fish-zone-address" name="pondAddress"
                                   placeholder="钓场地址简介" required="" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="fish-zone-fee">收费</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="fish-zone-fee" name="pondFee"
                                   placeholder="费用" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">可否夜钓</label>

                        <div class="col-sm-8">
                            <label class="radio-inline">
                                <input type="radio" name="nightable" id="fish-zone-night-permit" value="true"> 可以
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="nightable" id="fish-zone-night-deny" value="false"
                                       checked="checked"> 不可以
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">钓场类型</label>

                        <div class="col-sm-8">
                            <c:if test="${not empty typeList}">
                                <c:forEach var="item" items="${typeList}" varStatus="no">
                                    <c:if test="${no.index % 5 == 0}">
                                        <div class="form-group col-sm-12"> </c:if>
                                    <label class="checkbox-inline col-sm-2">
                                        <input type="checkbox" id="${item.pondTypeId}" name="pondType"
                                               value="${item.pondTypeId}"> <span
                                            for="${item.pondTypeId}">${item.pondTypeName}</span>
                                    </label>
                                    <c:if test="${no.index % 5 == 4 or no.last}"></div></c:if>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">主要鱼种</label>

                        <div class="col-sm-8">
                            <c:if test="${not empty fishList}">
                                <c:forEach var="item" items="${fishList}" varStatus="no">
                                    <c:if test="${no.index % 5 == 0}"><div class="form-group col-sm-12"> </c:if>
                                    <label class="checkbox-inline col-sm-2">
                                        <input type="checkbox" id="${item.fishId}" name="fishType"
                                               value="${item.fishId}"> <span
                                            for="${item.fishId}">${item.fishName}</span>
                                    </label>
                                    <c:if test="${no.index %5 == 4 or no.last}"></div></c:if>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">钓场图片</label>

                        <div class="col-sm-8">
                            <div class="fileinput file-input-new fileinput-inline" data-provides="fileinput">
                                <div class="fileinput-preview thumbnail">
                                    <img src="${path.concat('/material/image/blank.gif')}" alt="">
                                </div>
                                <div>
                                    <span class="btn btn-default btn-success btn-file">
                                        <span class="file-input-new">选择图片</span>
                                        <input type="file" multiple name="pondThumbnail"/>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">钓场简介</label>

                        <div class="col-sm-9">
                                <textarea id="pond-introduction" name="pondIntroduction"
                                          class="form-control"></textarea>
                        </div>
                    </div>
                    <hr/>
                    <button type="submit" id="confirm-zone" class="btn btn-primary btn-group-sm col-sm-1">添加
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
