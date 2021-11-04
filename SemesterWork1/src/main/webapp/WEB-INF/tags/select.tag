<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="name" required="true" type="java.lang.String"%>
<%@attribute name="value" required="true" type="java.lang.String"%>
<%@attribute name="map" required="true" type="java.util.Map" %>
<%@attribute name="elemVal" required="true" type="java.lang.String"%>

<jsp:doBody/>
<select name="${name}" aria-label=".form-select-lg example">
    <option value="null">${value}</option>
    <c:forEach items="${map.entrySet()}" var="elem">
        <option value="${elem.getKey()}"
            ${elem.getKey() == elemVal? 'selected': ''}> ${elem.getValue()}
        </option>
    </c:forEach>
</select><br><br>
<jsp:doBody/>
