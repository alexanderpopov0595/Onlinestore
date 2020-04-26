<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-form">
<form:form method="POST" class="form" modelAttribute="product"  onsubmit="checkSelected();clearEmptyForms()" enctype="multipart/form-data" >
    <div class="form-header">
        <h3>Add product</h3>
    </div>
        <div class="form-group">
            <label>Upload image</label>
            <input name="image" class="form-input" type="file"/>
        </div>
        <div class="form-group">
            <label>Product name:</label>
            <input type="text" name="name" class="form-input" required />
        </div>
        <div class="form-element">
            <label>Product price:</label>
            <input type="text" name="price" class="form-input" required />
        </div>
        <div class="form-element">
            <label>Product weight:</label>
            <input type="text" name="weight" class="form-input" required />
        </div>
        <div class="form-element">
            <label>Product volume:</label>
            <input type="text" name="volume" class="form-input" required />
        </div>
        <div class="form-element">
            <label>Product quantity</label>
            <input type="text" name="quantity" class="form-input" required />
        </div>
        <div class="form-element">
            <select id="categoryList" class="form-input" name="category.id" oninput="addForm(this)" >
                <option selected disabled>Choose category</option>
                <c:forEach var="category"  items="${categoryList}" varStatus="i">
                    <option id="${category.id}" value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <c:forEach var="category" items="${categoryList}" varStatus="i">
            <div id="category-${category.id}" class="dynamic" style ="display:none">
                <input type="hidden" name="category.id" value="${category.id}"/>
                <c:forEach var="parameter" items="${category.parameterList}" varStatus="j">
                    <div class="form-group">
                        <label><c:out value="${parameter.name}:"/></label>
                        <input type="hidden"  name="productDetailsList[${j.index}].parameter.id" value="${parameter.id}" />
                        <input type="text" class="form-input" name="productDetailsList[${j.index}].value" />
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        <div class="form-element">
            <input type="submit" class="form-button" value="Add product" />
        </div>
    </form:form>
</div>