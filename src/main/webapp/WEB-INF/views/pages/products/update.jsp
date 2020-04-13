<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-form">
<form:form method="POST" class="form" modelAttribute="product" onsubmit="clearEmptyForms()"  >
    <div class="form-header">
        <h3>Update product</h3>
    </div>
        <input type="hidden" name="id" value="${product.id}"/>
        <div class="form-element">
            <img src="${path}/images/products/${product.id}.jpg" width="300" border="0" align="center"	onError="this.src='<c:url value="/resources/img"/>/product.jpg';" />
        </div>
        <div class="form-element">
            <label>Product name:</label>
            <input type="text" class="form-input" name="name" value="${product.name}" required />
        </div>
        <div class="form-element">
            <label>Product price:</label>
            <input type="text" name="price" class="form-input" value="${product.price}" required />
        </div>
        <div class="form-element">
            <label>Product weight:</label>
            <input type="text" name="weight" class="form-input" value="${product.weight}" required />
        </div>
        <div class="form-element">
            <label>Product volume:</label>
            <input type="text" name="volume" value="${product.volume}" class="form-input" required />
        </div>
        <div class="form-element">
            <label>Product quantity</label>
            <input type="text" name="quantity" value="${product.quantity}" class="form-input" required />
        </div>
        <div class="form-element">
            <label style="font-weight: bold">Choose category: </label>
            <input type="text" value="${product.category.name}" name="category.name"  list="categoryList" class="form-input" oninput="addForm(this)"/>
        </div>
        <datalist id="categoryList">
            <c:forEach var="category" items="${categoryList}" varStatus="i">
                <option id="${category.id}" value="${category.name}"/>
            </c:forEach>
        </datalist>
        <c:forEach var="category" items="${categoryList}" varStatus="i">
            <div id="category-${category.id}" class="dynamic" style ="display:none">
                <input type="hidden" name="category.id" value="${category.id}"/>
                <c:forEach var="parameter" items="${category.parameterList}" varStatus="j">
                    <div>
                        <div class="form-element">
                            <label><c:out value="${parameter.name}:"/></label>
                            <input type="hidden" class="form-input"  name="productDetailsList[${j.index}].parameter.id" value="${parameter.id}" />
                            <input type="text"  class="form-input" name="productDetailsList[${j.index}].value" />
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        <c:forEach var="parameter" items="${product.category.parameterList}" varStatus="i">
            <div class="dynamic" id="${i.index}" style ="display:block">
                <input type="hidden" name="category.id" value="${product.category.id}"/>
                <div class="form-element">
                    <label><c:out value="${parameter.name}:"/></label>
                    <c:set var="checked" value="false"/>
                    <c:forEach var="productDetails" items="${product.productDetailsList}" varStatus="j" >
                        <c:if test="${parameter.id==productDetails.parameter.id}">
                            <input type="hidden" name="productDetailsList[${i.index}].id" value="${productDetails.id}"/>
                            <input type="hidden" name="productDetailsList[${i.index}].parameter.id" value="${productDetails.parameter.id}"/>
                            <input type="text" class="form-input" name="productDetailsList[${i.index}].value" value="${productDetails.value}"/>
                            <c:set var="checked" value="true"/>
                        </c:if>
                        <c:if test="${j.last && checked==false}">
                            <input type="hidden" name="productDetailsList[${i.index}].id" value="0"/>
                            <input type="hidden" name="productDetailsList[${i.index}].parameter.id" value="${parameter.id}"/>
                            <input type="text" class="form-input" name="productDetailsList[${i.index}].value"/>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
        <div class="form-element">
            <a href="<c:url value="/products/${product.id}/delete"/>">
                <input type="button" value="Delete"  class="form-button delete" />
            </a>
        </div>
        <div class="form-element">
            <input type="submit" class="form-button" value="Save"/>
        </div>
    </form:form>
</div>