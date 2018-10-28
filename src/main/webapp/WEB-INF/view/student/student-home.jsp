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
    <title>Student Home</title>

    <jsp:include page="includes/header-css.jsp" />
</head>
<body class="page-header-fixed sidemenu-closed-hidelogo
            page-content-white page-md header-white white-sidebar-color logo-indigo">

<div id="wrapper" class="page-wrapper">

    <!--include nav-->
    <jsp:include page="includes/nav.jsp" />

    <div class="page-container" >


        <!--include siberbar-->

        <jsp:include page="includes/sidebar.jsp" />

        <!-- ######### -->
    <h1>Welcome ${user.email}  ${student.firstName}</h1>


    </div>
</div>

    <jsp:include page="includes/footer-js.jsp" />
</body>
</html>