<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:mainLayout title="Sing up page" css="css/styleForSign.css">
<div class="main-row">
    <div class="form-col">
        <form method="POST" action="">
            <div class="mb-3">
                <label class="form-label">First Name</label>
                <input required name="firstName" type="text" class="form-control"
                       aria-describedby="nameHelp" value=${firstName}>
                <div id="nameHelp" class="form-text">${nameTip}</div>
            </div>
            <div class="mb-3">
                <label class="form-label">Last Name</label>
                <input required name="lastName" type="text" class="form-control"
                       aria-describedby="lastNameHelp" value=${lastName}>
                <div id="lastNameHelp" class="form-text">${nameTip}</div>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input required name="email" type="email" class="form-control"
                       aria-describedby="emailHelp" value=${email}>
                <div id="emailHelp" class="form-text">${emailTip}</div>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input required name="password" type="password" class="form-control"
                       aria-describedby="passHelp" value=${password}>
                <div id="passHelp" class="form-text">${passwordTip}</div>
            </div>
            <div class="mb-3 form-check">
                <input required type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">I agree to the processing of data</label>
            </div>
            <button type="submit" class="button">Зарегистрироваться</button>
        </form>
    </div>
    <div class="ladder-col">
        <div class="picture">
            <img src="<c:url value='/img/people.jpg'/>" alt="People">
        </div>
        <div class="step-3">
            Посмотреть отзывы на вакансии
        </div>
        <div class="step-2">
            Рассказать про собственный карьерный путь
        </div>
        <div class="step-1">
            Узнать подробнее об интересующей профессии от её представителя
        </div>
    </div>
</div>
</t:mainLayout>
