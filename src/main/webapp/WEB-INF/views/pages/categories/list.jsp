<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="grid-content">
    <div class="sidebar">
        <ul>
            <li>
                <a class="active" href="#">
                    Actions
                </a>
            </li>
            <c:forEach var="category" items="${categoryList}">
                <li class="dropdown">
                    <a href="<c:url value="/products/categories/${category.name}"/>">
                            ${category.name}
                    </a>
                    <sec:authorize access="hasRole('EMPLOYEE')">
                        <ul class="dropdown-content">
                            <li>
                                <a class="active" href="<c:url value="/categories/${category.id}/update"/>">
                                    Update category
                                </a>
                            </li>
                            <li>
                                <a class="delete" href="<c:url value="/categories/${category.id}/delete"/>">
                                    Delete category
                                </a>
                            </li>
                        </ul>
                    </sec:authorize>
                </li>
            </c:forEach>
            <sec:authorize access="hasRole('EMPLOYEE')">
                <li>
                    <a class="active" href="<c:url value="/categories/addCategory"/>">
                        <i class="fa fa-plus" aria-hidden="true"></i> Add category
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="content">
        <div class="gallery">
            <c:forEach var="category" items="${categoryList}" varStatus="i">
                <div style="text-align: center">
                    <a style="text-decoration: none; color:black" href="<c:url value="/products/categories/${category.name}"/>">
                        <img  src="<c:url value="/images/categories/${category.id}.jpg"/>" width="300"
                             border="0" align="middle"
                             onError="this.src='<c:url value="/resources/img"/>/category.jpg';" />
                        <br/>
                        <c:out value="${category.name}"/>
                    </a>
                    <br/>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

