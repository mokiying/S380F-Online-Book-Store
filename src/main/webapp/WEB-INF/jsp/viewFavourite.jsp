<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2> ${username} Favourite /></h2>
<div>
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a href="<c:url value="/logout" />">[Logout]</a>
    </security:authorize>
    <a href="<c:url value='/book/list'/>">[Back]</a>
</div>
<c:choose>
    <c:when test="${fn:length(favItems) == 0}">
        <i>There are no Books in your favourite.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Book Name</th>
                <th>Book Author</th>
                <th>Price</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${favItems}" var="favItem">
                <tr>
                    <td><a href="<c:url value='${favItem.item.bookId}}'/>">${favItem['book'].name}</a></td>
                    <td>${favItem['book'].author}</td>
                    <td>$${favItem['book'].price}</td
                    <td><a>[Delete]</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>