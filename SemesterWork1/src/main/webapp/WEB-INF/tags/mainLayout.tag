<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="css" required="true" type="java.lang.String"%>
<%@attribute name="jsPath" required="false" type="java.lang.String"%>
<%@attribute name="jsPath2" required="false" type="java.lang.String"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="http-equiv" content="Content-type: text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='${css}'/>"/>
    <script>ctx = "${pageContext.request.contextPath}"</script>
    <script src="${jsPath}" charset="UTF-8"></script>
    <script src="${jsPath2}" charset="UTF-8"></script>
    <title>${title}</title>
</head>
<body>
<div class="wrapper">
    <div class="header">
        <h2>SEMESTROVKA</h2>
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
        89172453479
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
