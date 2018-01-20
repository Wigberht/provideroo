<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>


<mt:general>
    <jsp:attribute name="head">
        <title><fmt:message key="access_denied"/></title>
      <mt:head/>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <h1><fmt:message key="access_denied"/></h1>
            <p><fmt:message key="access_denied_description"/></p>
            <c:if test="${isAdmin}">
                <a href="${root}/admin/control_panel">
                    <fmt:message key="back"/>
                </a>
            </c:if>
            <c:if test="${isSubscriber}">
                <a href="${root}/subscriber/control_panel">
                    <fmt:message key="back"/>
                </a>
            </c:if>


        </div>
    </jsp:body>
</mt:general>