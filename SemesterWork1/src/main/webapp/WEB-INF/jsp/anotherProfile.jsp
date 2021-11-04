<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<t:profile>
    <a <c:if test="${isSubscribe == 'Подписаться'}">
            href="<c:url value='/subscribe?user=${account.id}'/>"
        </c:if>
        <c:if test="${isSubscribe == 'Отписаться'}">
            href="<c:url value='/unsubscribe?user=${account.id}'/>"
        </c:if> >
        <button name="subscribe" class="button">${isSubscribe}</button>
    </a>
    </div>
    <div class="posts">
        <div class="message">
            ${message}
        </div>
        <c:forEach items="${posts}" var="post">
            <div class="post">
                <form class="form-edit" method="post" action="<c:url value='/anotherProfile?user=${post.accountId}#${posts.indexOf(post)}'/>">
                    <div class="ancor">
                        <a name="${posts.indexOf(post)}" class="a_link"></a>
                    </div>
                    <input type="hidden" name="postEdit" value="${post.id}">
                    <div class="title">${post.title}</div>
                        ${post.text}
                    <t:comments post="${post}">
                    </t:comments>
                </form>
            </div>
        </c:forEach>
    </div>
</t:profile>
