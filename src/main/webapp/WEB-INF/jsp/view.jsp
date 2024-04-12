<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
</head>
<body>
<h2>Book #${book.id}: <c:out value="${book.name}" /></h2>
<c:choose>
    <c:when test="${!empty imageData}">
        <a href="<c:url value='/book/${book.id}/attachment/${attachment.id}' />">
            <img style="max-width:600px;" src="data:image/jpeg;base64,${imageData}" />
        </a><br/>
    </c:when>
    <c:otherwise><h3>No cover image available.</h3></c:otherwise>
</c:choose>
<ul>
<li><b>Author:</b> <c:out value="${book.author}" /></li><br/>
<li><b>Price:</b> <c:out value="${book.price}" /></li><br/>
<li><b>Availability:</b><c:out value="${book.availability}" /></li><br/>
<li><b>Description:</b><p><c:out value="${book.description}" /></p></li><br/>
</ul>
<a href="<c:url value='/book/list' />">Return to book list</a>
</body>
</html>