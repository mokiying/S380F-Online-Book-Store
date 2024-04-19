<!DOCTYPE html>
<html>
<head><title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style> body {
        padding: 5rem;
    } </style>
</head>
<body>
<%@ include file="../navbar.jsp" %>

<div class="container">
    <h2>Edit Book ${book.id}</h2> <form:form method="POST" enctype="multipart/form-data" modelAttribute="bookForm"
                                             class="mt-4">
    <div class="form-group"><form:label path="name">Book Name</form:label> <form:input type="text" path="name"
                                                                                       value="${book.name}"
                                                                                       class="form-control"/></div>
    <div class="form-group"><form:label path="author">Author</form:label> <form:input type="text" path="author"
                                                                                      value="${book.author}"
                                                                                      class="form-control"/></div>
    <div class="form-group"><form:label path="price">Price</form:label> <form:input type="number" step="0.01"
                                                                                    path="price" value="${book.price}"
                                                                                    class="form-control"/></div>
    <div class="form-group"><form:label path="description">Description</form:label> <form:textarea path="description"
                                                                                                   rows="5" cols="30"
                                                                                                   value="${book.description}"
                                                                                                   class="form-control"></form:textarea></div>
    <div class="form-group"><form:label path="availability">Availability</form:label> <form:input type="number" step="1"
                                                                                                  path="availability"
                                                                                                  value="${book.availability}"
                                                                                                  class="form-control"/></div>
    <div class="form-group"><b>Attachments</b> <c:choose> <c:when test="${!empty imageData}"> <img
            style="max-width:600px;max-height:400px;" src="data:image/jpeg;base64,${imageData}"
            class="img-fluid mb-3"/> </c:when> <c:otherwise><h3>No cover image available.</h3></c:otherwise> </c:choose>
        <input type="file" name="attachments" multiple="multiple" accept="image/png, image/gif, image/jpeg"
               value="${imageData}" class="form-control-file"/></div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form:form></div>
</body>
</html>