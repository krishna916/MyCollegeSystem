<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>All Batches</title>

    <jsp:include page="includes/admin-header-css.jsp" />

    <!-- dropzone -->


</head>
<body class="page-header-fixed sidemenu-closed-hidelogo
            page-content-white page-md header-white white-sidebar-color logo-indigo">

<div id="wrapper" class="page-wrapper">

    <!--include nav-->
    <jsp:include page="includes/admin-nav.jsp" />
    <!-- ######-->
    <div class="page-container" >


        <!--include siberbar-->

        <jsp:include page="includes/admin-sidebar.jsp" />

        <!-- #########--->

        <!-- start page content -->
        <div class="page-content-wrapper">
            <div class="page-content">
                <div class="page-bar">
                    <div class="page-title-breadcrumb">
                        <div class="row">
                            <div class="col-md-4">
                                <div class=" pull-left">
                                    <div class="page-title">All Batches List</div>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <ol class="breadcrumb page-breadcrumb pull-right">
                                    <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="/admin/home">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li><a class="parent-item" href="#">Batches</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li class="active">All Batches</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="table table-line">

                            <div class="tab-content">
                                <div class="tab-pane active fontawesome-demo" id="tab1">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card card-box">
                                                <div class="card-head">
                                                    <header>All Batches List</header>
                                                    <div class="tools">
                                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
                                                        <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
                                                        <a class="t-close btn-color fa fa-times" href="javascript:;"></a>
                                                    </div>
                                                </div>
                                                <div class="card-body ">
                                                    <div class="row">
                                                        <div class="col-md-6 col-sm-6 col-6">
                                                            <div class="btn-group">
                                                                <a href="${pageContext.request.contextPath}/admin/addBatch" id="addRow" class="btn btn-info">
                                                                    Add New <i class="fa fa-plus"></i>
                                                                </a>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 col-sm-6 col-6">
                                                            <div class="btn-group pull-right">
                                                                <a class="btn deepPink-bgcolor  btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                                                                    <i class="fa fa-angle-down"></i>
                                                                </a>
                                                                <ul class="dropdown-menu pull-right">
                                                                    <li>
                                                                        <button class="btn btn-block btn-light" onclick="myPrint()">
                                                                            <i class="fa fa-print"></i> Print </button>
                                                                    </li>
                                                                    <li>
                                                                        <a href="javascript:;">
                                                                            <i class="fa fa-file-pdf-o"></i> Save as PDF </a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="javascript:;">
                                                                            <i class="fa fa-file-excel-o"></i> Export to Excel </a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="table-scrollable">
                                                        <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle" id="example4">
                                                            <thead>
                                                            <tr>
                                                                <th></th>
                                                                <th> Batch code </th>
                                                                <th> Professor Name </th>
                                                                <th> Course Name </th>
                                                                <th> Department </th>
                                                                <th> Max Size Of Batch </th>
                                                                <th> Batch Size </th>
                                                                <th> Action </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>


                                                            <c:forEach items="${batchCommand}" var="batchCommand">

                                                            <tr class="odd gradeX">
                                                                <td></td>
                                                                <td>${batchCommand.batchCode}</td>
                                                                <td>${batchCommand.professorCommand.firstName} ${batchCommand.professorCommand.lastName}</td>
                                                                <td>${batchCommand.courseCommand.courseName}</td>
                                                                <td>${batchCommand.departmentCommand.departmentName}</td>
                                                                <td>${batchCommand.batchLimit}</td>
                                                                <td>${batchCommand.numberOfStudents}</td>

                                                                <td>

                                                                    <a href="${pageContext.request.contextPath}/admin/addStudentsToBatch/${batchCommand.id}"
                                                                       class="btn btn-danger btn-xs">
                                                                        <i class="fa fa-plus"></i><i class="fas fa-user-graduate"></i>
                                                                    </a>
                                                                </td>

                                                                </c:forEach>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



    </div>
</div>



<jsp:include page="includes/admin-footer-js.jsp" />
</body>
</html>