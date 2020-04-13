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
            <li>
                <a  href="<c:url value="/sales/productsTop"/>">
                    Top-10 products
                </a>
            </li>
            <sec:authorize access="hasRole('EMPLOYEE')">
                <li>
                    <a  href="<c:url value="/sales/usersTop"/>">
                        Top-10 users
                    </a>
                </li>
                <li>
                    <a  href="<c:url value="/sales/weekSales"/>">
                        Week sales
                    </a>
                </li>
                <li>
                    <a  href="<c:url value="/sales/monthSales"/>">
                        Month sales
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="content">
        <div class="gallery">
            <br/>
            <table>
                <tr>
                    <th></th>
                    <th>Date</th>
                    <th>Revenue</th>
                    <th>Order number</th>
                    <th>User</th>
                </tr>
            <c:forEach var="sales" items="${salesList}" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${sales.date}</td>
                    <td>${sales.revenue}</td>
                    <td>
                        <a href="<c:url value="/orders/${sales.order.id}"/>">
                                ${sales.order.id}
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value="/orders/users/${sales.user.id}"/>">
                                ${sales.user.firstName} ${sales.user.lastName}
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
</div>