<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
</head>
<body>
<h2>User #${user.username}</h2>
<div>
    <a href="<c:url value='/user/list'/>">Back</a>
    <a>Update</a>
    <a href="<c:url value='/user/delete/${user.username}'/>" />Delete</a>
</div>

<ul>
    <li><b>password:</b> <c:out value="${user.password}" /></li><br/>
    <li><b>fullName:</b> <c:out value="${user.fullName}" /></li><br/>
    <li><b>email:</b><c:out value="${user.email}" /></li><br/>
    <li><b>address:</b><c:out value="${user.address}" /></li><br/>
</ul>
<h3>Comments</h3>

<c:choose>
    <c:when test="${fn:length(comments) > 0}">
        <ul>
            <c:forEach var="comment" items="${comments}">
                <li>
                    <p><b><c:out value="${comment.bookId}"/>:</b>
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