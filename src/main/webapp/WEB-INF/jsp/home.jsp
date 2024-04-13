<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welcome to S380F Book Store</h1>

<button onclick="redirectTo('/fav')">Favorite</button>
<button onclick="redirectTo('/bookList')">Book List</button>
<button onclick="redirectTo('/cart')">Cart</button>
<button onclick="redirectTo('/history')">History</button>
<button onclick="redirectTo('/user')">My Information</button>

<script>
    function redirectTo(url) {
        window.location.href = url;
    }
</script>
</body>
</html>