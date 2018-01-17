<%@tag description="General Page template" pageEncoding="UTF-8" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

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
        <jsp:doBody/>
    </div>
</main>
<jsp:invoke fragment="footer"/>

</body>
</html>