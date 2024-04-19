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
    <h2>Book #${book.id}: <c:out value="${book.name}" /></h2>
    <h3>Add Comment</h3>
    <form:form method="POST" enctype="multipart/form-data" modelAttribute="commentForm">
        <div class="form-group">
            <label for="content">Content</label>
            <form:textarea path="content" rows="5" cols="30" id="content" class="form-control"></form:textarea>
        </div>
        <br/><br/>
        <input type="submit" value="Submit" class="btn btn-primary"/>
    </form:form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>