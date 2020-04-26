<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="grid-content">
    <div class="sidebar">
        <ul>
            <li>
                <a class="active" href="#">
                    Actions
                </a>
            </li>
            <li>
                <a  href="<c:url value="/orders/addOrder"/>">
                    Add all to order
                </a>
            </li>
            <li>
                <a href="<c:url value="/cart/removeAll"/>">
                    Remove all from cart
                </a>
            </li>
        </ul>
    </div>
    <div class="content">
        <c:if test="${!empty cart.productMap}">
            <br/>
            <table>
                <tr>
                    <th></th>
                    <th>Product Image</th>
                    <th>Product name</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="product" items="${cart.productMap}" varStatus="i">
                    <tr>
                        <td>
                                ${i.index+1}.
                        </td>
                        <td>
                            <img src="<c:url value="/images/products/${product.key.id}.jpg"/>" width="50" border="0" align="left" onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                        </td>
                        <td>
                            <a href="<c:url value="/products/${product.key.id}"/>">${product.key.name}</a>
                        </td>
                        <td>
                            <a href="<c:url value="/cart/removeFromCart/${product.key.id}"/>">
                                <i class="fa fa-minus-circle" aria-hidden="true"></i>
                            </a>
                                ${product.value}
                            <a href="<c:url value="/cart/addToCart/${product.key.id}"/>">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </a>
                        </td>
                        <td>
                                ${product.key.price*product.value}
                        </td>
                        <td>
                            <a href="<c:url value="/cart/remove/${product.key.id}"/>">
                                <input type="button" class="button" value="Remove from cart"/>
                            </a>
                            <a  disabled href="<c:url value="/orders/addOrder/${product.key.id}"/>">
                                <input type="button" class="button" value="Add to order"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>