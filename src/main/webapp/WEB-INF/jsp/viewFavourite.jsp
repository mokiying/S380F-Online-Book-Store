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
    <c:when test="${fn:length(books) == 0}">
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
            <c:forEach items="${book}" var="books">
                <tr>
                    <td><a href="<c:url value='${book.bookId}}'/>">${book.book.name}</a></td>
                    <td>${book.author}</td>
                    <td>$${book.price}</td
                    <td><a>[Delete]</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>