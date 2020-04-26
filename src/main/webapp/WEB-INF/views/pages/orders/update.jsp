<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"	prefix="sec"%>
<div class="grid-form">
<form:form method="POST" class="form" modelAttribute="order" onsubmit="checkSelected()">
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
                    <select id="addressList" class="form-input" name="address.id" required>
                        <c:forEach var="address" items="${order.user.addressList}" varStatus="i">
                            <c:if test="${address.id==order.address.id}">
                                <option selected id="${address.id}"	value="${address.id}">Address <c:out value="${i.index+1}: ${address.country} ${address.city} ${address.zipcode} ${address.street} ${address.building} ${address.apartment}" /></option>
                            </c:if>
                            <c:if test="${address.id!=order.address.id}">
                                <option  id="${address.id}"	value="${address.id}">Address <c:out value="${i.index+1}: ${address.country} ${address.city} ${address.zipcode} ${address.street} ${address.building} ${address.apartment}" /></option>
                            </c:if>
                        </c:forEach>
                    </select>
                 </c:when>
                <c:otherwise>
                    <select id="addressList" class="form-input" name="address.id" >
                        <option selected id="${order.address.id}"	value="${order.address.id}">Address <c:out value="${i.index+1}: ${order.address.country} ${order.address.city} ${order.address.zipcode} ${order.address.street} ${order.address.building} ${order.address.apartment}" /></option>
                    </select>
                </c:otherwise>
            </c:choose>
        </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')">
            <select id="addressList" class="form-input" name="address.id">
                <option selected id="${order.address.id}"	value="${order.address.id}">Address <c:out value="${i.index+1}: ${order.address.country} ${order.address.city} ${order.address.zipcode} ${order.address.street} ${order.address.building} ${order.address.apartment}" /></option>
            </select>
        </sec:authorize>
     </div>
     <div class="form-element">
        <label>Payment type:</label>
         <select id="paymentTypes" class="form-input" name="paymentType" >
             <option selected id="${order.paymentType}" value="${order.paymentType}">${order.paymentType.code}</option>
         </select>
     </div>
    <div class="form-element">
        <label>Delivery type:</label>
        <sec:authorize access="hasRole('USER')">
            <c:choose>
                <c:when test="${order.orderStatus=='NOTSHIPPED'}">
                    <select id="deliveryTypes" class="form-input" name="deliveryType" >
                        <c:forEach var="deliveryType" items="${deliveryTypes}">
                            <c:if test="${deliveryType==order.deliveryType}">
                                <option selected id="${deliveryType}" value="${deliveryType}">${deliveryType.code}</option>
                            </c:if>
                            <c:if test="${deliveryType!=order.deliveryType}">
                                <option id="${deliveryType}" value="${deliveryType}">${deliveryType.code}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <select id="deliveryTypes" class="form-input" name="deliveryType" >
                        <option selected id="${order.deliveryType}" value="${order.deliveryType}">${order.deliveryType.code}</option>
                    </select>
                </c:otherwise>
            </c:choose>
        </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')">
            <select id="deliveryTypes" class="form-input" name="deliveryType" >
                <option selected id="${order.deliveryType}" value="${order.deliveryType}">${order.deliveryType.code}</option>
            </select>
        </sec:authorize>
    </div>
    <div class="form-element">
        <label>Payment status:</label>
        <sec:authorize access="hasRole('USER')">
            <select id="paymentStatuses" class="form-input" name="paymentStatus" >
                <option selected id="${order.paymentStatus}" value="${order.paymentStatus}">${order.paymentStatus.code}</option>
            </select>
        </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')">
            <c:choose>
                <c:when test="${order.paymentStatus=='NOTPAID'}">
                    <select id="paymentStatuses" class="form-input" name="paymentStatus" >
                        <c:forEach var="paymentStatus" items="${paymentStatuses}">
                            <c:if test="${paymentStatus==order.paymentStatus}">
                                <option selected id="${paymentStatus}" value="${paymentStatus}">${paymentStatus.code}</option>
                            </c:if>
                            <c:if test="${paymentStatus!=order.paymentStatus}">
                                <option selected id="${paymentStatus}" value="${paymentStatus}">${paymentStatus.code}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <select id="paymentStatuses" class="form-input" name="paymentStatus" >
                        <option selected id="${order.paymentStatus}" value="${order.paymentStatus}">${order.paymentStatus.code}</option>
                    </select>
                </c:otherwise>
            </c:choose>
        </sec:authorize>
    </div>
    <div class="form-element">
        <label>Order status:</label>
        <sec:authorize access="hasRole('USER')">
            <select id="orderStatuses" class="form-input" name="orderStatus" >
                <option selected id="${order.orderStatus}" value="${order.orderStatus}">${order.orderStatus.code}</option>
            </select>
        </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')">
            <c:choose>
                <c:when test="${order.orderStatus!='DELIVERED'}">
                    <select id="orderStatuses" class="form-input" name="orderStatus" >
                        <c:forEach var="orderStatus" items="${orderStatuses}">
                            <c:if test="${orderStatus==order.orderStatus}">
                                <option selected id="${orderStatus}" value="${orderStatus}">${orderStatus.code}</option>
                            </c:if>
                            <c:if test="${orderStatus!=order.orderStatus}">
                                <option  id="${orderStatus}" value="${orderStatus}">${orderStatus.code}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <select id="orderStatuses" class="form-input" name="orderStatus" >
                        <option selected id="${order.orderStatus}" value="${order.orderStatus}">${order.orderStatus.code}</option>
                    </select>
                </c:otherwise>
            </c:choose>
        </sec:authorize>
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
                    <img src="<c:url value="/images/products/${orderDetails.product.id}.jpg"/>" width="50" border="0" align="middle" onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
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