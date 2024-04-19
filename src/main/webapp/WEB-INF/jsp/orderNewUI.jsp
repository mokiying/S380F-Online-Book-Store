<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style> body {padding:5rem;} </style>
</head>
<%@ include file="navbar.jsp" %>
<body>
<h2>Order</h2>

<c:choose>
    <c:when test="${fn:length(orders) == 0}">
        <i>There are no Orders in the system.</i>
    </c:when>
    <c:otherwise>
        <table class="table table-bordered mt-4">
            <thead>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Order Detail</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.dateTime}</td>
                    <td><a href="<c:url value="/user/orders/view/${order.id}" />">
                        Details
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
</body>
</html>