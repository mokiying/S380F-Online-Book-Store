<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<div>
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a href="<c:url value="/logout" />">[Logout]</a>
    </security:authorize>
    <a href="<c:url value='/book/list'/>">[Back]</a>
</div>
<h2>Edit Book ${book.id}</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="bookForm">
    <form:label path="name">Book Name</form:label><br/>
    <form:input type="text" path="name" value="${book.name}"/><br/><br/>
    <form:label path="author">Author</form:label><br/>
    <form:input type="text" path="author" value="${book.author}"/><br/><br/>
    <form:label path="price">Price</form:label><br/>
    <form:input type="number" step="0.01" path="price" value="${book.price}"/><br/><br/>
    <form:label path="description">Description</form:label><br/>
    <form:textarea path="description" rows="5" cols="30" value="${book.description}"/><br/><br/>
    <form:label path="availability">Availability</form:label><br/>
    <form:input type="number" step="1" path="availability" value="${book.availability}"/><br/><br/>
    <b>Attachments</b><br/>
    <c:choose>
        <c:when test="${!empty imageData}">
            <img style="max-width:600px;max-height:400px;" src="data:image/jpeg;base64,${imageData}" />
            <br/>
        </c:when>
        <c:otherwise><h3>No cover image available.</h3></c:otherwise>
    </c:choose>
    <input type="file" name="attachments" multiple="multiple" accept="image/png, image/gif, image/jpeg" value="${imageData}"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>