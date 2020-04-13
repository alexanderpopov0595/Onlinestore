<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-form">
    <form:form class="form" method="POST" modelAttribute="category">
        <div class="form-header">
            <h3>Add category</h3>
        </div>
        <div class="form-element">
            <c:out value="${error}" />
        </div>
        <div class="form-element">
            <form:input type="text" path="name"   class="form-input" placeholder="Category name" required="true" />
            <form:errors path="name" cssClass="errors"/>
        </div>
        <div class="form-element">
            <input class="form-button" type="button" value="&#8853 Add parameter" onclick="addForm(this)"/>
        </div>
        <div class="form-element">
            <input class="form-button" type="submit" value="Add category"/>
        </div>
    </form:form>
</div>




