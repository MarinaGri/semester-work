<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:profile>
    <a href="<c:url value='/logout'/>"><button type="submit" class="button">Выйти</button></a>
    <a><button type="submit" id="deleteAcc" class="button">Удалить аккаунт</button></a>
    </div>
    <div class="posts">
        <form method="post" action="">
            <div class="mb-3">
                <label class="form-label">Заголовок</label>
                <input id="title" name="title" type="text" class="form-control"
                       aria-describedby="passHelp" value="${title}">
                <div id="titleHelp" class="form-text">${titleTip}</div>
            </div>
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label"><b>Новый пост</b></label>
                <textarea name="post-text" class="form-control" id="exampleFormControlTextarea1" rows="2"></textarea>
            </div>
            <button type="submit" id="1" name="post" value="" class="button">Опубликовать</button>
        </form>
        <c:forEach items="${posts}" var="post">
            <div class="post">
                <form class="form-edit" method="post" action="<c:url value='/profile#${posts.indexOf(post)}'/>">
                    <div class="ancor">
                        <a name="${posts.indexOf(post)}" class="a_link"></a>
                    </div>
                    <input type="hidden" name="postEdit" value="${post.id}">
                    <c:if test="${edit != post.id}">
                        <input id="trash-icon" name="delete" class="input icon" type="submit" value="">
                        <input id="edit-icon" name="edit" class="input icon" type="submit" value="">
                        <div class="title">${post.title}</div>
                        ${post.text}
                    </c:if>
                    <c:if test="${edit == post.id}">
                        <div class="mb-3">
                            <label class="form-label">Заголовок</label>
                            <input name="title" type="text" class="form-control"
                                   aria-describedby="passHelp" value="${post.title}">
                            <div class="form-text">${titleTip}</div>
                        </div>
                        <div class="mb-3">
                            <label for="exampleFormControlTextarea1" class="form-label"><b>Изменить пост</b></label>
                            <textarea name="post-text" class="form-control" rows="2">${post.text}</textarea>
                        </div>
                        <button type="submit" name="save" value="${post.title}${post.text}" class="button">Сохранить</button>
                    </c:if>
                </form>
            </div>
        </c:forEach>
    </div>
</t:profile>
