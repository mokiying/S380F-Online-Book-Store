<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding: 5rem;
        }

        .table {
            border-collapse: separate;
            border-spacing: 0;
        }
        .table tr {border: none;}
        .table td {border: none;}
        .table th {border: none;}
        .navbar {
            min-height: 80px;
        }
    </style>

</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container mt-5">
    <h2>Book</h2>
    <c:choose>
        <c:when test="${fn:length(bookDB) == 0}">
            <i>There are no Books in the system.</i>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="table">
                    <tbody>
                    <c:forEach items="${bookDB}" var="entry">
                        <tr>
                            <td colspan="4">
                                <h5>${entry.name}</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Author: ${entry.author}</p>
                            </td>
                            <td>
                                <p>Price: ${entry.price}</p>
                            </td>
                            <td>
                                <a href="<c:url value="/book/view/${entry.id}" />" class="btn btn-primary">Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>