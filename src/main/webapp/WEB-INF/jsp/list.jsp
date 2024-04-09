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
        <c:forEach items="${bookDB}" var="entry">
            Book  ${entry.key}:
            <a href="<c:url value="/book/view/${entry.key}" />">
                <c:out value="${entry.value.name}"/></a>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>