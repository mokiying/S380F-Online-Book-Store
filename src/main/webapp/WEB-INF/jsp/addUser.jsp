<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Register</h2>
<div>
    <security:authorize access="hasRole('ADMIN')">
    <a href="<c:url value='/user/list'/>">[Back]</a>
    </security:authorize>
    <security:authorize access="!hasRole('ADMIN')">
        <a href="<c:url value='/book/list'/>">[Back]</a>
    </security:authorize>
</div>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
    <form:label path="username">Username</form:label><br/>
    <form:input type="text" path="username"/><br/><br/>
    <form:label path="password">Password</form:label><br/>
    <form:input type="text" path="password"/><br/><br/>
    <form:label path="fullName">Full Name</form:label><br/>
    <form:input type="text" path="fullName"/><br/><br/>
    <form:label path="email">Email</form:label><br/>
    <form:input type="text" path="email"/><br/><br/>
    <form:label path="address">Address</form:label><br/>
    <form:input type="text" path="address"/><br/><br/>

    <security:authorize access="hasRole('ADMIN')">
    <form:checkbox path="roles" value="ROLE_USER" checked="true"/>ROLE_USER<br/>
    <form:checkbox path="roles" value="ROLE_ADMIN"/>ROLE_ADMIN
    </security:authorize>
    <br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>