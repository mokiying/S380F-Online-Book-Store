<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Book #${book.id}: <c:out value="${book.name}" /></h2>
<div>
    <a href="<c:url value='/book/list'/>">[Back]</a>
    <a href="<c:url value='/book/edit/${book.id}'/>">[Update]</a>
    <a href="<c:url value='/book/delete/${book.id}'/>" >[Delete]</a>
</div>
<c:choose>
    <c:when test="${!empty imageData}">
        <img style="max-width:600px;max-height:400px;" src="data:image/jpeg;base64,${imageData}" />
        <br/>
    </c:when>
    <c:otherwise><h3>No cover image available.</h3></c:otherwise>
</c:choose>
<ul>
<li><b>Author:</b> <c:out value="${book.author}" /></li><br/>
<li><b>Price:</b> <c:out value="${book.price}" /></li><br/>
<li><b>Availability:</b><c:out value="${book.availability}" /></li><br/>
<li><b>Description:</b><p><c:out value="${book.description}" /></p></li><br/>
</ul>
<h3>Comments</h3>
<div>
    <a href="<c:url value='/book/comment/add/${bookId}'/>">Add Comment</a>
</div>
<c:choose>
    <c:when test="${fn:length(comments) > 0}">
        <ul>
            <c:forEach var="comment" items="${comments}">
                <li>
                    <p><b><c:out value="${comment.username}"/>:</b>
                    <c:out value="${comment.content}"/>
                    </p>
                    <a href="<c:url value='/book/comment/delete/${comment.id}'/>">[Delete]</a>
                </li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <b>No Comments</b>
    </c:otherwise>
</c:choose>
</body>
</html>