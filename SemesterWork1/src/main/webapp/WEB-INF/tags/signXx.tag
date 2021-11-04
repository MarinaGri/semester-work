<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="jsPath2" required="false" type="java.lang.String"%>

<t:mainLayout title="${title}" css="static/css/styleForSign.css"
              jsPath="${pageContext.request.contextPath}/js/formCheck.js" jsPath2="${jsPath2}">
    <div class="main-row">
        <div class="form-col">
            <jsp:doBody/>
        </div>
        <div class="ladder-col">
            <div class="picture">
                <img src="<c:url value='/static/img/people.jpg'/>" alt="People">
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
