<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="admin_control_panel"/></title>
    <jsp:include page="/WEB-INF/jspf/head.jspf"/>
</head>
<body>
<div id="app">
    <c:set var="page" value="subscribers"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="container">
        <h2><fmt:message key="list_of_subscribers"/></h2>
        <c:forEach items="${subscribers}" var="subscriber">
            <div class="row">
                <div class="col s2">${subscriber.firstName}</div>
                <div class="col s2">${subscriber.lastName}</div>
                <div class="col s2">${subscriber.user.login}</div>
                <div class="col s2">${subscriber.user.banned}</div>
                <div class="col s2">${subscriber.account.balance}</div>
                <div class="col s2">${subscriber.birthDate}</div>
            </div>
        </c:forEach>
        <div class="row">
            <a href="/jsp/admin/register_user.jsp" class="btn waves-effect waves-light">
                <fmt:message key="register_new_user"/>
            </a>
        </div>
    </div>
</div>
</body>
</html>