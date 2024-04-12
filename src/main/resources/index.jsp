<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Online Book Store - Index</title>
</head>
<body>
    <h1>Welcome to the Online Book Store</h1>
    <ul>
        <c:forEach var="book" items="${books}">
            <li>
                <a href="item.jsp?id=${book.id}">${book.name}</a>
                - ${book.author}
            </li>
        </c:forEach>
    </ul>
</body>
</html>
