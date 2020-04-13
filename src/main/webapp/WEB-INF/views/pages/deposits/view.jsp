<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="grid-form">
    <form:form class="form" method="POST" modelAttribute="deposit">
        <div class="form-header">
            <h3>Your deposit</h3>
        </div>
        <input type="hidden" name="id" value="${deposit.id}"/>
        <div class="form-element">
            <img src="<c:url value="/resources/img/card.png" />" width="300" border="0" align="center"/>
        </div>
        <div class="form-element">
            <label>Deposit number:</label>
            <input type="text" class="form-input"  value="${deposit.number}" readonly/>
        </div>
        <div class="form-element">
            <label>Deposit password:</label>
            <input type="password" class="form-input"  value="${deposit.password}" readonly/>
        </div>
        <div class="form-element">
            <label>Deposit balance:</label>
            <input type="text" class="form-input"  value="${deposit.balance}" readonly/>
        </div>
        <div class="form-element">
            <label>Add to balance:</label>
            <input type="text" class="form-input" name="balance" placeholder="+ value"  required/>
        </div>
        <div class="form-element">
            <input type="submit" class="form-button" value="Update balance" formaction="<c:url value="/deposits/updateDepositBalance"/>"/>
        </div>
        <div class="form-element">
            <a href="<c:url value="/deposits/updateDeposit"/>">
                <input type="button" class="form-button" value="Update deposit" />
            </a>
        </div>
    </form:form>
</div>
