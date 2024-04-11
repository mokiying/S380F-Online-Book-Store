<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
</head>
<body>
<h2>Book #${book.id}: <c:out value="${book.name}" /></h2>
<b>Author - <c:out value="${book.author}" /></b><br/><br/>
<b>Price - <c:out value="${book.price}" /></b><br/><br/>
<b>Availability - <c:out value="${book.availability}" /></b><br/><br/>

<c:if test="${book.numberOfAttachments > 0}">
    <h3>Attachments:</h3>
    <c:forEach items="${book.attachments}" var="attachment" varStatus="status">
        <a href="<c:url value='/book/${book.id}/attachment/${attachment.id}' />">
            Attachment #${status.index + 1}
        </a>
        <br/>
    </c:forEach>
    <br/>
</c:if>

<h3>Description:</h3>
<c:out value="${book.description}" /><br/><br/>

<h3>Comments:</h3>
<a href="<c:url value='/book/view/${bookId}/comment/add' />">Add new Comment</a>
<c:if test="${not empty book.comments}">
    <ul>
        <c:forEach items="${book.comments}" var="comment">
            <li>
                <b>${comment.value.username}:</b> ${comment.value.content}
            </li>
        </c:forEach>
    </ul>
</c:if>

<a href="<c:url value='/book/list' />">Return to book list</a>
</body>
</html>