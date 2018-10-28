<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>



<!-- start sidebar menu -->
<div class="sidebar-container">
    <div class="sidemenu-container navbar-collapse collapse fixed-menu">
        <div id="remove-scroll" class="left-sidemenu">
            <ul class="sidemenu  page-header-fixed slimscroll-style" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
                <li class="sidebar-toggler-wrapper hide">
                    <div class="sidebar-toggler">
                        <span></span>
                    </div>
                </li>
                <li class="sidebar-user-panel">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="data:image/jpeg;base64,${userPhoto}" class="img-circle user-img-circle" alt="User Image" />

                        </div>
                        <div class="pull-left info">
                            <p>${admin.firstName}</p>
                            <a href="#"><i class="fa fa-circle user-online"></i><span class="txtOnline"> Online</span></a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="fas fa-tachometer-alt"></i>
                        <span class="title">Dashboard</span>

                        <span class="pull-right"><i class="fas fa-angle-right"></i></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item active">
                            <a href="/admin/home" class="nav-link ">
                                <span class="title">Admin Home</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a href="dashboard2.html" class="nav-link ">
                                <span class="title">Dashboard 2</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="dashboard3.html" class="nav-link ">
                                <span class="title">Dashboard 3</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="/admin/upload/${user.id}" class="nav-link nav-toggle"> <i class="material-icons">event</i>
                        <span class="title">Upload Photo</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle"> <i class="fas fa-chalkboard-teacher"></i></i>
                        <span class="title">Professors</span> <span class="pull-right"><i class="fas fa-angle-right"></i></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="all_professors.html" class="nav-link "> <span class="title">All Professors</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_professor.html" class="nav-link "> <span class="title">Add Professor</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_professor_bootstrap.html" class="nav-link "> <span class="title">Add Professor Bootstrap</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="edit_professor.html" class="nav-link "> <span class="title">Edit Professor</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="professor_profile.html" class="nav-link "> <span class="title">About Professor</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle"><i class="fas fa-user-friends"></i>
                        <span class="title">Students</span><span class="pull-right"><i class="fas fa-angle-right"></i></span></a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="${pageContext.servletContext.contextPath}/admin/showAllStudents" class="nav-link "> <span class="title">All Students</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.servletContext.contextPath}/admin/addStudent" class="nav-link "> <span class="title">Add Student</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.servletContext.contextPath}/admin/addStudentBasic" class="nav-link ">
                                <span class="title">Add Basic Student</span>

                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.servletContext.contextPath}/admin/newRegistrations" class="nav-link "> <span class="title">New Registrations</span>

                                <c:if test="${user.countNewStudents != null && user.countNewStudents != 0}" >
                                <span class="badge badge-success mt-2">${user.countNewStudents}</span>
                                </c:if>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="student_profile.html" class="nav-link "> <span class="title">About Student</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle"> <i class="material-icons">school</i>
                        <span class="title">Courses</span> <span class="arrow"></span>
                        <span class="label label-rouded label-menu label-success">new</span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="all_courses.html" class="nav-link "> <span class="title">All Courses</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_course.html" class="nav-link "> <span class="title">Add Course</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_course_bootstrap.html" class="nav-link "> <span class="title">Add Course Bootstrap</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="edit_course.html" class="nav-link "> <span class="title">Edit Course</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="course_details.html" class="nav-link "> <span class="title">About Course</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle"> <i class="material-icons">local_library</i>
                        <span class="title">Library</span> <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="all_assets.html" class="nav-link "> <span class="title">All Library Assets</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_library.html" class="nav-link "> <span class="title">Add Library Asset</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_library_bootstrap.html" class="nav-link "> <span class="title">Add Asset Bootstrap</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="edit_library.html" class="nav-link "> <span class="title">Edit Asset</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle"> <i class="material-icons">business</i>
                        <span class="title">Departments</span> <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="all_department.html" class="nav-link "> <span class="title">All Departments</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_department.html" class="nav-link "> <span class="title">Add Department</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_department_bootstrap.html" class="nav-link "> <span class="title">Add Department Bootstrap</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="edit_department.html" class="nav-link "> <span class="title">Edit Department</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle"> <i class="material-icons">face</i>
                        <span class="title">Staff</span> <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="all_staffs.html" class="nav-link "> <span class="title">All Staff</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_staff.html" class="nav-link "> <span class="title">Add Staff</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_staff_bootstrap.html" class="nav-link "> <span class="title">Add Staff Bootstrap</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="edit_staff.html" class="nav-link "> <span class="title">Edit Staff</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="staff_profile.html" class="nav-link "> <span class="title">Staff Profile</span>
                            </a>
                        </li>
                    </ul>
                </li>


                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle"> <i class="material-icons">monetization_on</i>
                        <span class="title">Fees</span> <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="nav-item">
                            <a href="fees_collection.html" class="nav-link "> <span class="title">Fees Collection</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_fees.html" class="nav-link "> <span class="title">Add Fees </span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="add_fees_bootstrap.html" class="nav-link "> <span class="title">Add Fees Bootstrap</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="fees_receipt.html" class="nav-link "> <span class="title">Fee Receipt</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--end of sidebar container-->