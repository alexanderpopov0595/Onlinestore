<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="grid-content">
    <div class="sidebar">
        <ul>
            <li>
                <a class="active" href="#">
                    Actions
                </a>
            </li>
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
        </ul>
    </div>
    <div class="content">
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'All')" id="defaultOpen">All orders</button>
            <button class="tablinks" onclick="openTab(event, 'NOTSHIPPED')" >Not shipped orders</button>
            <button class="tablinks" onclick="openTab(event, 'SHIPPED')" >Shipped orders</button>
            <button class="tablinks" onclick="openTab(event, 'DELIVERED')" >Delivered orders</button>
        </div>
        <div class="tabcontent" id="All">
            <c:if test="${!empty orderList}">
                <table>
                    <tr>
                        <th></th>
                        <th>Order number</th>
                        <th>Order status</th>
                        <th>Payment status</th>
                        <th>Payment type</th>
                        <th>Delivery type</th>
                        <th>User name</th>
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
                            <td>
                                <a href="<c:url value="/orders/user/${order.user.id}"/>">
                                        ${order.user.firstName} ${order.user.lastName}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <div class="tabcontent" id="NOTSHIPPED">
            <table>
                <tr>
                    <th></th>
                    <th>Order number</th>
                    <th>Order status</th>
                    <th>Payment status</th>
                    <th>Payment type</th>
                    <th>Delivery type</th>
                    <th>User name</th>
                </tr>
                <c:forEach var="order" items="${orderList}" varStatus="i">
                    <c:if test="${order.orderStatus=='NOTSHIPPED'}">
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
                            <td>
                                <a href="<c:url value="/orders/user/${order.user.id}"/>">
                                        ${order.user.firstName} ${order.user.lastName}
                                </a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <div class="tabcontent" id="SHIPPED">
            <table>
                <tr>
                    <th></th>
                    <th>Order number</th>
                    <th>Order status</th>
                    <th>Payment status</th>
                    <th>Payment type</th>
                    <th>Delivery type</th>
                    <th>User name</th>
                </tr>
                <c:forEach var="order" items="${orderList}" varStatus="i">
                    <c:if test="${order.orderStatus=='SHIPPED'}">
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
                            <td>
                                <a href="<c:url value="/orders/user/${order.user.id}"/>">
                                        ${order.user.firstName} ${order.user.lastName}
                                </a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <div class="tabcontent" id="DELIVERED">
            <table>
                <tr>
                    <th></th>
                    <th>Order number</th>
                    <th>Order status</th>
                    <th>Payment status</th>
                    <th>Payment type</th>
                    <th>Delivery type</th>
                    <th>User name</th>
                </tr>
                <c:forEach var="order" items="${orderList}" varStatus="i">
                    <c:if test="${order.orderStatus=='DELIVERED'}">
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
                            <td>
                                <a href="<c:url value="/orders/user/${order.user.id}"/>">
                                        ${order.user.firstName} ${order.user.lastName}
                                </a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<script>
    document.getElementById("defaultOpen").click();
</script>

