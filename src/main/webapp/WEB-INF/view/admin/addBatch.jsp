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
    <title>Add Batch</title>

    <jsp:include page="includes/admin-header-css.jsp" />
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

        <!-- ###<!-- start page content -->
        <div class="page-content-wrapper">
            <div class="page-content">
                <div class="page-bar">
                    <div class="page-title-breadcrumb">
                        <div class="row">
                            <div class="col-md-4">
                                <div class=" pull-left">
                                    <div class="page-title">Add Batch</div>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <ol class="breadcrumb page-breadcrumb pull-right">
                                    <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="/admin/home">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li><a class="parent-item" href="#">Batches</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li class="active">Add Batch</li>
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
                                <c:url var="addBatch" value="${pageContext.request.contextPath}/admin/addBatch" />
                                <form:form modelAttribute="batchCommand" action="${addBatch}"
                                           id="form_sample_1" method="POST" class="form-horizontal">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-md-5 offset-md-3">
                                                <c:if test="${param.error == true}" >
                                                    <p class="alert alert-danger">Enter Valid Details</p>
                                                </c:if>

                                                <c:if test="${param.courseExists == true}" >
                                                    <p class="alert alert-danger">Course Already Exists</p>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="control-label col-md-3">Batch Code
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="col-md-5">



                                                <form:input path="batchCode" type="text" name="batchCode"
                                                            placeholder="Enter Batch Code" required="required"
                                                            class="form-control input-height" /> </div>
                                            <form:errors path="batchCode"  cssClass="alert alert-danger" />
                                        </div>



                                        <div class="form-group row">
                                            <label class="control-label col-md-3">Department
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="col-md-5">



                                                    <%--<form:select path="departmentCommand.id" items="${department}" />--%>

                                                <form:select path="departmentCommand.id" cssClass="form-control input-height" >
                                                    <form:option value="" label="select" />
                                                    <form:options items="${department}" />

                                                </form:select>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="control-label col-md-3">Course
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="col-md-5">



                                                    <%--<form:select path="departmentCommand.id" items="${department}" />--%>

                                                <form:select path="courseCommand.id" cssClass="form-control input-height" >
                                                    <form:option value="" label="select" />
                                                    <form:options items="${course}" />

                                                </form:select>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="control-label col-md-3">Professor
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="col-md-5">

                                                    <%--<form:select path="departmentCommand.id" items="${department}" />--%>

                                                <form:select path="ProfessorCommand.id" cssClass="form-control input-height" >
                                                    <form:option value="" label="select" />
                                                    <form:options items="${professor}" />

                                                </form:select>
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="control-label col-md-3">Batch Limit
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="col-md-5">

                                                <form:select path="batchLimit" cssClass="form-control input-height">
                                                    <form:option value="" label="select" />
                                                    <form:option value="8" label="8" />
                                                    <form:option value="10" label="10" />
                                                    <form:option value="12" label="12" />
                                                    <form:option value="14" label="14" />
                                                </form:select>

                                            </div>
                                        </div>





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
        <!-- end page content -->######--->






    </div>

</body>


<jsp:include page="includes/admin-footer-js.jsp" />
</body>
</html>