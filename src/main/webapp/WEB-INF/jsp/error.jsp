<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

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