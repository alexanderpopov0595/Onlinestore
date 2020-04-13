<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="grid-form">
    <form:form class="form" method="POST" modelAttribute="deposit">
        <div class="form-header">
            <h3>Update deposit</h3>
        </div>
        <input type="hidden" name="user.id" value="${deposit.user.id}"/>
        <input type="hidden" name="id" value="${deposit.id}"/>
        <input type="hidden" name="balance" value="${deposit.balance}"/>
        <div class="form-element">
            <img class="image" src="<c:url value="/resources/img/card.png" />" width="300" border="0" align="center"/>
        </div>
        <div class="form-element">
            <label>Deposit number:</label>
            <form:input type="text" class="form-input" path="number" placeholder="Number" value="${deposit.number}" required="true" />
            <form:errors path="number" cssClass="errors"/>
        </div>
        <div class="form-element">
            <label>Deposit password:</label>
            <form:input type="password" class="form-input" path="password" placeholder="Password"  value="${deposit.password}" required="true"/>
            <form:errors path="password" cssClass="errors"/>
        </div>
        <div class="form-element">
            <input class="form-button" type="submit" value="Update deposit"/>
        </div>
        <div class="form-element">
            <input class="form-button delete" type="submit" value="Delete deposit"  formaction="<c:url value="/deposits/deleteDeposit"/>"/>
        </div>
    </form:form>
</div>