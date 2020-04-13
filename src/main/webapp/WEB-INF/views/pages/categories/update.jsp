<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-form">
<form:form class="form" method="POST" modelAttribute="category">
    <div class="form-header">
        <h3>Update category</h3>
    </div>
        <c:out value="${error}" />
        <input type="hidden" name="id" value="${category.id}"/>
        <div class="form-group">
            <label>Category name:</label>
            <form:input type="text" path="name" value="${category.name}" class="form-input" placeholder="Category name" required="true" />
            <form:errors path="name" cssClass="errors"/>
        </div>
        <c:forEach var="parameter" items="${category.parameterList}" varStatus="i">
            <div class="dynamic" id="${i.index}">
                <input type="hidden" name="parameterList[${i.index}].id"	value="${parameter.id}" />
                <div class="form-group">
                    <input type="button" class="form-button delete" value="&#8854 Delete parameter" onclick="removeForm(this)">
                </div>
                <div class="form-group">
                    <label>Parameter name</label>
                    <input type="text" name="parameterList[${i.index}].name" class="form-input" value="${parameter.name}" required/>
                </div>
            </div>
        </c:forEach>
        <div class="form-group">
            <input type="button" class="form-button" value="&#8853 Add parameter" onclick="addForm(this)"/>
        </div>
        <div class="form-group">
            <input type="submit" value="Delete category"  class="form-button delete" formaction="<c:url value="/categories/${category.name}/delete"/>" />
        </div>
        <div class="form-group">
            <input type="submit"  class="form-button" value="Update category" />
        </div>
    </form:form>
</div>
