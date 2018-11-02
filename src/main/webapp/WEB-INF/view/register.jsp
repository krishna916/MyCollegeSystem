<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/fontawesome-all.min.css">
    <link rel="stylesheet" type="text/css" href="css/iofrm-style.css">
    <link rel="stylesheet" type="text/css" href="css/iofrm-theme5.css">
</head>
<body>

<div class="form-body">
    <div class="website-logo">
        <a href="index-2.html">
            <div class="logo">
                <img class="logo-size" src="images/logo-light.svg" alt="">
            </div>
        </a>
    </div>
    <div class="row">
        <div class="img-holder">
            <div class="bg"></div>
            <div class="info-holder">
                <img src="images/graphic2.svg" alt="">
            </div>
        </div>
        <div class="form-holder">
            <div class="form-content">
                <div class="form-items">
                    <h3>Get more things done with Loggin platform.</h3>
                    <p>Access to the most powerfull tool in the entire design and web industry.</p>
                    <div class="page-links">
                        <c:url var="login" value="${pageContext.request.contextPath}/loginPage" />
                        <c:url var="register" value="${pageContext.request.contextPath}/register" />
                        <a href="${login}">Login</a><a href="${register}" class="active">Register</a>
                    </div>


                        <c:if test="${ param.error == true }" >

                            <p class="alert alert-danger"> Invalid Details</p>
                        </c:if>

                        <c:if test="${ param.userExists == true }" >
                            <p class="alert alert-danger"> Student Already Exists</p>
                        </c:if>

                        <c:if test="${ param.registered == true }" >
                            <p class="alert alert-success"> Registered Successfully</p>
                        </c:if>


                    <c:url var="register" value="${pageContext.request.contextPath}/register" />
                    <form:form action="/register" method="post" modelAttribute="userCommand" >

                        <form:input path="studentCommand.firstName" class="form-control" type="text"  placeholder="First Name" required="required" />
                        <form:errors path="studentCommand.firstName" cssClass="alert alert-danger" />

                        <form:input path="studentCommand.lastName" class="form-control" type="text"  placeholder="Last Name" required="required" />
                        <form:errors path="studentCommand.lastName" cssClass="alert alert-danger" />

                        <form:input path="email" class="form-control" type="email"  placeholder="E-mail Address" required="required" />
                        <form:errors path="email" cssClass="alert alert-danger" />


                        <form:password path="password"  id="password"  class="form-control mb-0" pattern=".{8,}" placeholder="Password" required="required" />
                        <small id="passwordHelpBlock" class="form-text text-muted mb-2 " style="color: #fffdff">
                            Password must contain atleast 8 characters
                        </small>


                        <form:errors path="password" cssClass="alert alert-danger" />


                        <input class="form-control" id="confirmPassword"  type="password" name="confirmPassword" placeholder="Confirm Password" required>
                        <small id="error"></small>

                        <form:select path="studentCommand.departmentCommand.id" cssClass="form-control" >
                            <form:option value="" label="Select Department" />
                            <form:options items="${department}" />

                        </form:select>


                        <form:hidden path="role" value="ROLE_STUDENT" />

                        <div class="form-button">
                            <button id="submit" type="submit" class="ibtn">Register</button>
                        </div>
                    </form:form>
                    <div class="other-links">
                        <span>Or register with</span><a href="#">Facebook</a><a href="#">Google</a><a href="#">Linkedin</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script src="js/custom.js"></script>

</body>
</html>