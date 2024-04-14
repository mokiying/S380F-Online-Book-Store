<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            padding: 5rem;
        }

        .toolbar {
            width: 200px;
            background-color: #f1f1f1;
            padding: 1rem;
            position: fixed;
            height: 100%;
        }

        .content {
            margin-left: 220px;
        }

        h1 {
            margin-top: 0;
        }

        .active-link {
            background-color: #e9ecef;
        }
    </style>
</head>
<body>
<div class="content">
    <h1>Welcome to S380F Book Store</h1>
</div>
<div class="toolbar">
    <ul class="list-group">
        <li class="list-group-item">
            <a onclick="loadPage('/fav')" class="nav-link">Favorite</a>
        </li>
        <li class="list-group-item">
            <a onclick="loadPage('/bookList')" class="nav-link">Book List</a>
        </li>
        <li class="list-group-item">
            <a onclick="loadPage('/cart')" class="nav-link">Cart</a>
        </li>
        <li class="list-group-item">
            <a onclick="loadPage('/history')" class="nav-link">Order History</a>
        </li>
        <li class="list-group-item">
            <a onclick="loadPage('/history')" class="nav-link">Comment History</a>
        </li>
        <li class="list-group-item">
            <a onclick="loadPage('/bookUser')" class="nav-link">My Information</a>
        </li>
    </ul>
</div>

<div class="content">
    <iframe id="pageFrame" src="" style="width: 100%; height: 100vh; border: none;"></iframe>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script>
    function loadPage(url) {
        document.getElementById('pageFrame').src = url;
    }

    var navLinks = document.getElementsByClassName('nav-link');
    for (var i = 0; i < navLinks.length; i++) {
        navLinks[i].addEventListener('click', function() {
            var current = document.getElementsByClassName('active-link');
            if (current.length > 0) {
                current[0].className = current[0].className.replace(' active-link', '');
            }
            this.className += ' active-link';
        });
    }
</script>
</body>
</html>