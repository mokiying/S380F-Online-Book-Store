<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <style> body {padding:5rem;} </style>
</head>

<body>
<%@ include file="navbar.jsp" %>
<h2>User</h2>
<c:choose>
    <c:when test="${fn:length(userDB) == 0}">
        <i>There are no Users in the system.</i>
    </c:when>
    <c:otherwise>
        <table class="table table-bordered mt-3">
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
                    <td><a href="<c:url value="/user/view/${entry.username}" />">
                        Details
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <security:authorize access="hasRole('ADMIN')">
            <a class="btn btn-primary mb-2" href="<c:url value="/user/create" />">Create a User</a>
        </security:authorize>
    </c:otherwise>
</c:choose>
</body>
</html>