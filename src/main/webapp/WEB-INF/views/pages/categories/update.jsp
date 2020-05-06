<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-form">
<form:form class="form" method="POST" modelAttribute="category" enctype="multipart/form-data">
    <div class="form-header">
        <h3>Update category</h3>
    </div>
        <input type="hidden" name="id" value="${category.id}"/>
        <div class="form-element">
            <img src="<c:url value="/images/categories/${category.id}.jpg"/>" width="300" border="0" align="center"	onError="this.src='<c:url value="/resources/img"/>/category.jpg';" />
            <p class="errors">${error}</p>
        </div>
        <div class="form-element">
            <label>Upload image</label>
            <input name="image" class="form-input" type="file"/>
        </div>
        <div class="form-element">
            <label>Category name:</label>
            <form:input type="text" path="name" value="${category.name}" class="form-input" placeholder="Category name" required="true" />
            <form:errors path="name" cssClass="errors"/>
        </div>
        <c:if test="${category.status=='ACTIVE'}">
            <input type="hidden" name="status" value="ACTIVE"/>
        </c:if>
        <c:if test="${category.status!='ACTIVE'}">
            <div class="form-element">
                <select id="status" class="form-input" name="status" required>
                    <option id="deleted" checked value="DELETED">Category is deleted</option>
                    <option id="active" value="ACTIVE">Restore category</option>
                </select>
            </div>
        </c:if>
        <c:forEach var="parameter" items="${category.parameterList}" varStatus="i">
            <div class="dynamic" id="${i.index}">
                <input type="hidden" name="parameterList[${i.index}].id"	value="${parameter.id}" />
                <div class="form-element">
                    <input type="button" class="form-button delete" value="&#8854 Delete parameter" onclick="removeForm(this)">
                </div>
                <div class="form-element">
                    <label>Parameter name</label>
                    <input type="text" name="parameterList[${i.index}].name" class="form-input" value="${parameter.name}" required/>
                </div>
            </div>
        </c:forEach>
        <div class="form-element">
            <input type="button" class="form-button" value="&#8853 Add parameter" onclick="addForm(this)"/>
        </div>
        <div class="form-element">
            <a href="<c:url value="/categories/${category.name}/delete"/>">
                <input type="button" value="Delete"  class="form-button delete" />
            </a>
        </div>
        <div class="form-element">
            <input type="submit"  class="form-button" value="Update category" />
        </div>
    </form:form>
</div>
