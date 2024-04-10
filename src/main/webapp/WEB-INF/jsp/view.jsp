<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
</head>
<body>
<h2>Book #${book.id}: <c:out value="${book.name}"/></h2>
<b>Author - <c:out value="${book.author}"/></b><br/><br/>
<b>Price - <c:out value="${book.price}"/></b><br/><br/>
<b>Available - <c:out value="${book.available}"/></b><br/><br/>
<c:if test="${book.numberOfAttachments > 0}">
    <c:forEach items="${book.attachments}" var="attachment" varStatus="status">
        <c:if test="${not status.first}">, </c:if>
        <a href="<c:url value='/book/${book.id}/attachment/${attachment.id}' />">
            <img src="data:image/jpeg;base64,${imageData}" alt="Image" />
        </a>
    </c:forEach><br/><br/>
</c:if>
<h3>Description</h3>
<c:out value="${book.description}"/><br/><br/>
<a href="<c:url value='/book' />">Return to book list</a>
</body>
</html>