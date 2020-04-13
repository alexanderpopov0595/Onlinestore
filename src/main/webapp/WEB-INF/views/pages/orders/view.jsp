<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-content">
    <div class="sidebar">
        <ul>
            <li>
                <a class="active" href="#">
                    Actions
                </a>
            </li>
            <c:if test="${order.orderStatus!='DELIVERED'}">
                <li>
                    <a href="<c:url value="/orders/${order.id}/update"/>">
                        Update order
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/orders/${order.id}/delete"/>">
                        Delete order
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="content">
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Order')" id="defaultOpen">Order</button>
            <button class="tablinks" onclick="openTab(event, 'Order details')">Order details</button>
        </div>
        <div class="tabcontent" id="Order">
            <table style="width:100%">
                <tr>
                    <td rowspan="7">
                        <img  width="100" src="<c:url value="/resources/img/${order.orderStatus}.jpg" />"/>
                    </td>
                    <td>First name</td>
                    <td>${order.user.firstName}</td>
                </tr>
                <tr>

                    <td>Last name</td>
                    <td>${order.user.lastName}</td>
                </tr>
                <tr>

                    <td>Address</td>
                    <td>${order.address.country}, ${order.address.city}, ${order.address.zipcode}, ${order.address.street}, ${order.address.building}, ${order.address.apartment}</td>
                </tr>
                <tr>

                    <td>Delivery type</td>
                    <td>${order.deliveryType.code}</td>
                </tr>
                <tr>

                    <td>Payment type</td>
                    <td>${order.paymentType.code}</td>
                </tr>
                <tr>

                    <td>Order status</td>
                    <td>${order.orderStatus.code}</td>
                </tr>
                <tr>

                    <td>Payment status</td>
                    <td>${order.paymentStatus.code}</td>
                </tr>
            </table>
        </div>
        <div class="tabcontent" id="Order details">
            <table style="width:100%">
                <tr>
                    <th></th>
                    <th>Product image</th>
                    <th>Product name</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                <c:forEach var="orderDetails" items="${order.orderDetailsList}" varStatus="i">
                    <tr>
                        <td>${i.index+1}</td>
                        <td>
                            <img src="${path}/images/products/${orderDetails.product.id}.jpg" width="100"  onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                        </td>
                        <td>
                            <a href="<c:url value="/products/${orderDetails.product.id}"/>">
                                <c:url value="${orderDetails.product.name}"/>
                            </a>
                        </td>
                        <td>
                            <c:url value="${orderDetails.quantity}"/>
                        </td>
                        <td>
                            <c:url value="${orderDetails.product.price*orderDetails.quantity}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<script>
    document.getElementById("defaultOpen").click();
</script>
























