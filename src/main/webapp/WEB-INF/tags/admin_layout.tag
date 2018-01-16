<%@tag description="Admin Page template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<%@attribute name="pageName" %>
<%@attribute name="titleKey" %>

<t:general>
    <jsp:attribute name="head">
        <title><fmt:message key="${titleKey}"/></title>
      <t:head/>
    </jsp:attribute>

    <jsp:attribute name="header">

      <t:header pageName="${pageName}"/>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:general>