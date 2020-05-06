<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-form">
    <div class="form">
        <h2>Your deposit is out of balance</h2>
        <p><c:out value="${missmatch}"/> are not enough to pay for the order.</p>
    </div>
</div>