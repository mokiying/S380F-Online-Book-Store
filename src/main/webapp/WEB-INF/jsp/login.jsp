<!DOCTYPE html>
<html>
<head>
    <title>Customer Support Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<c:if test="${param.error != null}">
    <p class="alert alert-danger">Login failed.</p>
</c:if>
<c:if test="${param.logout != null}">
    <p class="alert alert-success">You have logged out.</p>
</c:if>

<div class="container">
    <h2>Online Book System - Login</h2>
    <a href="<c:url value='/book/list'/>">[Back]</a>
    <form action="login" method="POST">
        <div class="mb-3">
            <label for="username" class="form-label">Username:</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me">
            <label class="form-check-label" for="remember-me">Remember me</label>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Log In</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>