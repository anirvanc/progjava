<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Users</title>
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
                <li class="active"><a href="#">Users</a></li>
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
    <div class="page-header">
        <h1>Search users <small>Search users by their full name</small><a class="btn pull-right" href="/users/add.html"><i class="icon-user"></i> Add User</a></h1>
    </div>

    <!-- Search form
         ================================================== -->
    <form class="form-horizontal" id="search" action="/users.html" method="get">
        <fieldset>
            <div class="control-group">
                <label for="q" class="control-label">Name</label>
                <div class="controls">
                    <input class="input-xlarge" id="q" name="q" placeholder="First name or Last name"  size="30" type="text">
                    <input type="submit" class="btn btn-primary" value="Submit">
                </div>
            </div>
        </fieldset>
    </form>
    <hr/>
    <div class="row">
        <c:choose>
            <c:when test="${not empty users}">
                <div class="span4">
                    <h3>${fn:length(users)} users found!</h3>
                </div>
                <br/>
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Department</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.fullName}</td>
                            <td>${user.department.name}</td>
                            <td>
                                <form class="form-inline" method="POST" action="/users/delete/${user.username}.html">
                                    <input type="hidden" name="_method" value="DELETE"/>
                                    <a class="btn" href="/users/edit/${user.id}.html"><i class="icon-edit"></i> Edit</a>
                                    <a class="btn" href="#" onclick="submitFor(this);"><i class="icon-trash"></i> Delete</a>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="span4">
                    <h3>No users found!</h3>
                </div>
            </c:otherwise>
        </c:choose>
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
<script type="text/javascript">
    function submitFor(anchorElement) {
        $(anchorElement).closest('form').submit();
    }
</script>
</body>
</html>