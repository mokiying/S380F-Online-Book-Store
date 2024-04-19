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
    <h2>Create a Book</h2>

    <form:form method="POST" enctype="multipart/form-data" modelAttribute="bookForm">
        <div class="form-group">
            <label for="name">Book Name</label>
            <form:input type="text" path="name" id="name" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="author">Author</label>
            <form:input type="text" path="author" id="author" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <form:input type="number" step="0.01" path="price" id="price" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <form:textarea path="description" id="description" rows="5" cols="30" class="form-control"></form:textarea>
        </div>
        <div class="form-group">
            <label for="availability">Availability</label>
            <form:input type="number" step="1" path="availability" id="availability" class="form-control"/>
        </div>
        <div class="form-group">
            <strong>Attachments</strong><br/>
            <input type="file" name="attachments" multiple="multiple" accept="image/png, image/gif, image/jpeg" class="form-control-file"/>
        </div>
        <input type="submit" value="Submit" class="btn btn-primary"/>
    </form:form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>