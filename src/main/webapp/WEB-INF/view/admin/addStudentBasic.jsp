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
    <title>AddStudent</title>

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
                                    <div class="page-title">Add Student</div>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <ol class="breadcrumb page-breadcrumb pull-right">
                                    <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="/admin/home">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li><a class="parent-item" href="#">Student</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li class="active">Add Student</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                        <div class="card card-box">
                            <div class="card-head">
                                <header>Basic Information</header>


                            </div>
                            <div class="card-body" id="bar-parent">
                                <c:url var="addBasicStudent"
                                       value="${pageContext.request.contextPath}/admin/addStudentBasicAdmin" />
                                <form:form action="${addBasicStudent}" modelAttribute="userCommand"
                                           id="form_sample_1" class="form-horizontal" method="post">
                                    <div class="form-body">

                                        <div class="form-group row">
                                            <label class="control-label col-md-3">Email
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="col-md-5">
                                                <div class="input-group">
                                                        <span class="input-group-addon">
                                                                <i class="fa fa-envelope"></i>
                                                            </span>
                                                    <form:input path="email"  type="email" class="form-control input-height"
                                                                placeholder="Email Address" required="required" />
                                                    <form:errors path="email"  cssClass="alert alert-danger" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="control-label col-md-3">Password
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="col-md-5">
                                                <form:password path="password" name="password" placeholder="enter Password"
                                                               class="form-control input-height" required="required" />
                                                <form:errors path="password"  cssClass="alert alert-danger" />
                                            </div>
                                        </div>
                                                <form:hidden path="role" name="role" value="ROLE_STUDENT" />
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="offset-md-3 col-md-9">
                                                    <button type="submit" class="btn btn-info m-r-20">Submit</button>
                                                    <button type="button" class="btn btn-default">Cancel</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end page content -->






    </div>
</div>


<jsp:include page="includes/admin-footer-js.jsp" />

</body>
</html>