<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Departments</title>
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
                <li><a href="/users.html">Users</a></li>
                <li class="active"><a href="#">Departments</a></li>
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
        <h1>Search departments <small>Search departments by their name</small>
            <a class="btn pull-right" href="/departments/add.html"><i class="icon-plus-sign"></i> Add Department</a>
        </h1>
    </div>

    <!-- Search form
         ================================================== -->
    <form class="form-horizontal" id="search" action="/departments.html" method="get">
        <fieldset>
            <div class="control-group">
                <label for="q" class="control-label">Name</label>
                <div class="controls">
                    <input class="input-xlarge" id="q" name="q" placeholder="Name like"  size="30" type="text">
                    <input type="submit" class="btn btn-primary" value="Submit">
                </div>
            </div>
        </fieldset>
    </form>
    <hr/>
    <div class="row">
        <c:choose>
            <c:when test="${not empty departments}">
                <div class="span4">
                    <h3>${fn:length(departments)} department(s) found!</h3>
                </div>
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${departments}" var="department">
                        <tr>
                            <td>${department.nodeId}</td>
                            <td>${department.name}</td>
                            <td>
                                <a class="btn" href="/departments/edit/${department.nodeId}.html"><i class="icon-edit"></i> Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="span4">
                    <h3>No departments found!</h3>
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
</body>
</html>