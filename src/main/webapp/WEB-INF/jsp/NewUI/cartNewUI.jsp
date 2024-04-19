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
    <h2>Shopping Cart</h2>
    <c:choose>
        <c:when test="${fn:length(cartItems) == 0}">
            <i>There are no Books in the Shopping cart.</i>
        </c:when>
        <c:otherwise>

            <table class="table table-bordered">
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
                        <c:url var="post_url" value="/user/cart/edit/${cartItem.item.bookId}" />
                        <form:form method="POST" enctype="multipart/form-data" modelAttribute="cartForm" action="${post_url}">
                            <td><a href="<c:url value='/book/view/${cartItem.item.bookId}'/>">${cartItem['book'].name}</a></td>
                            <td>${cartItem['book'].author}</td>
                            <td>$${cartItem['book'].price}</td>
                            <td><form:input type="number" step="1" path="quantity" value="${cartItem['item'].quantity}" class="form-control"/></td>
                            <td><input type="submit" value="Save" class="btn btn-primary"/></td>
                            <td><a class="btn btn-danger" href="<c:url value='/user/cart/delete/${cartItem.item.bookId}'/>">[Delete]</a></td>
                        </form:form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div>
                <a class="btn btn-primary" href="<c:url value="/user/cart/checkout" />">Check Out</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>