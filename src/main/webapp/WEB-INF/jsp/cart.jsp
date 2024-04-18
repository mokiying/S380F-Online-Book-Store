<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Shopping Cart</h2>
<security:authorize access="hasAnyRole('USER', 'ADMIN')">
    <a href="<c:url value="/logout" />">[Logout]</a>
</security:authorize>
<c:choose>
    <c:when test="${fn:length(cartItems) == 0}">
        <div>
            <a href="<c:url value="/book/list" />">[Back]</a>
        </div>
        <i>There are no Books in the Shopping cart.</i>
    </c:when>
    <c:otherwise>
        <div>
            <a href="<c:url value="/book/list" />">[Back]</a>
            <a href="<c:url value="/user/cart/checkout" />">[Check Out]</a>
        </div>
        <table border="1">
            <thead>
            <tr>
                <th>Book Name</th>
                <th>Book Author</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Save</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cartItems}" var="cartItem">
                <tr>
                    <c:url var="post_url"  value="/user/cart/edit/${cartItem.item.bookId}" />
                    <form:form method="POST" enctype="multipart/form-data" modelAttribute="cartForm" action="${post_url}">
                    <td><a href="<c:url value='/book/view/${cartItem.item.bookId}'/>">${cartItem['book'].name}</a></td>
                    <td>${cartItem['book'].author}</td>
                    <td>$${cartItem['book'].price}</td>
                    <td><form:input type="number" step="1" path="quantity" value="${cartItem['item'].quantity}"/></td>
                    <td><input type="submit" value="Save"/></td>
                    <td><a href="<c:url value='/user/cart/delete/${cartItem.item.bookId}'/>">[Delete]</a></td>
                    </form:form>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>