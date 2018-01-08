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
        <h2><fmt:message key="list_of_subscribers"/></h2>
        <div class="row">

            <a href="/jsp/admin/register_user.jsp" class="btn waves-effect waves-light">
                <fmt:message key="register_new_user"/>
            </a>
        </div>
    </div>
</div>
</body>
</html>