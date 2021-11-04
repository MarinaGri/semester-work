<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:mainLayout title="Search page" css="static/css/search.css" jsPath="${pageContext.request.contextPath}/js/comment.js">
    <div class="main-row">
        <div class="lenta-col">
            <c:forEach items="${vacancies}" var="vacancy">
                <div class="vacancy">
                    <div class="ancor">
                        <a name="${vacancy.number}" class="a_link"></a>
                    </div>
                    <form method="post" action="<c:url value='/search#${vacancy.number}'/>">
                        <a href="${vacancy.link}"><h3><b>${vacancy.name}</b></h3></a>
                        <div class="param">
                            <b>Область:</b> ${vacancy.area}<br>
                            <b>График:</b> ${vacancy.schedule}<br>
                            <b>Зарплата:</b> ${vacancy.salary}<br>
                            <b>Требования:</b> ${vacancy.requirement}<br>
                            <b>Обязанности:</b> ${vacancy.responsibility}<br>
                        </div>
                        <input type="hidden" name="numVac" value="${vacancy.number}">
                        <c:if test="${vacancy.comments != null && vacancy.comments.size() != 0 && vacancy.comments.get(0) != null
                                && vacancy.number == vacancy.comments.get(0).numVacancy}">
                            <b>Комментарии: </b><br>
                            <c:forEach items="${vacancy.comments}" var="comment">
                                <div id="${comment.id}" class="comment">
                                    <a href="<c:url value='/profile?user=${comment.accountId}'/>">
                                        <img src="<c:url value='/static/img/svg/person-circle.svg'/>">
                                    </a>
                                    <input type="hidden" name="commentEdit" value="${comment.id}">
                                    <c:if test="${comment.accountId == account.id}">
                                    <input id="${comment.id}" type="submit" name="delete" class="trash icon" value="${comment.id}">
                                    <input id="${comment.id}" name="edit" class="edit icon" type="button" value="${comment.id}">
                                    </c:if>
                                    <span class="com">${comment.text}</span><br>
                                </div>
                            </c:forEach>
                        </c:if>
                        <div class="mb-3">
                            <label for="exampleFormControlTextarea1" class="form-label"><b>Комментарий</b></label>
                            <textarea name="comment" class="form-control" id="exampleFormControlTextarea1" rows="2"></textarea>
                        </div>
                        <button type="submit" name="post" value="" class="button">Опубликовать</button>
                    </form>
                </div>
            </c:forEach>
        </div>
        <div class="form-col">
            <form method="get" action="">
                <div class="mb-3">
                    <label class="form-label">Ключевое слово</label>
                    <input name="keyword" type="text" class="form-control"
                       aria-describedby="passHelp" value="${keywordVal == null? keyword: keywordVal}">
                    <div id="keywordHelp" class="form-text">${keywordTip}</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Зарплата</label>
                    <input name="salary" type="text" class="form-control"
                       aria-describedby="passHelp" value="${salaryVal == null? salary: salaryVal}">
                    <div id="salaryHelp" class="form-text">${salaryTip}</div>
                </div>
                <t:select name="experience" value="Опыт" map="${experience}" elemVal="${experienceVal}">
                </t:select>
                <t:select name="employment" value="Тип занятости" map="${employment}" elemVal="${employmentVal}">
                </t:select>
                <t:select name="schedule" value="График" map="${schedule}" elemVal="${scheduleVal}">
                </t:select>
                <div class="mb-3 form-check">
                    <input ${onlyWithSalaryVal == 'on'? 'checked': ''}
                            name="onlyWithSalary" type="checkbox" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Показывать только с указанной зарплатой</label>
                </div>
                <button type="submit" name="sent" value="Поиск" class="button">Поиск</button>
            </form>
        </div>
    </div>
</t:mainLayout>
