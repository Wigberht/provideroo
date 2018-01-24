<%@tag description="Subscriber Page template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<%@attribute name="pageName" %>
<%@attribute name="titleKey" %>

<mt:general>
    <jsp:attribute name="head">
        <title><fmt:message key="${titleKey}"/></title>
      <mt:head/>
    </jsp:attribute>

    <jsp:attribute name="header">
      <mt:header pageName="${pageName}"/>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <mt:banned_notification/>
        </div>
        <jsp:doBody/>
    </jsp:body>
</mt:general>