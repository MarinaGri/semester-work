<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="css" required="true" type="java.lang.String"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="http-equiv" content="Content-type: text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='${css}'/>"/>
    <script>ctx = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/modal.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/token.js" charset="utf-8"></script>
    <title>${title}</title>
</head>
<body>
<div class="wrapper">
    <div class="header">
        kkdjksjdvbkjsbvks
        <div class="icon-group">
            <a href="<c:url value='/profile'/>">
                <img class="icon" src="<c:url value='/static/img/svg/person-fill.svg'/>" alt="profile">
            </a>
            <a href="<c:url value='/search'/>">
                <img class="icon" src="<c:url value='/static/img/svg/card-heading.svg'/>" alt="feed">
            </a>
        </div>
    </div>
    <jsp:doBody/>
    <div class="footer">
        svjnvnjksnjkvn
        <div class="icon-group">
            <a href="https://github.com/MarinaGri">
                <img class="icon" src="<c:url value='/static/img/svg/github.svg'/>" alt="github">
            </a>
            <a href="https://t.me/MaryGrimm">
                <img class="icon" src="<c:url value='/static/img/svg/telegram.svg'/>" alt="telegram">
            </a>
        </div>
    </div>
</div>
</body>
</html>