<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
</head>
<body>
<h2>User</h2>
<a href="<c:url value="/user/create" />">Create a User</a><br/><br/>
<c:choose>
    <c:when test="${fn:length(userDB) == 0}">
        <i>There are no Users in the system.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <caption>Users</caption>
            <thead>
            <tr>
                <th>username</th>
                <th>password</th>
                <th>fullName</th>
                <th>email</th>
                <th>address</th>
                <th>userRole</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userDB}" var="entry">
                <tr>
                    <td>${entry.username}</td>
                    <td>${entry.password}</td>
                    <td>${entry.fullName}</td>
                    <td>${entry.email}</td>
                    <td>${entry.address}</td>
                    <td>${entry.userRole}</td>
                    <td><a href="<c:url value="/user/userDetail/${entry.username}" />">
                        Details
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>