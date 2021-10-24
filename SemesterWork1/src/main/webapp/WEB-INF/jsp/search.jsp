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
                                <b>Область:</b>${vacancy.area}<br>
                                <b>График:</b> ${vacancy.schedule} <br>
                                <b>Зарплата:</b>${vacancy.salary}<br>
                                <b>Требования:</b>${vacancy.requirement}<br>
                                <b>Обязанности:</b>${vacancy.responsibility}<br>
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlTextarea1" class="form-label">Комментарий</label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                            </div>
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
                               aria-describedby="passHelp" value=${keyword}>
                        <div id="keywordHelp" class="form-text">${keywordTip}</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Зарплата</label>
                        <input name="salary" type="text" class="form-control"
                               aria-describedby="passHelp" value=${salary}>
                        <div id="salaryHelp" class="form-text">${salaryTip}</div>
                    </div>
                    <select name="experience"  aria-label=".form-select-lg example">
                        <option value="null">Опыт</option>
                        <c:forEach items="${experience.entrySet()}" var="exp">
                            <option value="${exp.getKey()}"
                                ${exp.getKey() == expVal? 'selected': ''}> ${exp.getValue()}
                                    </option>
                        </c:forEach>
                    </select><br><br>
                    <select name="employment"  aria-label=".form-select-lg example">
                        <option value="null">Тип занятости</option>
                        <c:forEach items="${employment.entrySet()}" var="emp">
                            <option value="${emp.getKey()}">${emp.getValue()}</option>
                        </c:forEach>
                    </select><br><br>
                    <select name="schedule" aria-label=".form-select-lg example">
                        <option value="null">График</option>
                        <c:forEach items="${schedule.entrySet()}" var="sched">
                            <option value="${sched.getKey()}">${sched.getValue()}</option>
                        </c:forEach>
                    </select><br><br>
                    <div class="mb-3 form-check">
                        <input name="isWithSalary" type="checkbox" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">Показывать только с указанной зарплатой</label>
                    </div>
                    <button type="submit" class="button">Поиск</button>
                </form>
            </div>
    </div>
</t:mainLayout>
