<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>

    <jsp:include page="includes/admin-header-css.jsp" />
</head>
<body>

<div class="container">

    <div style="margin: 5rem 0 0 10rem">

        <C:url var="uploadPhoto" value="${pageContext.request.contextPath}/admin/uploadPhoto" />

        <form:form action="${uploadPhoto}" modelAttribute="user" enctype="multipart/form-data"
                   class="form-horizontal" method="post">

            <form:hidden path="id" value="${user.id}" />

            <div class="row form-group">
                <div class="col-md-2">
                    <label for="upload" >Upload Photo:</label>
                </div>
                <div class="col-md-5">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="uploadAddOn">Upload</span>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" name="photo"
                                   id="upload" aria-describedby="uploadAddOn">
                            <label class="custom-file-label" for="upload">Choose file</label>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row form-group">
                <div class="col-md-5 offset-md-2">
                    <button type="submit" name="upload" class="btn btn-block btn-warning">
                        Upload</button>
                </div>
            </div>
            <!-- end of form-group -->


        </form:form>

    </div>

</div>

</body>

</html>