<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="grid-content">
    <div class="sidebar">
        <ul>
            <li>
                <a class="active" href="#">
                    Actions
                </a>
            </li>
            <sec:authorize access="hasRole('EMPLOYEE')">
                <li>
                    <a href="<c:url value="/orders/all"/>">
                        Show all orders
                    </a>
                </li>
            <li>
                <a href="<c:url value="/orders/status/NOTSHIPPED"/>">
                    Show all not shipped orders
                </a>
            </li>
            <li>
                <a href="<c:url value="/orders/status/SHIPPED"/>">
                    Show all not shipped orders
                </a>
            </li>
            <li>
                <a href="<c:url value="/orders/status/DELIVERED"/>">
                    Show all delivered orders
                </a>
            </li>
            <li>
                <form method="POST" class="nav-search" action="<c:url value="/orders/search"/>">
                    <input type="text" name="parameter" placeholder="Search orders for..."/>
                    <input type="radio"  name="searchBy" value="id" checked>Order id
                    <input type="radio"  name="searchBy" value="login">Login
                    <input type="submit" class="button other">
                </form>
            </li>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
                    <li>
                        <a href="<c:url value="/orders/myorders"/>">
                            Show all orders
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/orders/myorders/NOTSHIPPED"/>">
                            Show all not shipped orders
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/orders/myorders/SHIPPED"/>">
                            Show all not shipped orders
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/orders/myorders/DELIVERED"/>">
                            Show all delivered orders
                        </a>
                    </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="content">
       <br/>
        <c:if test="${!empty orderList}">
        <table>
            <tr>
                <th></th>
                <th>Order number</th>
                <th>Order status</th>
                <th>Payment status</th>
                <th>Payment type</th>
                <th>Delivery type</th>
                <sec:authorize access="hasRole('EMPLOYEE')">
                    <th>User</th>
                </sec:authorize>
            </tr>
            <c:forEach var="order" items="${orderList}" varStatus="i">
                <tr>
                    <td>
                            ${i.index+1}.
                    </td>
                    <td>
                        <a href="<c:url value="/orders/${order.id}"/>">
                                ${order.id}
                        </a>
                    </td>
                    <td>
                            ${order.orderStatus.code}
                    </td>
                    <td>
                            ${order.paymentStatus.code}
                    </td>
                    <td>
                            ${order.paymentType.code}
                    </td>
                    <td>
                            ${order.deliveryType.code}
                    </td>
                    <sec:authorize access="hasRole('EMPLOYEE')">
                        <td>
                            <a href="<c:url value="/orders/user/${order.user.id}"/>">
                                ${order.user.firstName} ${order.user.lastName}
                            </a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    </div>
</div>

