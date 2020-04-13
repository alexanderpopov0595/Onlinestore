<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"	prefix="sec"%>
<div class="grid-form">
<form:form method="POST" class="form" modelAttribute="order" onsubmit="setValues()">
    <div class="form-header">
        <h3>Update order</h3>
     </div>
     <input type="hidden" name="user.id" value="${order.user.id}" />
     <input type="hidden" name="id" value="${order.id}" />
     <div class="form-element">
        <label>Address:</label>
        <sec:authorize access="hasRole('USER')">
            <c:choose>
                <c:when test="${order.orderStatus=='NOTSHIPPED'}">
                    <input class="form-input" type="text" id="address.id" value="${order.address.country} ${order.address.city} ${order.address.zipcode} ${order.address.street} ${order.address.building} ${order.address.apartment}"	list="addresses" />
                 </c:when>
                <c:otherwise>
                    <input class="form-input" type="text" id="address.id" value="${order.address.country} ${order.address.city} ${order.address.zipCode} ${order.address.street} ${order.address.building} ${order.address.apartment}"	readonly />
                </c:otherwise>
            </c:choose>
        </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')">
            <input class="form-input" type="text" id="address.id" value="${order.address.country} ${order.address.city} ${order.address.zipcode} ${order.address.street} ${order.address.building} ${order.address.apartment}"	readonly />
        </sec:authorize>
        <datalist id="addresses">
            <c:forEach var="address" items="${order.user.addressList}" varStatus="i">
                <option id="${address.id}"
                      value="<c:out value="${address.country} ${address.city} ${address.zipcode} ${address.street} ${address.building} ${address.apartment}"/>">Address
                        <c:out value="${i.index+1}" />
                </option>
            </c:forEach>
        </datalist>
     </div>
     <div class="form-element">
        <label>Payment type:</label>
        <input class="form-input" type="text" id="paymentType"	value="${order.paymentType.code}" readonly />
        <datalist id="paymentTypes">
            <c:forEach var="paymentType" items="${paymentTypes}">
                <option id="${paymentType}" value="${paymentType.code}" />
            </c:forEach>
        </datalist>
     </div>
    <div class="form-element">
        <label>Delivery type:</label>
        <sec:authorize access="hasRole('USER')">
            <c:choose>
                <c:when test="${order.orderStatus=='NOTSHIPPED'}">
                    <input class="form-input" type="text" id="deliveryType" value="${order.deliveryType.code}" list="deliveryTypes" />
                </c:when>
                <c:otherwise>
                    <input class="form-input" type="text" id="deliveryType" value="${order.deliveryType.code}" readonly />
                </c:otherwise>
            </c:choose>
        </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')">
            <input class="form-input" type="text" id="deliveryType" value="${order.deliveryType.code}" readonly />
        </sec:authorize>
        <datalist id="deliveryTypes">
            <c:forEach var="deliveryType" items="${deliveryTypes}">
                <option id="${deliveryType}" value="${deliveryType.code}" />
            </c:forEach>
        </datalist>
    </div>
    <div class="form-element">
        <label>Payment status:</label>
        <sec:authorize access="hasRole('USER')">
            <input class="form-input" type="text" id="paymentStatus" value="${order.paymentStatus.code}" readonly />
        </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')">
            <c:choose>
                <c:when test="${order.paymentStatus=='NOTPAID'}">
                    <input class="form-input" type="text" id="paymentStatus" value="${order.paymentStatus.code}" list="paymentStatuses" />
                </c:when>
                <c:otherwise>
                    <input class="form-input" type="text" id="paymentStatus" value="${order.paymentStatus.code}" readonly />
                </c:otherwise>
            </c:choose>
        </sec:authorize>
        <datalist id="paymentStatuses">
            <c:forEach var="paymentStatus" items="${paymentStatuses}">
                <option id="${paymentStatus}" value="${paymentStatus.code}" />
            </c:forEach>
        </datalist>
    </div>
    <div class="form-element">
        <label>Order status:</label>
        <sec:authorize access="hasRole('USER')">
            <input class="form-input" type="text" id="orderStatus" value="${order.orderStatus.code}" readonly />
        </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')">
            <c:choose>
                <c:when test="${order.orderStatus!='DELIVERED'}">
                    <input  class="form-input" type="text" id="orderStatus"	value="${order.orderStatus.code}" list="orderStatuses" />
                </c:when>
                <c:otherwise>
                    <input class="form-input" type="text" id="orderStatus"	value="${order.orderStatus.code}" readonly />
                </c:otherwise>
            </c:choose>
        </sec:authorize>
        <datalist id="orderStatuses">
            <c:forEach var="orderStatus" items="${orderStatuses}">
                <option id="${orderStatus}" value="${orderStatus.code}" />
            </c:forEach>
        </datalist>
    </div>
    <br/>
    <table style="width: 100%">
        <tr>
            <th></th>
            <th>Product image</th>
            <th>Product name</th>
            <th>Quantity</th>
            <th>Total</th>
        </tr>
        <c:forEach var="orderDetails" items="${order.orderDetailsList}"	varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>
                    <img src="${path}/images/products/${product.key.id}.jpg" width="50" border="0" align="left" onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                </td>
                <td>
                    <input type="hidden" name="orderDetailsList[${i.index}].id" value="${orderDetails.id}"/>
                    <input type="hidden" name="orderDetailsList[${i.index}].product.id" value="${orderDetails.product.id}"/>
                    <input type="hidden" name="orderDetailsList[${i.index}].quantity" value="${orderDetails.quantity}"/>
                    <a href="<c:url value="/products/${orderDetails.product.id}"/>">
                        <c:out value="${orderDetails.product.name}"/>
                    </a>
                </td>
                <td>
                    <c:out value="${orderDetails.quantity}"/>
                <td>
                    <c:out value="${orderDetails.quantity*orderDetails.product.price}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>

    <sec:authorize access="hasRole('USER')">
        <c:if test="${order.orderStatus=='NOTSHIPPED' }">
            <input class="form-button" type="submit" id="submit" value="Update order" />
        </c:if>
    </sec:authorize>
    <sec:authorize access="hasRole('EMPLOYEE')">
        <c:if test="${order.orderStatus!='DELIVERED'}">
            <input class="form-button"  type="submit" id="submit" value="Update order" />
        </c:if>
    </sec:authorize>
    <c:if test="${order.orderStatus!='DELIVERED'}">
        <a href="<c:url value="/orders/${order.id}/delete"/>">
            <input type="button" class="form-button delete" value="Delete order">
        </a>
    </c:if>
</form:form>
</div>