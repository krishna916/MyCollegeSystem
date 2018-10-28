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
    <title>College System</title>

    <jsp:include page="aa-includes/header-css.jsp" />

</head>
<body>

<!--Main Wrapper Start-->
<div class="as-mainwrapper">
    <!--Bg White Start-->
    <div class="bg-white">

    <!-- include nav-->
    <jsp:include page="aa-includes/public-nav.jsp" />
    <!--nav end-->




        <div class="slider-area">
            <div class="hero-slider owl-carousel">
                <!--Single Slider Start-->
                <div class="single-slider"  style="background-image: url(public/img/slider/1.jpg)">
                    <div class="hero-slider-content">
                        <h1>Education Needs <br> Complete Solution</h1>6g
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque unde, at molestias voluptatem praesentium quia magnam? Iste aliquam, voluptas sapiente animi, repudiandae officiis voluptatem tempore alias nihil. Aperiam voluptatum, velit.</p>
                        <div class="slider-btn">
                            <a class="button-default" href="course.html">View Courses</a>
                        </div>
                    </div>
                </div>
                <!--Single Slider End-->
                <!--Single Slider Start-->
                <div class="single-slider"  style="background-image: url(public/img/slider/2.jpg)">
                    <div class="hero-slider-content">
                        <h1>Education Needs <br> Complete Solution</h1>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque unde, at molestias voluptatem praesentium quia magnam? Iste aliquam, voluptas sapiente animi, repudiandae officiis voluptatem tempore alias nihil. Aperiam voluptatum, velit.</p>
                        <div class="slider-btn">
                            <a class="button-default" href="course.html">View Courses</a>
                        </div>
                    </div>
                </div>
                <!--Single Slider End-->
                <!--Single Slider Start-->
                <div class="single-slider"  style="background-image: url(public/img/slider/3.jpg)">
                    <div class="hero-slider-content">
                        <h1>Education Needs <br> Complete Solution</h1>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque unde, at molestias voluptatem praesentium quia magnam? Iste aliquam, voluptas sapiente animi, repudiandae officiis voluptatem tempore alias nihil. Aperiam voluptatum, velit.</p>
                        <div class="slider-btn">
                            <a class="button-default" href="course.html">View Courses</a>
                        </div>
                    </div>
                </div>
                <!--Single Slider End-->
                <!--Single Slider Start-->
                <div class="single-slider"  style="background-image: url(public/img/slider/4.jpg)">
                    <div class="hero-slider-content">
                        <h1>Education Needs <br> Complete Solution</h1>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque unde, at molestias voluptatem praesentium quia magnam? Iste aliquam, voluptas sapiente animi, repudiandae officiis voluptatem tempore alias nihil. Aperiam voluptatum, velit.</p>
                        <div class="slider-btn">
                            <a class="button-default" href="course.html">View Courses</a>
                        </div>
                    </div>
                </div>
                <!--Single Slider End-->
            </div>
        </div>
        <!--Slider Area End-->





    </div><!--End of Bg White-->
</div><!--End of Main Wrapper Area-->


    <jsp:include page="aa-includes/footer-js.jsp" />
</body>
</html>