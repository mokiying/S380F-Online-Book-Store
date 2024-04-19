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
                <label for="username">Username:</label><br/>
                <input type="text" id="username" name="username"/><br/><br/>
                <label for="password">Password:</label><br/>
                <input type="password" id="password" name="password"/><br/><br/>
                <input type="checkbox" id="remember-me" name="remember-me"/>
                <label for="remember-me">Remember me</label><br/><br/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Log In"/>
                <a href="/CSApp/login_by_guest" class="btn btn-secondary ml-2">See the book list</a>
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