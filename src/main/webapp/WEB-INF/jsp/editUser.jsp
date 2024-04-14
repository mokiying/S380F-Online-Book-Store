<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Edit User: ${user.username}</h2>
<div>
    <a href="<c:url value='/user/list'/>">[Back]</a>
</div>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
    <form:label path="password">Password</form:label><br/>
    <form:input type="text" path="password" value="${user.password}"/><br/><br/>
    <form:label path="fullName">Full Name</form:label><br/>
    <form:input type="text" path="fullName" value="${user.fullName}"/><br/><br/>
    <form:label path="email">Email</form:label><br/>
    <form:input type="text" path="email" value="${user.email}"/><br/><br/>
    <form:label path="address">Address</form:label><br/>
    <form:input type="text" path="address" value="${user.address}"/><br/><br/>
    <c:forEach var="r" items="${roles}">
        <c:choose>
            <c:when test="${r.role == 'ROLE_USER'}" >
                <form:checkbox path="roles" value="ROLE_USER" checked="true" />ROLE_USER
            </c:when>
            <c:when test="${r.role != 'ROLE_USER'}" >
                <form:checkbox path="roles" value="ROLE_USER" />ROLE_USER
            </c:when>
        </c:choose>
        <br/>
        <c:choose>
            <c:when test="${r.role == 'ROLE_ADMIN'}" >
                <form:checkbox path="roles" value="ROLE_ADMIN" checked="true" />ROLE_ADMIN
            </c:when>
            <c:when test="${r.role != 'ROLE_ADMIN'}" >
                <form:checkbox path="roles" value="ROLE_ADMIN" />ROLE_ADMIN
            </c:when>
        </c:choose>
    </c:forEach>
    <br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>