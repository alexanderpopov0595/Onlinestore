<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-form">
    <form:form method="POST" class="form" id="search" modelAttribute="product" onsubmit="clearEmptyForms();deleteEmpty();rewriteIndexes()"  >
        <div class="form-header">
            <h3>Custom search</h3>
        </div>
            <div class="form-element">
                <label>Product name:</label>
                <input type="text" class="form-input" name="name"  />
            </div>
            <div class="form-element">
                <label>Product min price:</label>
                <input type="text" class="form-input" name="minPrice"  />
            </div>
            <div class="form-element">
                <label>Product max price:</label>
                <input type="text" class="form-input" name="maxPrice"  />
            </div>
            <div class="form-element">
                <label>Choose category: </label>
                <input type="text" class="form-input"  list="categoryList" oninput="addForm(this)" />
                <datalist id="categoryList">
                    <c:forEach var="category" items="${categoryList}" varStatus="i">
                        <option id="${category.id}" value="${category.name}"/>
                    </c:forEach>
                </datalist>
            </div>
            <c:forEach var="category" items="${categoryList}" varStatus="i">
                <div id="category-${category.id}" class="dynamic" style ="display:none">
                    <input type="hidden" class="form-input" name="category.id" value="${category.id}"/>
                    <c:forEach var="parameter" items="${category.parameterList}" varStatus="j">
                        <div class="form-element">
                            <label><c:out value="${parameter.name}:"/></label>
                            <input type="hidden"  name="productDetailsList[${j.index}].parameter.id" value="${parameter.id}" />
                            <input type="text"class="form-input" name="productDetailsList[${j.index}].value" value="" />
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>

            <input type="submit" class="form-button" value="Search"/>
            <input type="button" value="clc" onclick="clearEmptyForms();deleteEmpty();rewriteIndexes()"/>


    </form:form>
</div>
