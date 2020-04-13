<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<ul>
    <li>
        <a href="<c:url value="/" />">
            <i class="fa fa-fw fa-home"></i>    Home
        </a>
    </li>
    <li>
        <a href="<c:url value="/categories"/>">
            <i class="fa fa-align-justify" title="Align Justify"></i>   Menu
        </a>
    </li>
    <li>
        <a href="<c:url value="/cart"/>">
            <c:set var="values" value="${sessionScope.cart.productMap.values()}"/>
            <c:forEach var="value" items="${values}">
                <c:set var="total" value="${total + value}" />
            </c:forEach>
            <i class="fa fa-shopping-cart fa-1x" data-count="${total}"></i>
        </a>
    </li>
    <li>
        <form class="nav-search" method="POST" action="<c:url value="/products/searchByName"/>">
            <input type="text" name="name" placeholder="Search any goods"/>
            <input type="submit" class="button other" value="Search"/>
        </form>
    </li>
    <li>
        <a href="<c:url value="/products/search"/>">
            <i class="fa fa-fw fa-search"></i> Custom search
        </a>
    </li>
    <sec:authorize access="!isAuthenticated()">
        <li style="float:right">
            <a class="active" href="<c:url value="/users/signup"/>">
                <i class="fa fa-fw fa-user"></i>   Sign Up
            </a>
        </li>
        <li style="float:right">
            <a  href="<c:url value="/users/signin"/>">
                <i class="fa fa-sign-in" aria-hidden="true"></i>    Sign In
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <li style="float:right">
            <a class="active" href="<c:url value="/users/account"/>">
                <i class="fa fa-fw fa-user"></i>   Your account
            </a>
        </li>
        <li style="float:right">
            <a  href="<c:url value="/users/signout"/>">
                <i class="fa fa-sign-in" aria-hidden="true"></i>    Sign out
            </a>
        </li>
    </sec:authorize>
</ul>
