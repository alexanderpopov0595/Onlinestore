<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
                <a  href="<c:url value="/sales/productsTop"/>">
                    Top-10 products
                </a>
            </li>
            <sec:authorize access="hasRole('EMPLOYEE')">
                <li>
                    <a class="active" href="<c:url value="/products/addProduct"/>">
                        <i class="fa fa-plus" aria-hidden="true"></i> Add product
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="content">
        <div class="gallery">
            <c:forEach var="product" items="${productList}" varStatus="i">
                <sec:authorize access="hasRole('EMPLOYEE')">
                    <div style="text-align: center">
                        <a style="text-decoration: none; color:black" href="<c:url value="/products/${product.id}"/>">
                            <img src="<c:url value="/images/products/${product.id}.jpg"/>" width="300"
                                 border="0" align="middle"
                                 onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                            <br/>
                            <c:out value="${product.name}"/>
                            <c:if test="${product.quantity==0 || product.quantity<=sessionScope.cart.productMap[product]}">
                                <tr>
                                    <td colspan="2">
                                        <input type="button" class="form-button"  value="Product not allowed" disabled/>
                                    </td>
                                </tr>
                            </c:if>
                        </a>
                    </div>
                </sec:authorize>
                <sec:authorize access="!hasRole('EMPLOYEE')">
                    <c:if test="${product.quantity>0}">
                        <div style="text-align: center">
                            <a style="text-decoration: none; color:black" href="<c:url value="/products/${product.id}"/>">
                                <img src="<c:url value="/images/products/${product.id}.jpg"/>" width="300"
                                     border="0" align="middle"
                                     onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                                <br/>
                                <c:out value="${product.name}"/>
                            </a>
                        </div>
                    </c:if>
                </sec:authorize>
            </c:forEach>
        </div>
    </div>
</div>

