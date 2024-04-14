<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>User</h2>
<a href="<c:url value='/book/list'/>">[Back]</a>
<a href="<c:url value="/bookUser/create" />">[Create a User]</a><br/><br/>
<c:choose>
    <c:when test="${fn:length(userDB) == 0}">
        <i>There are no Users in the system.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>User Name</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userDB}" var="entry">
                <tr>
                    <td>${entry.username}</td>
                    <td>${entry.fullName}</td>
                    <td>${entry.email}</td>
                    <td><a href="<c:url value="/user/detail/${entry.username}" />">
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