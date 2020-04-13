<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<div class="grid-content">
    <div class="sidebar">
        <ul>
            <li>
                <a class="active" href="#">
                    Actions
                </a>
            </li>
                <c:if test="${product.quantity>0}">
                    <li>
                        <a href="<c:url value="/cart/addToCart/${product.id}"/>">
                            Add to cart
                        </a>
                    </li>
                </c:if>
            <sec:authorize access="hasRole('EMPLOYEE')">
                <li>
                    <a href="<c:url value="/products/${product.id}/update"/>">
                        Update product
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/products/${product.id}/delete"/>">
                        Delete product
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="content">
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Product')" id="defaultOpen">Product</button>
            <button class="tablinks" onclick="openTab(event, 'Product details')">Product details</button>
        </div>
        <div class="tabcontent" id="Product">
            <table style="width:100%">
                <tr>
                    <td rowspan="7" style="border:none">
                        <img src="${path}/images/products/${product.id}.jpg"  onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                    </td>
                    <td>Category</td>
                    <td>${product.category.name}</td>
                </tr>
                <tr>
                    <td>Product name</td>
                    <td>${product.name}</td>
                </tr>
                <tr>
                    <td>Product price</td>
                    <td>${product.price}</td>
                </tr>
                <tr>
                    <td>Product weight</td>
                    <td>${product.weight}</td>
                </tr>
                <tr>
                    <td>Product volume</td>
                    <td>${product.volume}</td>
                </tr>
                <tr>
                    <td>Product quantity</td>
                    <td>${product.quantity}</td>
                </tr>
                <c:if test="${product.quantity==0 || product.quantity<=sessionScope.cart.productMap[product]}">
                    <tr>
                        <td colspan="2">
                            <input type="button" class="form-button"  value="Product not allowed" disabled/>
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="tabcontent" id="Product details">
            <table width="80%">
                <tr>
                    <th></th>
                    <th>Parameter</th>
                    <th>Value</th>
                </tr>
                <c:forEach var="productDetails" items="${product.productDetailsList}" varStatus="i">
                    <tr>
                        <td>${i.index+1}</td>
                        <td>${productDetails.parameter.name}</td>
                        <td>${productDetails.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<script>
    document.getElementById("defaultOpen").click();
</script>















