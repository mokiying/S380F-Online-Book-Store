<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<div class="container">
    <h2>Register</h2>
    <form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
        <div class="form-group">
            <label for="username">Username</label>
            <form:input type="text" path="username" id="username" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <form:input type="password" path="password" id="password" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="fullName">Full Name</label>
            <form:input type="text" path="fullName" id="fullName" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <form:input type="email" path="email" id="email" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <form:input type="text" path="address" id="address" class="form-control"/>
        </div>

        <security:authorize access="hasRole('ADMIN')">
            <div class="form-check">
                <form:checkbox path="roles" value="ROLE_USER" checked="true" id="roleUser" class="form-check-input"/>
                <label class="form-check-label" for="roleUser">ROLE_USER</label>
            </div>
            <div class="form-check">
                <form:checkbox path="roles" value="ROLE_ADMIN" id="roleAdmin" class="form-check-input"/>
                <label class="form-check-label" for="roleAdmin">ROLE_ADMIN</label>
            </div>
        </security:authorize>
        <br/><br/>
        <input type="submit" value="Submit" class="btn btn-primary"/>
    </form:form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>