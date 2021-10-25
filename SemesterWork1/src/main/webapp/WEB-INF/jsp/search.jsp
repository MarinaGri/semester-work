<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:mainLayout title="Search page" css="css/styleForSearch.css">
    <div class="main-row">
        <div class="lenta-col">
            <c:forEach items="${vacancies}" var="vacancy">
                <div class="vacancy">
                    <form method="post" action="">
                        <a href="${vacancy.link}"><h3><b>${vacancy.name}</b></h3></a>
                        <div class="param">
                            <b>Область:</b> ${vacancy.area}<br>
                            <b>График:</b> ${vacancy.schedule}<br>
                            <b>Зарплата:</b> ${vacancy.salary}<br>
                            <b>Требования:</b> ${vacancy.requirement}<br>
                            <b>Обязанности:</b> ${vacancy.responsibility}<br>
                        </div>
                        <c:if test="${comments != null && comments.size() != 0 && comments.get(0) != null
                                && vacancy.number == comments.get(0).vacancy.number}">
                            <b>Комментарии: </b><br>
                            <c:forEach items="${comments}" var="comment">
                                <div class="comment">
                                    ${comment.text}<br>
                                </div>
                            </c:forEach>
                        </c:if>
                        <div class="mb-3">
                            <label for="exampleFormControlTextarea1" class="form-label"><b>Комментарий</b></label>
                            <textarea name="comment" class="form-control" id="exampleFormControlTextarea1" rows="2"></textarea>
                        </div>
                        <input type="hidden" name="numVacancy" value="${vacancy.number}">
                        <button type="submit" class="button">Опубликовать</button>
                    </form>
                </div>
            </c:forEach>
        </div>
        <div class="form-col">
            <form method="post" action="">
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
                <button type="submit" class="button">Поиск</button>
            </form>
        </div>
    </div>
</t:mainLayout>
