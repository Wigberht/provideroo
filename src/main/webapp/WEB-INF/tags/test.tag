<%@tag description="General Page template" pageEncoding="UTF-8" %>
<%@attribute name="pre_html" fragment="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<jsp:invoke fragment="pre_html"/>
<html>
<head>
    <jsp:invoke fragment="head"/>
</head>
<body>

<div id="pageheader">
    <jsp:invoke fragment="header"/>
</div>

<div id="body">
    <jsp:doBody/>
</div>

<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>