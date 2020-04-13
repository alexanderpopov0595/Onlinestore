<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="grid-form">
    <form:form class="form" method="POST" modelAttribute="deposit">
        <div class="form-header">
            <h3>Add deposit</h3>
        </div>
        <div class="form-element">
            <img  src="<c:url value="/resources/img/card.png" />" width="300" border="0" align="center"/>
        </div>
        <div class="form-element">
            <form:input type="text" class="form-input" path="number" placeholder="Number"  required="true" />
            <form:errors path="number" cssClass="errors"/>
        </div>
        <div class="form-element">
            <form:input type="password" class="form-input" path="password" placeholder="Password"   required="true"/>
            <form:errors path="password" cssClass="errors"/>
        </div>
        <div class="form-element">
            <input class="form-button" type="submit" value="Add deposit" />
        </div>
    </form:form>
</div>
