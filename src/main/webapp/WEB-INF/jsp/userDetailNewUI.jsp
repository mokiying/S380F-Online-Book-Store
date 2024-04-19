<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <style> body {padding: 5rem;} </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h2>User - ${user.username}</h2>


    <table class="table table-bordered mt-4">
        <tbody>
        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>
        <tr>
            <td><b>password:</b></td>
            <td><c:out value="${user.password}" /></td>
        </tr>
        <tr>
            <td><b>fullName:</b></td>
            <td><c:out value="${user.fullName}" /></td>
        </tr>
        <tr>
            <td><b>email:</b></td>
            <td><c:out value="${user.email}" /></td>
        </tr>
        <tr>
            <td><b>address:</b></td>
            <td><c:out value="${user.address}" /></td>
        </tr>
        </tbody>
    </table>
    <div class="mt-3">
        <ul class="nav">
            <security:authorize access="hasAnyRole('USER', 'ADMIN')">
                <li class="nav-item">
                    <a class="nav-link btn btn-primary" href="<c:url value='/user/edit/${user.username}'/>">Update</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link btn btn-danger" href="<c:url value='/user/delete/${user.username}'/>">Delete</a>
                </li>
            </security:authorize>
        </ul>
    </div>
    <security:authorize access="hasRole('ADMIN')">
        <h2>Roles</h2>

        <table class="table table-bordered mt-3">
            <thead>
            <tr>
                <th>Role</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="r" items="${roles}">
                <tr>
                    <td><c:out value="${r.role}" /></td>
                    <td>
                        <a class="text-danger ml-2" href="<c:url value='/user/role/delete/${user.username}/${r.id}'/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div>
            <a class="btn btn-primary mb-2" href="<c:url value='/user/roleuser/create/${user.username}'/>">Add Role User</a>
            <a class="btn btn-primary mb-2" href="<c:url value='/user/roleadmin/create/${user.username}'/>">Add Role Admin</a>
        </div>
    </security:authorize>


    <h2>Comments</h2>
    <c:choose>
        <c:when test="${fn:length(comments) > 0}">
            <table class="table table-bordered mt-3">
                <thead>
                <tr>
                    <th>Book</th>
                    <th>Comment</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="comment" items="${comments}">
                    <tr>
                        <td>
                            <a href="<c:url value='/book/view/${comment.bookId}'/>">
                                <c:out value="${comment.book.name}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${comment.content}"/>
                        </td>
                        <td>
                            <a class="text-danger ml-2" href="<c:url value='/book/comment/delete/${comment.id}'/>">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <b>No Comments</b>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>