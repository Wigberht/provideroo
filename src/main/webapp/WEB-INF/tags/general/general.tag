<%@tag description="General Page template" pageEncoding="UTF-8" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<!DOCTYPE HTML>
<html>
<head>
    <jsp:invoke fragment="head"/>
</head>

<body>

<%--necessary for sticky footer--%>
<main>
    <%--necessary for Vue.js integration--%>
    <div id="app">
        <jsp:invoke fragment="header"/>
        <div class="container">
            <jsp:doBody/>
        </div>
    </div>
</main>
<jsp:invoke fragment="footer"/>

<%@ include file="/jsp/vue/vue-global.jspf" %>
</body>
</html>