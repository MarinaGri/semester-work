<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="name" required="true" type="java.lang.String"%>
<%@attribute name="value" required="true" type="java.lang.String"%>
<%@attribute name="innerText" required="true" type="java.lang.String" %>
<%@attribute name="type" required="true" type="java.lang.String"%>
<%@attribute name="nameTip" required="true" type="java.lang.String"%>
<%@attribute name="length" required="false" type="java.lang.String"%>

<jsp:doBody/>
<div class="mb-3">
    <label class="form-label">${innerText}</label>
    <c:if test="${length != null}">
        <div class="count">0/${length}</div>
    </c:if>
    <input required name="${name}" type="${type}" class="form-control" id="${name}"
           aria-describedby="${name}Help" value=${value}>
    <div id="${name}Help" class="form-text">${nameTip}</div>
</div>
<jsp:doBody/>
