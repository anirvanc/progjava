<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>User</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/override.css" rel ="stylesheet">

</head>
<body>

<!-- Topbar
    ================================================== -->
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="#">UserManagementSystem</a>
            <ul class="nav">
                <li class="active"><a href="/users.html">Users</a></li>
                <li><a href="/departments.html">Departments</a></li>
                <li><a href="#">Support</a></li>
            </ul>
            <ul class="nav pull-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Super Admin <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">My Team</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="container">

    <div class="content">
        <div class="page-header">
            <h1><c:choose><c:when test="${empty user.nodeId}">Add</c:when><c:otherwise>Edit</c:otherwise></c:choose> User</h1>
        </div>

        <!-- Add/Edit Department form
              ================================================== -->
        <form:form commandName="user" action="/users/save.html" method="post" cssClass="form-horizontal">
            <form:hidden path="nodeId" />
            <fieldset>
                <div class="control-group">
                    <label for="username" class="control-label">Username</label>
                    <div class="controls">
                        <form:input id="username" path="username" cssClass="input-xlarge" size="30"/>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="control-group">
                    <label for="fullName" class="control-label">Full Name</label>
                    <div class="controls">
                        <form:input id="fullName" path="fullName" cssClass="input-xlarge" size="30"/>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="control-group">
                    <label for="department" class="control-label">Department</label>
                    <div class="controls">
                        <form:select id="department" path="department.nodeId" items="${departments}" itemValue="nodeId" itemLabel="name"/>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="form-actions">
                    <input type="submit" class="btn btn-primary" value="Submit">
                </div>
            </fieldset>
        </form:form>
    </div>

    <!-- Footer
   =================================================== -->
    <hr/>
    <footer class="footer">
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>User management at it's best!</p>
        <p>&copy; Cake Solutions Ltd. 2012</p>
    </footer>

</div> <!-- /container -->

<!-- Le javascript -->
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
</body>
</html>