<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <a class="navbar-brand" href="#">Online Book Store</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <security:authorize access="hasAnyRole('USER', 'ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/user/personal" />">Personal Information</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/user/cart" />">Shopping Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/user/orders'/>">Orders</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link text-success" href="<c:url value="/book/create" />">Create a Book</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-success" href="<c:url value="/user/list" />">User Management</a>
                </li>
            </security:authorize>
            <security:authorize access="!hasAnyRole('USER', 'ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/login" />">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/user/create" />">Register</a>
                </li>
            </security:authorize>
            <security:authorize access="hasAnyRole('USER', 'ADMIN')">
                <li class="nav-item">
                    <a class="nav-link text-danger" href="<c:url value="/logout" />">Logout</a>
                </li>
            </security:authorize>
        </ul>
    </div>
</nav>