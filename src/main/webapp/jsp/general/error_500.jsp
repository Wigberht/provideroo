<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>


<mt:general>
    <jsp:attribute name="head">
        <title><fmt:message key="page_not_found"/></title>
      <mt:head/>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <h1><fmt:message key="server_error"/></h1>
            <p><fmt:message key="500_description"/></p>
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
            <c:if test="${!isSubscriber && !isAdmin}">
                <a href="${root}/">
                    <fmt:message key="login_page"/>
                </a>
            </c:if>
        </div>
    </jsp:body>
</mt:general>