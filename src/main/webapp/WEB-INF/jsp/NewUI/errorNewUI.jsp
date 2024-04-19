<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<%@ include file="../navbar.jsp" %>


<h2>Error page</h2>
<c:choose>
    <c:when test="${empty message}">
        <p>Something went wrong.</p>
        <ul>
            <li>Status Code: ${status}</li>
            <li>Exception: ${exception}</li>
        </ul>
    </c:when>
    <c:otherwise>
        <p>${message}</p>
    </c:otherwise>
</c:choose>
<a href="<c:url value="/book/list" />">Return to Home page</a>
</body>
</html>