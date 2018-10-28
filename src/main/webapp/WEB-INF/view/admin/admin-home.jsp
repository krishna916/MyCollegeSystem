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
    <title>${admin.firstName} ${admin.lastName}</title>

   <jsp:include page="includes/admin-header-css.jsp" />

</head>
<body class="page-header-fixed sidemenu-closed-hidelogo
            page-content-white page-md header-white white-sidebar-color logo-indigo">
<div id="wrapper" class="page-wrapper">

    <!--include nav-->
    <jsp:include page="includes/admin-nav.jsp" />

        <div class="page-container" >


            <!--include siberbar-->

            <jsp:include page="includes/admin-sidebar.jsp" />

            <!-- #########--->

            <!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">

                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Dashboard</div>
                                <ol class="breadcrumb page-breadcrumb " style="float: left">
                                    <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <li class="active">Dashboard</li>
                                </ol>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div><!--page container end-->

</div><!--page wrapper end-->



<jsp:include page="includes/admin-footer-js.jsp" />


</body>
</html>