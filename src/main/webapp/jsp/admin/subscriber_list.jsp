<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="admin_control_panel"/></title>
    <jsp:include page="/WEB-INF/jspf/head.jspf"/>
</head>
<body>
<div id="app">
    <c:set var="pagekey" value="subscribers"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="container">
        <h2><fmt:message key="list_of_subscribers"/></h2>

        <%@ include file="/WEB-INF/jspf/subscriber_table.jspf" %>

        <div class="row">
            <a href="${root}/admin/register_user"
               class="btn waves-effect waves-light">
                <fmt:message key="register_new_user"/>
            </a>
        </div>
    </div>
</div>
</body>
</html>