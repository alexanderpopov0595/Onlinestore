<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="gallery">
    <c:forEach var="product" items="${productList}" varStatus="i">
        <div style="text-align: center">
            <a style="text-decoration: none; color:black" href="<c:url value="/products/${product.id}"/>">
                <img src="<c:url value="/images/products/${product.id}.jpg"/>" width="300"
                     border="0" align="middle"
                     onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
                <br/>
                <c:out value="${product.name}"/>
            </a>
        </div>
    </c:forEach>
</div>
</div>


