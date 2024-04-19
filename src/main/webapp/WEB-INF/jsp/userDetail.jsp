<!DOCTYPE html>
<html>
<head>
    <title>Online Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <%@ include file="navbarHead.jsp" %>

</head>
<body>
<%@ include file="navbar.jsp" %>

<h2>User - ${user.username}</h2>

<ul>
    <li><b>password:</b> <c:out value="${user.password}"/></li>
    <br/>
    <li><b>fullName:</b> <c:out value="${user.fullName}"/></li>
    <br/>
    <li><b>emailCertainly! Here's the modified code with Bootstrap 4 integration, using your provided navigation source
        and removing the `[Back]` and `[Logout]` links:

        ```html
        <!DOCTYPE html>
        <html>
        <head>
            <title>Online Book Store</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <style>
                body {
                    padding: 3.75rem;
                }
            </style>
        </head>
        <body>
        <%@ include file="navbar.jsp" %>

        <h2>User - ${user.username}</h2>

        <ul>
            <li><b>password:</b> <c:out value="${user.password}"/></li>
            <br/>
            <li><b>fullName:</b> <c:out value="${user.fullName}"/></li>
            <br/>
            <li><b>email:</b><c:out value="${user.email}"/></li>
            <br/>
            <li><b>address:</b><c:out value="${user.address}"/></li>
            <br/>
        </ul>

        <security:authorize access="hasRole('ADMIN')">
            <h2>Roles</h2>
            <div>
                <a href="<c:url value='/user/roleuser/create/${user.username}'/>">[Add Role User]</a>
                <a href="<c:url value='/user/roleadmin/create/${user.username}'/>">[Add Role Admin]</a>
            </div>
            <ul>
                <c:forEach var="r" items="${roles}">
                    <li><c:out value='${r.role}'/> <a
                            href="<c:url value='/user/role/delete/${user.username}/${r.id}'/>">[Delete]</a></li>
                </c:forEach>
            </ul>
        </security:authorize>

        <h2>Comments</h2>
        <c:choose>
            <c:when test="${fn:length(comments) > 0}">
                <table class="table">
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
                                <a href="<c:url value="/book/view/${comment.bookId}"/>">
                                    <c:out value="${comment.book.name}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${comment.content}"/>
                            </td>
                            <td>
                                <a href="<c:url value='/book/comment/delete/${comment.id}'/>" class="btn btn-danger">Delete</a>
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

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        </body>
        </html>