<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<%@ include file="../navbar.jsp" %>

<div class="container">

    <h2>Order - ${orderId}</h2>

    <c:choose>
        <c:when test="${fn:length(orderItems) == 0}">
            <i>There are no Books in the Shopping cart.</i>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered mt-4">
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
                        <td><a href="<c:url value='/book/view/${orderItem.item.bookId}'/>">${orderItem['book'].name}</a>
                        </td>
                        <td>${orderItem['book'].author}</td>
                        <td>$${orderItem['book'].price}</td>
                        <td>${orderItem['item'].quantity}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>