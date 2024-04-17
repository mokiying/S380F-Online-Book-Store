<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Order - ${orderId}</h2>
<div>
    <a href="<c:url value="/book/list" />">[Back]</a>
</div>
<c:choose>
    <c:when test="${fn:length(orderItems) == 0}">
        <i>There are no Books in the Shopping cart.</i>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Book Name</th>
                <th>Book Author</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderItems}" var="orderItem">
                <tr>
                    <td><a href="<c:url value='/book/view/${orderItem.item.bookId}'/>">${orderItem['book'].name}</a></td>
                    <td>${orderItem['book'].author}</td>
                    <td>$${orderItem['book'].price}</td>
                    <td>${orderItem['item'].quantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>