<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
</head>
<body>
<h2>Book</h2>
<a href="<c:url value="/book/create" />">Create a Book</a><br/><br/>
<c:choose>
    <c:when test="${fn:length(bookDB) == 0}">
        <i>There are no Books in the system.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <caption>Books</caption>
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