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
    <title>New Registrations</title>

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

        <div class="page-content-wrapper">
            <div class="page-content">
                <div class="page-bar">
                    <div class="page-title-breadcrumb">
                        <div class="row">
                            <div class="col-md-4">
                                <div class=" pull-left">
                                    <div class="page-title">New Registrations</div>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <ol class="breadcrumb page-breadcrumb pull-right">
                                    <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="/admin/home">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li><a class="parent-item" href="#">Students</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li class="active">New Registrations</li>
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
                                                    <header>New Regitrations</header>
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
                                                                <a href="/admin/addStudent" id="addRow" class="btn btn-info">
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
                                                                <th>First Name</th>
                                                                <th>Last Name</th>
                                                                <th> Email </th>
                                                                <th> Department </th>
                                                                <th> Action </th>

                                                            </tr>
                                                            </thead>
                                                            <tbody>


                                                            <c:forEach items="${newStudents}" var="newStudent">

                                                            <tr class="odd gradeX">
                                                                <td class="patient-img">

                                                                </td>
                                                                <td>${newStudent.studentCommand.firstName}</td>
                                                                <td>${newStudent.studentCommand.lastName}</td>
                                                                <td>${newStudent.email}</td>
                                                                <td>${newStudent.studentCommand.departmentCommand.departmentName}</td>

                                                                <td>
                                                                    <a href="${pageContext.request.contextPath}/admin/approveStudent/${newStudent.id}" class="btn btn-primary btn-xs">
                                                                        <i class="fas fa-thumbs-up"></i>
                                                                    </a>
                                                                    <a href="${pageContext.request.contextPath}/admin/deleteNewStudent/${newStudent.id}" class="btn btn-danger btn-xs">
                                                                        <i class="fas fa-trash-alt"></i>
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