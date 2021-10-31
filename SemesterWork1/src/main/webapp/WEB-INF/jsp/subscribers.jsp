<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:profile>
    </div>
    <c:if test="${accounts != null && accounts.get(0) != null && accounts.get(0).firstName != null}">
    <div class="subs">
    <table class="table">
    <tr>
        <th>Ссылка</th>
        <th>Имя</th>
        <th>Фамилия</th>
    </tr>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>
                <a href="<c:url value='/profile?user=${account.id}'/>">
                <img src="<c:url value='/static/img/svg/person-circle.svg'/>">
                </a>
            </td>
            <td>${account.firstName}</td>
            <td>${account.lastName}</td>
        </tr>
    </c:forEach>
</table></div>
    </c:if>
</t:profile>