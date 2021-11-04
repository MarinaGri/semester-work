<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="jsPath" required="false" type="java.lang.String"%>

<t:mainLayout title="Profile page" css="static/css/styleForProfile.css"
              jsPath="${pageContext.request.contextPath}/js/modal.js">
    <div class="main-row">
        <div class="account">
            <div class="name">
                <b>${account.firstName}<br>${account.lastName}</b>
            </div>
            <div class="follow">
                <b>Подписки:${numOfSubs}</b>
                <a href="<c:url value='/subscribers?user=${account.id}'/>">
                    <img class="icon" src="<c:url value='/static/img/svg/people-fill.svg'/>">
                </a>
            </div>
            <div class="follow">
                <b>Посты:${numOfPosts}</b>
                <a href="<c:url value='/profile?user=${account.id}'/>">
                    <img class="icon" src="<c:url value='/static/img/svg/file-earmark-post.svg'/>">
                </a>
            </div>
            <jsp:doBody/>
    </div>
</t:mainLayout>
