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
            <br/>
            <table width="100%">
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Birth date</th>
                    <th>Email</th>
                </tr>
            <c:forEach var="user" items="${topUsersList}" varStatus="i">
               <tr>
                   <td>${i.index+1}</td>
                   <td>
                       <a href="<c:url value="/orders/user/${user.id}"/>">
                               ${user.firstName} ${user.lastName}
                       </a>

                   </td>
                   <td>${user.birthday}</td>
                   <td>${user.email}</td>
               </tr>
            </c:forEach>
            </table>
    </div>
</div>