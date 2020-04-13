<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"	prefix="sec"%>
<div class="grid-form">
<form:form method="POST" class="form" modelAttribute="order" onsubmit="setValues()" autocomplete="off">

    <div class="form-header">
        <h3>Add order</h3>
    </div>
        <input type="hidden" name="user.id" value="${user.id}" />
        <div class="form-element">
            <label>Address:</label>
            <input type="text" class="form-input" id="address.id" list="addresses" required/>
        </div>
        <datalist id="addresses">
            <c:forEach var="address" items="${user.addressList}" varStatus="i">
                <option id="${address.id}"	value="<c:out value="${address.country} ${address.city} ${address.zipcode} ${address.street} ${address.building} ${address.apartment}"/>">Address <c:out value="${i.index+1}" /></option>
            </c:forEach>
        </datalist>
        <div class="form-element">
            <label>Payment type:</label>
            <input type="text" class="form-input" id="paymentType"	 list="paymentTypes" required />
        </div>
        <datalist id="paymentTypes">
            <c:forEach var="paymentType" items="${paymentTypes}">
                <option id="${paymentType}" value="${paymentType.code}" />
            </c:forEach>
        </datalist>
        <div class="form-element">
            <label>Delivery type:</label>
            <input type="text" class="form-input" id="deliveryType"  list="deliveryTypes" required/>
        </div>
        <datalist id="deliveryTypes">
            <c:forEach var="deliveryType" items="${deliveryTypes}">
                <option id="${deliveryType}" value="${deliveryType.code}" />
            </c:forEach>
        </datalist>
        <div class="form-element">
            <label>Payment status:</label>
            <input type="text" id="paymentStatus" class="form-input" value="${paymentStatuses[0].code}" readonly />
        </div>
        <datalist id="paymentStatuses">
            <c:forEach var="paymentStatus" items="${paymentStatuses}">
                <option id="${paymentStatus}" value="${paymentStatus.code}" />
            </c:forEach>
        </datalist>

        <div class="form-element">
            <label>Order status:</label>
            <input type="text" id="orderStatus" class="form-input" value="${orderStatuses[0].code}" readonly />
        </div>
        <datalist id="orderStatuses">
            <c:forEach var="orderStatus" items="${orderStatuses}">
                <option id="${orderStatus}" value="${orderStatus.code}" />
            </c:forEach>
        </datalist>
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
            <div class="dynamic">
                <tr>
                    <td>
                        <img src="${path}/images/products/${product.key.id}.jpg" width="50" border="0" align="middle" onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                    </td>
                    <td>
                        <a href="<c:url value="/products/${product.key.id}"/>">
                            <c:out value="${product.key.name}"/>
                        </a>
                        <input type="hidden" name="orderDetailsList[${i.index}].product.id"	value="${product.key.id}" />
                        <input type="hidden" name="orderDetailsList[${i.index}].id" value="0"/>
                    </td>
                    <td>
                        <input type="number" class="form-input" name="orderDetailsList[${i.index}].quantity" value="${product.value}" />
                    </td>
                    <td>
                        <c:out value="${product.key.price*product.value}"/>
                    </td>
                    <td>
                        <input type="button" class="form-button delete" value="Remove from list" onclick="removeForm(this)" />
                    </td>
                </tr>
            </div>
        </c:forEach>
    </table>
    <br/>
    <input type="submit" class="form-button" id="submit" value="Save order" />
</form:form>
</div>