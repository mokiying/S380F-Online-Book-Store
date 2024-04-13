<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://unpkg.com/marx-css/css/marx.min.css">
    <style> body {padding:5rem;} </style>
</head>
<body>
<h1>Welcome to S380F Book Store</h1>

<button onclick="redirectTo('/fav')">Favorite</button>
<button onclick="redirectTo('/bookList')">Book List</button>
<button onclick="redirectTo('/cart')">Cart</button>
<button onclick="redirectTo('/history')">History</button>
<button onclick="redirectTo('/bookUser')">My Information</button>

<script>
    function redirectTo(url) {
        window.location.href = url;
    }
</script>
</body>
</html>