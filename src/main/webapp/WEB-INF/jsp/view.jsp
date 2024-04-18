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
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a href="<c:url value="/logout" />">[Logout]</a>
    </security:authorize>
    <a href="<c:url value='/book/list'/>">[Back]</a>
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
    <a href="<c:url value='/user/cart/add/${book.id}'/>">[Add to Shopping Cart]</a>
    </security:authorize>
    <security:authorize access="hasRole('ADMIN')">
    <a href="<c:url value='/book/edit/${book.id}'/>">[Update]</a>
    <a href="<c:url value='/book/delete/${book.id}'/>" >[Delete]</a>
    </security:authorize>
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
<security:authorize access="hasRole('ADMIN')">
    <li><b>Availability:</b><c:out value="${book.availability}" /></li><br/>
</security:authorize>
<li><b>Description:</b><p><c:out value="${book.description}" /></p></li><br/>
</ul>
<h3>Comments</h3>
<div>
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
    <a href="<c:url value='/book/comment/add/${bookId}'/>">[Add Comment]</a>
    </security:authorize>
</div>
<c:choose>
    <c:when test="${fn:length(comments) > 0}">
        <ul>
            <c:forEach var="comment" items="${comments}">
                <li>
                    <p><b><c:out value="${comment.username}"/>:</b>
                    <c:out value="${comment.content}"/>
                    </p>
                    <security:authorize access="hasRole('ADMIN')">
                    <a href="<c:url value='/book/comment/delete/${comment.id}'/>">[Delete]</a>
                    </security:authorize>
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