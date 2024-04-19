<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <%@ include file="../navbarHead.jsp" %>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<div class="container mt-5">
    <h2>Book</h2>
    <c:choose>
        <c:when test="${fn:length(bookDB) == 0}">
            <i>There are no Books in the system.</i>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="table table-bordered mt-4">
                    <tbody>
                    <c:forEach items="${bookDB}" var="entry">
                        <tr>
                            <td colspan="4">
                                <h5><a href="<c:url value='/book/view/${entry.id}' />">${entry.name}</a></h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Author: ${entry.author}</p>
                            </td>
                            <td>
                                <p>Price: ${entry.price}</p>
                            </td>
                            <security:authorize access="hasAnyRole('USER', 'ADMIN')">
                            <td>
                                <a href="<c:url value='/user/cart/add/${entry.id}' />" class="btn btn-primary">Add to Cart</a>
                            </td>
                            </security:authorize>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>