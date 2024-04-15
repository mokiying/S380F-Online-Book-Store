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
    <a href="<c:url value='/book/list'/>">[Back]</a>
</div>
<c:choose>
    <c:when test="${fn:length(favItems) == 0}">
        <i>There are no Books in Favourite.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Book Id</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${favItems}" var="item">
                <tr>
                    <td>${item.bookId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>