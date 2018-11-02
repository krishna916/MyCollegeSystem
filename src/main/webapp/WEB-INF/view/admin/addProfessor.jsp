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
    <title>Add Professor</title>

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
                                    <div class="page-title">Add Professor</div>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <ol class="breadcrumb page-breadcrumb pull-right">
                                    <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="/admin/home">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li><a class="parent-item" href="#">Professor</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li class="active">Add Profesor</li>
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
                                <c:url var="addProfesor" value="${pageContext.request.contextPath}/admin/addProfessor" />
                                <form:form modelAttribute="professorCommand" action="${addProfesor}"
                                           id="form_sample_1" method="POST" class="form-horizontal">
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-md-5 offset-md-3">
                                            <c:if test="${param.error == true}" >
                                                <p class="alert alert-danger">Enter Valid Details</p>
                                            </c:if>

                                            <c:if test="${param.alreadyExists == true}" >
                                                <p class="alert alert-danger">Professor Already Exits</p>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-md-3">First Name
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-5">

                                            <form:hidden path="userCommand.role" value="ROLE_PROFESSOR" />

                                            <form:input path="firstName" type="text" name="firstname"
                                                        placeholder="enter first name" required="required"
                                                        class="form-control input-height" /> </div>
                                        <form:errors path="firstName"  cssClass="alert alert-danger" />
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-md-3">Last Name
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-5">
                                            <form:input path="lastName" type="text" name="lastname" required="required" placeholder="enter last name" class="form-control input-height" /> </div>
                                        <form:errors path="lastName"  cssClass="alert alert-danger" />
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-md-3">Email
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-5">
                                            <form:input path="userCommand.email" type="text" name="email"
                                                        required="required" placeholder="enter Email" class="form-control input-height" /> </div>
                                        <form:errors path="userCommand.email"  cssClass="alert alert-danger" />
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-md-3">Password
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-5">
                                            <form:password path="userCommand.password"  name="password"
                                                           required="required" placeholder="enter Default Password" class="form-control input-height" /> </div>
                                        <form:errors path="userCommand.password"  cssClass="alert alert-danger" />
                                    </div>


                                    <div class="form-group row">
                                        <label class="control-label col-md-3">Gender
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-5">

                                            <form:select path="gender" required="required" cssClass="form-control input-height">
                                                <form:option value="" label="Select" />
                                                <form:option value="Male" label="MALE" />
                                                <form:option value="Female" label="FEMALE" />
                                            </form:select>
                                            <form:errors path="gender"  cssClass="alert alert-danger" />
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-md-3">Mobile No.
                                            <span class="required">  </span>
                                        </label>
                                        <div class="col-md-5">
                                            <form:input path="phone" name="phone" type="text" pattern="[789][0-9]{9}"
                                                        placeholder="mobile number" class="form-control input-height" />
                                            <form:errors path="phone"  required="required" cssClass="alert alert-danger" />
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-md-3">Date Of Birth
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="col-md-5">

                                            <form:input path="dob" min="1958-01-01" max="1993-12-31" value="yyyy/mm/dd" id="myDate"
                                                        required="required" class="form-control input-height" size="16" placeholder="date of Birth" type="date" />

                                            <form:errors path="dob"  cssClass="alert alert-danger" />
                                        </div>

                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="control-label col-md-3">Address
                                        <span class="required">  </span>
                                    </label>
                                    <div class="col-md-5">
                                        <form:textarea path="address" name="address" required="required"
                                                       placeholder="address" class="form-control-textarea" rows="5" ></form:textarea>
                                        <form:errors path="address"  cssClass="alert alert-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="control-label col-md-3">State
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-5">
                                        <form:input path="state"  required="required" name="state" type="text" placeholder="State" class="form-control input-height" /> </div>
                                    <form:errors path="state"  cssClass="alert alert-danger" />
                                </div>
                                <div class="form-group row">
                                    <label class="control-label col-md-3">City
                                        <span class="required"> * </span>
                                    </label>
                                    <div class="col-md-5">
                                        <form:input path="city" required="required" name="city" type="text" placeholder="City" class="form-control input-height" /> </div>
                                    <form:errors path="city"  cssClass="alert alert-danger" />
                                </div>
                                <div class="form-group row">
                                    <label class="control-label col-md-3">Blood Group
                                        <span class="required">  </span>
                                    </label>
                                    <div class="col-md-5">
                                        <form:input path="bloodGroup" name="bloodGroup" type="text" placeholder="Blood Group" class="form-control input-height" /> </div>
                                    <form:errors path="bloodGroup"  cssClass="alert alert-danger" />
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