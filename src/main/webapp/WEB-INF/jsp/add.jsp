<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Create a Book </h2>
<div>
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a href="<c:url value="/logout" />">[Logout]</a>
    </security:authorize>
    <a href="<c:url value='/book/list'/>">[Back]</a>
</div>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="bookForm">
    <form:label path="name">Book Name</form:label><br/>
    <form:input type="text" path="name"/><br/><br/>
    <form:label path="author">Author</form:label><br/>
    <form:input type="text" path="author"/><br/><br/>
    <form:label path="price">Price</form:label><br/>
    <form:input type="number" step="0.01" path="price"/><br/><br/>
    <form:label path="description">Description</form:label><br/>
    <form:textarea path="description" rows="5" cols="30"/><br/><br/>
    <form:label path="availability">Availability</form:label><br/>
    <form:input type="number" step="1" path="availability"/><br/><br/>
    <b>Attachments</b><br/>
    <input type="file" name="attachments" multiple="multiple" accept="image/png, image/gif, image/jpeg"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>