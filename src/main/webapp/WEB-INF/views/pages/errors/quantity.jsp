<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Failed to make order</h2>
<p>Following products from order are out of quantity:</p>
<div class="error">
    <!--
    <c:forEach var="product" items="${productList}">
        <div class="form-element">
            <label>Product name</label>
            <a class="error" href="<c:url value="/products/${product.category.name}/${product.id}"/>">
                <input type="text"	value="${product.name}" readonly />
            </a>
        </div>
        <br/>
    </c:forEach>-->
    <c:forEach var="entry" items="${productMap}">
        <div class="form-element">
            <label>Product name</label>

                <input type="text"	value="${entry.key.name}" readonly />

        </div>
        <div class="form-element">
            <label>Product quan</label>

                <input type="text"	value="${entry.value}" readonly />

        </div>
        <br/>
    </c:forEach>
</div>