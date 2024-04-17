<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Book</h2>
<div>
    <c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Log out" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a href="<c:url value="/logout" />">[Logout]</a>
    <a href="<c:url value="/user/personal" />">[Personal Information]</a>
    <a href="<c:url value="/user/cart" />">[Shopping Cart]</a>
        <a href="<c:url value='/user/orders'/>">[Orders]</a>
    </security:authorize>
    <security:authorize access="hasRole('ADMIN')">
    <a href="<c:url value="/book/create" />">[Create a Book]</a>
    <a href="<c:url value="/user/list" />">[User Management]</a>
    </security:authorize>
    <security:authorize access="!hasAnyRole('USER', 'ADMIN')">
        <a href="<c:url value="/login" />">[login]</a>
        <a href="<c:url value="/user/create" />">[Register]</a>
    </security:authorize>
</div>
<c:choose>
    <c:when test="${fn:length(bookDB) == 0}">
        <i>There are no Books in the system.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
                <tr>
                    <th>Book Name</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bookDB}" var="entry">
                    <tr>
                        <td>${entry.name}</td>
                        <td>${entry.author}</td>
                        <td>${entry.price}</td>
                        <td><a href="<c:url value="/book/view/${entry.id}" />">
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