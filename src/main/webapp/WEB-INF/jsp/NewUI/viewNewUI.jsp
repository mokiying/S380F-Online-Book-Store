<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<h2>Book #${book.id}: <c:out value="${book.name}" /></h2>
<c:choose>
    <c:when test="${!empty imageData}">
        <img class="img-fluid" style="max-width:600px;max-height:400px;" src="data:image/jpeg;base64,${imageData}" />
        <br/>
    </c:when>
    <c:otherwise><h3>No cover image available.</h3></c:otherwise>
</c:choose>
<table class="table table-bordered mt-3">
    <tbody>
    <tr>
        <td><b>Author:</b></td>
        <td><c:out value="${book.author}" /></td>
    </tr>
    <tr>
        <td><b>Price:</b></td>
        <td><c:out value="${book.price}" /></td>
    </tr>
    <security:authorize access="hasRole('ADMIN')">
        <tr>
            <td><b>Availability:</b></td>
            <td><c:out value="${book.availability}" /></td>
        </tr>
    </security:authorize>
    <tr>
        <td><b>Description:</b></td>
        <td><p><c:out value="${book.description}" /></p></td>
    </tr>
    </tbody>
</table>
<div class="d-flex justify-content-between">
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a class="btn btn-primary" href="<c:url value='/user/cart/add/${book.id}'/>">Add to Shopping Cart</a>
    </security:authorize>
    <security:authorize access="hasRole('ADMIN')">
        <div>
            <a class="btn btn-primary" href="<c:url value='/book/edit/${book.id}'/>">Update</a>
            <a class="btn btn-danger ml-2" href="<c:url value='/book/delete/${book.id}'/>">Delete</a>
        </div>
    </security:authorize>
</div>
<h3>Comments</h3>

<c:choose>
    <c:when test="${fn:length(comments) > 0}">
        <table class="table table-bordered mt-3">
            <tbody>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <td>
                        <b><c:out value="${comment.username}" />:</b>
                        <br>
                        <span><c:out value="${comment.content}" /></span>
                    </td>

                    <td>
                        <security:authorize access="hasRole('ADMIN')">
                            <a class="btn btn-danger" href="<c:url value='/book/comment/delete/${comment.id}'/>">Delete</a>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <b>No Comments</b>
    </c:otherwise>
</c:choose>
<div>
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a class="btn btn-primary" href="<c:url value='/book/comment/add/${bookId}'/>">Add Comment</a>
    </security:authorize>
</div>
</body>
</html>