<%--
  Created by IntelliJ IDEA.
  User: sunshine
  Date: 11/28/15
  Time: 15:07
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
    <link rel="stylesheet" href="${path.concat('/material/plugins/datatable/css/dataTables.bootstrap.css')}"/>
    <link rel="stylesheet" type="text/css"
          href="${path.concat('/material/css/dashboard.css')}"/>
    <link rel="stylesheet" type="text/css" href="${path.concat('/material/css/customize.css')}"/>
    <link rel="stylesheet" type="text/css" href="${path.concat('/material/css/page.css')}"/>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-1.11.3.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/jquery/jquery-migrate-1.2.1.min.js')}"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/bootstrap-3.3.5-dist/js/bootstrap.js')}"></script>
    <script type="text/javascript" src="/material/plugins/datatable/js/jquery.dataTables.js"></script>
    <script type="text/javascript"
            src="${path.concat('/material/plugins/datatable/js/dataTables.bootstrap.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/dashboard.js')}"></script>
    <script type="text/javascript" src="${path.concat('/material/js/date.js')}"></script>
    <title>鱼种概览</title>
    <script>
        $(function () {
            $("#fish-type-management").collapse('show');
            $("#fish-zone-management").collapse('hide');
            $("#fish-man-management").collapse('hide');
            $("#location-management").collapse('hide');
        });

        $(document).ready(function () {
            $("#fish-type-list").DataTable({
                searching: false,
                ordering: false,
                paging: true,
                "sPaginationType": "full_numbers",
                "bPaginate": false,
                "bLengthChange": false,
                "bSort": false,
                "bDeferRender": true,
                "bProcessing": true,
                "bServerSide": true,
                "bInfoFiltered": false,
                "sAjaxSource": '${path.concat('/fishtype/overview')}',
                "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    $(nRow).removeClass('dataTable');
                    $(nRow).css("text-align", "left");
                    return nRow;
                },
                "fnInitComplete": function () {
                    $("#fish-type-list").removeClass('no-footer');
                },
                "fnServerData": function (sSource, aoData, fnCallback) {
                    $.ajax({
                        "dataType": "json",
                        "type": "POST",
                        "url": sSource,
                        "data": aoData,
                        "success": fnCallback
                    })
                },
                "aoColumns": [
                    {"sTitle": "名称", "sWidth": "10%", "mData": "fishName"},
                    {
                        "sTitle": "添加日期", "sWidth": "10%", "mRender": function (data, type, full) {
                        return ((new Date(full.createAt)).format("yyyy-MM-dd hh:mm:ss"));
                    }
                    },
                    {
                        "sTitle": "操作", "sWidth": "10%", "mRender": function (data, type, full) {
                        var url = "${path.concat('/fishtype/edit/')}" + full.fishId;
                        return "<button type='button' class='btn btn-success btn-group-sm control-box' onclick=\"javascript:location.href='" + url + "'\" id='" + full.fishId + "'>修改</button>";
                    }
                    }
                ],
                "oLanguage": {
                    "sProcessing": "正在加载中",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "抱歉， 没有找到",
                    "sInfo": "从 _START_ 到 _END_, 共 _TOTAL_ 条数据",
                    "sInfoEmpty": "暂无数据",
                    "sInfoFiltered": "数据表中共 _MAX_ 条记录)",
                    "sZeroRecords": "没有检索到数据",
                    "sSearch": "名称:",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
                    }
                }
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
                        class="fa fa-book"></i> 鱼种管理<span class="sr-only">(current)</span><i
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
                        class="fa fa-book"></i> 钓场管理<i class="pull-right fa fa-caret-down"></i></a>
                    <ul id="fish-zone-management" class="nav nav-collapse collapse">
                        <li><a class="sub-nav" href="${path.concat('/fishzone/create')}"><i class="fa fa-edit"></i>
                            添加钓场</a>
                        </li>
                        <li><a class="sub-nav" href="${path.concat('/fishzone/overview')}"><i class="fa fa-tasks"></i>
                            钓场概览</a>
                        <li><a class="sub-nav" href="${path.concat('/zonetype/create')}"><i class="fa fa-edit"></i>
                            添加类型</a>
                        </li>
                        <li><a class="sub-nav" href="${path.concat('/zonetype/overview')}"><i class="fa fa-tasks"></i>
                            类型概览</a>
                        </li>
                    </ul>
                </li>
                <li><a data-toggle="collapse" data-parent="#accordion" href="#fish-man-management"><i
                        class="fa fa-book"></i> 承包人管理<i
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
                <h1><i class="fa fa-home"></i> 鱼种管理 </h1>
                <ol class="breadcrumb">
                    <li><a href="${path.concat('/dashboard')}">首页</a></li>
                    <li><a data-toggle="collapse" data-parent="#accordion" href="#fish-type-management">鱼种管理</a>
                    </li>
                    <li class="active">鱼种概览</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 col-lg-12">
                <table id="fish-type-list" class="table table-striped table-bordered" cellspacing="1" width="100%">
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
