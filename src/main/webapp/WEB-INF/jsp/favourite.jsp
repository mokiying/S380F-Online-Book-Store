<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Shopping Cart</h2>
<div>
    <a href="<c:url value="/book/list" />">[Back]</a>
</div>
<c:choose>
    <c:when test="${fn:length(books) == 0}">
        <i>There are no Books in the Shopping cart.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Book Name</th>
                <th>Book Author</th>
                <th>Price</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="cartItem">
                <tr>
                    <c:url var="post_url"  value="/user/cart/edit/${cartItem.item.bookId}" />
                    <form:form method="POST" enctype="multipart/form-data" modelAttribute="cartForm" action="${post_url}">
                        <td><a href="<c:url value='/book/view/${cartItem.item.bookId}'/>">${cartItem['book'].name}</a></td>
                        <td>${cartItem['book'].author}</td>
                        <td>$${cartItem['book'].price}</td>
                        <td><a href="<c:url value='/user/favourtie/delete/${cartItem.item.bookId}'/>">[Delete]</a></td>
                    </form:form>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>