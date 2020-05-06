<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"	prefix="sec"%>
<div class="grid-form">
<form:form method="POST" class="form" modelAttribute="order" onsubmit="return checkSelected()">
    <div class="form-header">
        <h3>Add order</h3>
    </div>
        <input type="hidden" name="user.id" value="${user.id}" />
        <div class="form-element">
            <label>Address:</label>
            <select id="addressList" class="form-input" name="address.id" required>
                <option selected disabled>Choose address</option>
                <c:forEach var="address" items="${user.addressList}" varStatus="i">
                    <option id="${address.id}"	value="${address.id}">Address <c:out value="${i.index+1}: ${address.country} ${address.city} ${address.zipcode} ${address.street} ${address.building} ${address.apartment}" /></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-element">
            <label>Payment type:</label>
            <select id="paymentTypes" class="form-input" name="paymentType" required>
                <option selected disabled>Choose payment type</option>
                <c:forEach var="paymentType" items="${paymentTypes}">
                    <option id="${paymentType}" value="${paymentType}">${paymentType.code}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-element">
            <label>Delivery type:</label>
            <select id="deliveryTypes" class="form-input" name="deliveryType" required>
                <option selected disabled>Choose delivery type</option>
                <c:forEach var="deliveryType" items="${deliveryTypes}">
                    <option id="${deliveryType}" value="${deliveryType}">${deliveryType.code}</option>
                </c:forEach>
            </select>
        </div>
        <input type="hidden" name="paymentStatus" class="form-input" value="${paymentStatuses[0]}"  />
        <input type="hidden" name="orderStatus" class="form-input" value="${orderStatuses[0]}"  />
        <br/>
        <table>
            <tr>
                <th>Product image</th>
                <th>Product name</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="product" items="${productMap}"	varStatus="i">
                    <tr class="dynamic">
                        <td>
                            <img src="<c:url value="/images/products/${product.key.id}.jpg"/>" width="50" border="0" align="middle" onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                        </td>
                        <td>
                            <a href="<c:url value="/products/${product.key.id}"/>">
                                <c:out value="${product.key.name}"/>
                            </a>
                            <input type="hidden" name="orderDetailsList[${i.index}].product.id"	value="${product.key.id}" />
                            <input type="hidden" name="orderDetailsList[${i.index}].id" value="0"/>
                        </td>
                        <td>
                            <input type="number" min="1" class="form-input" name="orderDetailsList[${i.index}].quantity" value="${product.value}" />
                        </td>
                        <td>
                            <c:out value="${product.key.price*product.value}"/>
                        </td>
                        <td>
                            <input type="button" class="form-button delete" value="Remove from list" onclick="removeForm(this)" />
                        </td>
                    </tr>
            </c:forEach>
        </table>
    <br/>
    <c:if test="${empty productMap }">
        <input type="submit" class="form-button" id="submit" value="Save order" disabled/>
    </c:if>
    <c:if test="${!empty productMap }">
        <input type="submit" class="form-button" id="submit" value="Save order" />
    </c:if>


</form:form>
</div>