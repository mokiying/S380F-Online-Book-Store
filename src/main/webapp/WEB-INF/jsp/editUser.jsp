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
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a href="<c:url value="/logout" />">[Logout]</a>
    </security:authorize>
    <a href="<c:url value='/user/list'/>">[Back]</a>
</div>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
    <form:label path="password">Password</form:label><br/>
    <form:input type="text" path="password" value="${password}"/><br/><br/>
    <form:label path="fullName">Full Name</form:label><br/>
    <form:input type="text" path="fullName" value="${user.fullName}"/><br/><br/>
    <form:label path="email">Email</form:label><br/>
    <form:input type="text" path="email" value="${user.email}"/><br/><br/>
    <form:label path="address">Address</form:label><br/>
    <form:input type="text" path="address" value="${user.address}"/><br/><br/>
    <br/><br/>
    <security:authorize access="hasRole('ADMIN')">
    <h2>Roles</h2>
    <ul>
        <c:forEach var="r" items="${roles}">
            <li><c:out value='${r.role}'/> </li>
        </c:forEach>
    </ul>
    </security:authorize>
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>