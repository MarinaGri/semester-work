<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<t:mainLayout title="Error page" css="static/css/styleForProfile.css">
    <div class="main-row">
        <div class="error">
            <b>${message}</b>
        </div>
    </div>
</t:mainLayout>
