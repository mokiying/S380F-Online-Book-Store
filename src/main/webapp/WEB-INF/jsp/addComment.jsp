<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h2>Book #${book.id}: <c:out value="${book.name}" /></h2>
<div>
    <a href="<c:url value='/book/view/${bookId}'/>">Back</a>
</div>
<h3>Add Comment</h3>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="commentForm">
    <form:label path="content">Content</form:label><br/>
    <form:textarea path="content" rows="5" cols="30"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>