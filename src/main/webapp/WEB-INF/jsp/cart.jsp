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
    <c:when test="${fn:length(cartItems) == 0}">
        <i>There are no Books in the Shopping cart.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Book Id</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cartItems}" var="item">
                <tr>
                    <td>${item.bookId}</td>
                    <td>${item.quantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>