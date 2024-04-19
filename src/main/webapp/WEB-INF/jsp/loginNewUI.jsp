<!DOCTYPE html>
<html>
<head>
    <title>Customer Support Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding: 5rem;
            height: 100vh;
        }</style>
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
    <div class="row">
        <div class="col-md-6">
            <form action="login" method="POST">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me">
                    <label class="form-check-label" for="remember-me">Remember me</label>
                    <button type="submit" class="btn btn-primary">Log In</button>
                    <a href="/CSApp/login_by_guest" class="btn btn-secondary ml-2">See the book list</a>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>