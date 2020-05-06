<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-form">
    <div class="form">
        <h2>Failed to make order</h2>
        <p>Following products from order are out of quantity:</p>
        <div class="error">
            <c:forEach var="entry" items="${productMap}">
                <div class="form-element">
                    <label>Product name</label>
                    <input type="text"	class="form-input" value="${entry.key.name}" readonly />
                </div>
                <div class="form-element">
                    <label>Product quantity</label>
                    <input type="text"	class="form-input"	value="${entry.value}" readonly />
                </div>
                <br/>
            </c:forEach>
        </div>
    </div>
</div>