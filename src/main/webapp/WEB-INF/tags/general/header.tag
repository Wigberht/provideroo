<%@tag description="Header of the body tag" pageEncoding="UTF-8" %>
<%@ attribute name="pageName" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<div id="app"></div>
<div class="navbar-fixed">
    <nav>
        <div id="navbar_header">
            <div class="nav-wrapper">
                <c:if
                    test='${sessionScope.user.roleId==applicationScope.get("admin_role_id")}'>

                    <mt:admin_header/>

                </c:if>
                <c:if
                    test='${sessionScope.user.roleId==applicationScope.get("subscriber_role_id")}'>

                    <mt:subscriber_header/>

                </c:if>
            </div>
        </div>
    </nav>
</div>

<%--<%@ include file="/jsp/vue/vue-integration.jspf" %>--%>