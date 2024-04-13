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
<h2>Roles</h2>
<ul>
    <c:forEach var="r" items="${roles}">
        <li><c:out value='${r.role}'/> </li>
    </c:forEach>
</ul>
<c:choose>
    <c:when test="${fn:length(comments) > 0}">
        <table border="1">
            <tr>
                <th>Book</th>
                <th>Comment</th>
                <th>Action</th>
            </tr>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <td>
                        <a href="<c:url value="/book/view/${comment.bookId}"/>">
                            <c:out value="${comment.book.name}"/>
                        </a>
                    </td>
                    <td>
                        <c:out value="${comment.content}"/>
                    </td>
                    <td>
                        <a href="<c:url value='/book/comment/delete/${comment.id}'/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <b>No Comments</b>
    </c:otherwise>
</c:choose>
</body>
</html>