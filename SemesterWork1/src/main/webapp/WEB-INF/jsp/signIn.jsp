<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:signXx title="Sing in page" jsPath2="${pageContext.request.contextPath}/js/token.js">
    <form method="POST" action="">
        <t:input name="email" value="${email}" innerText="Email" type="email" nameTip="${emailTip}">
        </t:input>
        <t:input name="password" value="${password}" innerText="Password" type="password"
                 nameTip="${passwordTip}" length="20">
        </t:input>
        <button type="submit" class="button" name="sent">Войти</button>
    </form>
    <a href="<c:url value='/signUp'/>">
        <button class="button">Зарегистрироваться</button></a>
    <a href="https://oauth.yandex.ru/authorize?response_type=token&client_id=${clientId}">
        <button class="button">Войти с помощью Яндекса</button></a>
</t:signXx>
