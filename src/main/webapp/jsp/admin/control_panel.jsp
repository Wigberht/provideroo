<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="admin_control_panel"/></title>
    <jsp:include page="/WEB-INF/jspf/head.jspf"/>
</head>
<body>
<div id="app">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="container">
        <div class="row">
            <form class="col s8 offset-s2" action="/MainController" method="post">
                <h1>WELCOME <c:out value="${sessionScope.user.login}"/></h1>
            </form>
        </div>
    </div>
</div>
</body>
</html>