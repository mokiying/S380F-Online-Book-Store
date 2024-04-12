<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Online Book Store - ${book.name}</title>
</head>
<body>
    <h1>${book.name}</h1>
    <p>Author: ${book.author}</p>
    <p>Price: $${book.price}</p>
    <p>Description: ${book.description}</p>
    <p>Availability: ${book.availability ? 'In Stock' : 'Out of Stock'}</p>
    <img src="${book.coverPhoto}" alt="Book Cover">
    <h2>Comments</h2>
    <ul>
        <c:forEach var="comment" items="${comments}">
            <li>${comment.user}: ${comment.content}</li>
        </c:forEach>
    </ul>
</body>
</html>
