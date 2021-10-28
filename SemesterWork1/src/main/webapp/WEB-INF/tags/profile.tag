<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mainLayout title="Profile page" css="css/styleForProfile.css">
    <div class="main-row">
        <div class="account">
            <div class="name">
                <b>${firstName}<br>${lastName}</b>
            </div>
            <div class="follow">
                <b>Подписки: 0</b>
                <img class="icon" src="<c:url value='/img/svg/people-fill.svg'/>">
            </div>
            <div class="follow">
                <b>Посты: ${numOfPosts}</b>
                <img class="icon" src="<c:url value='/img/svg/file-earmark-post.svg'/>">
            </div>
        </div>
            <jsp:doBody/>
    </div>
</t:mainLayout>