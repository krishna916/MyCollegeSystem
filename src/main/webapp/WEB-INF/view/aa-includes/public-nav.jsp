<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>





<!--Header Area Start-->
<header>
    <div class="header-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-md-6 col-12">
                    <div class="pull-left">
                        <span>Have any question? +968 547856 254</span>
                    </div>

                </div>
                <div class="col-lg-7 col-md-6 col-12">
                    <div class="pull-right">
                        <span>Phone: +85 4856 5478</span>
                        <span>Email: info@example.com</span>
                        <span class="ml-3"><a href="${pageContext.request.contextPath}/register" class="btn btn-danger">Register</a> </span>
                        <span class="ml-3"><a href="${pageContext.request.contextPath}/loginPage" class="btn btn-danger">Login</a> </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="header-logo-menu sticker">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-12">
                    <div class="logo">
                        <a href="index.html"><img src="public/img/logo/logo.png" alt="EDUCAT"></a>
                    </div>
                </div>
                <div class="col-lg-9 col-12">
                    <div class="mainmenu-area pull-right">
                        <div class="mainmenu d-none d-lg-block">
                            <nav>
                                <ul id="nav">
                                    <li class="current"><a href="index.html">Home</a>
                                        <ul class="sub-menu">
                                            <li><a href="index.html">Home Version 1</a></li>
                                            <li><a href="index-2.html">Home Version 2</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="course.html">Courses</a>
                                        <ul class="sub-menu">
                                            <li><a href="courses-details.html">Courses Details</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Features</a>
                                        <ul class="sub-menu">
                                            <li><a href="#">Blog<i class="zmdi zmdi-chevron-right"></i></a>
                                                <ul class="inside-menu">
                                                    <li><a href="blog.html">Blog</a></li>
                                                    <li><a href="blog-details.html">Blog Details</a></li>
                                                    <li><a href="blog-left-sidebar.html">Blog Left Sidebar</a></li>
                                                    <li><a href="blog-no-sidebar.html">Blog No Sidebar</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">Shop<i class="zmdi zmdi-chevron-right"></i></a>
                                                <ul class="inside-menu">
                                                    <li><a href="shop.html">Shop</a></li>
                                                    <li><a href="single-product.html">Single Product</a></li>
                                                    <li><a href="cart.html">Shopping Cart</a></li>
                                                    <li><a href="wishlist.html">Wishlist</a></li>
                                                    <li><a href="checkout.html">Checkout</a></li>
                                                    <li><a href="login-register.html">Login - Register</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a href="index.html">Pages</a>
                                        <ul class="sub-menu">
                                            <li><a href="team-details.html">Team Details</a></li>
                                            <li><a href="course.html">Courses Page</a></li>
                                            <li><a href="courses-details.html">Course Details Page</a></li>
                                            <li><a href="event.html">Event Page</a></li>
                                            <li><a href="event-details.html">Event Details Page</a></li>
                                            <li><a href="blog.html">Blog</a></li>
                                            <li><a href="blog-details.html">Blog Details Page</a></li>
                                            <li><a href="blog-left-sidebar.html">Blog Left Sidebar</a></li>
                                            <li><a href="blog-no-sidebar.html">Blog No Sidebar</a></li>
                                            <li><a href="wishlist.html">Wishlist Page</a></li>
                                            <li><a href="checkout.html">Checkout Page</a></li>
                                            <li><a href="cart.html">Shopping Cart Page</a></li>
                                            <li><a href="login-register.html">Login Page</a></li>
                                            <li><a href="contact.html">Contact</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="shop.html">Shop</a>
                                        <ul class="sub-menu">
                                            <li><a href="single-product.html">Single Product Page</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="contact.html">Contact</a></li>
                                </ul>
                            </nav>
                        </div>
                        <ul class="header-search">
                            <li class="search-menu">
                                <i id="toggle-search" class="zmdi zmdi-search"></i>
                            </li>
                        </ul>
                        <!--Search Form-->
                        <div class="search">
                            <div class="search-form">
                                <form id="search-form" action="#">
                                    <input type="search" placeholder="Search here..." name="search" />
                                    <button type="submit">
                                        <span><i class="fa fa-search"></i></span>
                                    </button>
                                </form>
                            </div>
                        </div>
                        <!--End of Search Form-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Mobile Menu Area start -->
    <div class="mobile-menu-area">
        <div class="container clearfix">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="mobile-menu">
                        <nav id="dropdown">
                            <ul>
                                <li><a href="index.html">HOME</a>
                                    <ul>
                                        <li><a href="index.html">Home Version 1</a></li>
                                        <li><a href="index-2.html">Home Version 1</a></li>
                                    </ul>
                                </li>
                                <li><a href="team-details.html">Team Details</a></li>
                                <li><a href="course.html">Courses</a>
                                    <ul class="sub-menu">
                                        <li><a href="courses-details.html">Courses Details</a></li>
                                    </ul>
                                </li>
                                <li><a href="shop.html">Shop</a>
                                    <ul class="sub-menu">
                                        <li><a href="single-product.html">Single Product</a></li>
                                    </ul>
                                </li>
                                <li><a href="event.html">Event</a>
                                    <ul class="sub-menu">
                                        <li><a href="event-details.html">Event Details</a></li>
                                    </ul>
                                </li>
                                <li><a href="blog.html">Blog</a>
                                    <ul class="sub-menu">
                                        <li><a href="blog-details.html">Blog Details</a></li>
                                        <li><a href="blog-left-sidebar.html">Blog Left Sidebar</a></li>
                                        <li><a href="blog-no-sidebar.html">Blog No Sidebar</a></li>
                                    </ul>
                                </li>
                                <li><a href="contact.html">Pages</a>
                                    <ul>
                                        <li><a href="team-details.html">Team Details</a></li>
                                        <li><a href="course.html">Courses Page</a></li>
                                        <li><a href="courses-details.html">Course Details Page</a></li>
                                        <li><a href="event.html">Event Page</a></li>
                                        <li><a href="event-details.html">Event Details Page</a></li>
                                        <li><a href="blog.html">Blog</a></li>
                                        <li><a href="blog-details.html">Blog Details Page</a></li>
                                        <li><a href="blog-left-sidebar.html">Blog Left Sidebar</a></li>
                                        <li><a href="blog-no-sidebar.html">Blog No Sidebar</a></li>
                                        <li><a href="wishlist.html">Wishlist Page</a></li>
                                        <li><a href="checkout.html">Checkout Page</a></li>
                                        <li><a href="cart.html">Shopping Cart Page</a></li>
                                        <li><a href="login-register.html">Login Page</a></li>
                                        <li><a href="contact.html">Contact</a></li>
                                    </ul>
                                </li>
                                <li><a href="contact.html">Contact us</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Mobile Menu Area end -->
</header>
<!--End of Header Area-->
