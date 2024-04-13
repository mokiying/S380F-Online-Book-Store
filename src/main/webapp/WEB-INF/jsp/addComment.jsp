<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
</head>
<body>
<h2>Book #${book.id}: <c:out value="${book.name}" /></h2>
<div>
    <a href="<c:url value='/book/view/${bookId}'/>">Back</a>
</div>
<h3>Add Comment</h3>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="commentForm">
    <form:input type="text" path="bookId" value="${book.id}" hidden="true" /><br/>
    <form:label path="content">Content</form:label><br/>
    <form:textarea path="content" rows="5" cols="30"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>