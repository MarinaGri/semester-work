<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="post" required="true" type="ru.kpfu.itis.models.Post"%>

<jsp:doBody/>
<div id="${post.id}" class="comments">
    <c:forEach items="${post.comments}" var="comment">
    <div id="${comment.id}" class="comment">
        <a href="<c:url value='/profile?user=${comment.accountId}'/>">
            <img src="<c:url value='/static/img/svg/person-circle.svg'/>">
        </a>
        <input type="hidden" name="commentEdit" value="${comment.id}">
        <span class="com">${comment.text}</span><br>
    </div>
    </c:forEach>
    <input type="hidden" name="postId" value="${post.id}">
    <input id="${post.id}" name="editComment" class="plus icon" type="button" value="${post.id}">
</div>
<jsp:doBody/>
