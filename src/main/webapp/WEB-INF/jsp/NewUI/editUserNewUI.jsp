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
    <h2>Edit User: ${user.username}</h2>

    <form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
        <div class="form-group">
            <label for="password">Password</label>
            <form:input type="text" path="password" id="password" class="form-control" value="${password}"/>
        </div>
        <div class="form-group">
            <label for="fullName">Full Name</label>
            <form:input type="text" path="fullName" id="fullName" class="form-control" value="${user.fullName}"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <form:input type="text" path="email" id="email" class="form-control" value="${user.email}"/>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <form:input type="text" path="address" id="address" class="form-control" value="${user.address}"/>
        </div>

        <input type="submit" value="Submit" class="btn btn-primary"/>
    </form:form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>