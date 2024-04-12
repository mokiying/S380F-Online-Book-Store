<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
</head>
<body>
<h2>Add Comment to #${book.id} : <c:out value="${book.name}"/></h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="commentForm">
    <form:label path="username">Username: test</form:label><br/>
    <form:input type="text" path="username" value="test" hidden="true" /><br/><br/>
    <form:label path="bookId">
        <c:out value="${book.name}"/>
    </form:label><br/>
    <form:input type="text" path="bookId" value="${book.id}" hidden="true" /><br/><br/>
    <form:label path="content">Content</form:label><br/>
    <form:textarea path="content" rows="5" cols="30"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>