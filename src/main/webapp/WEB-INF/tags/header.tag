<%@tag description="Header of the body tag" pageEncoding="UTF-8" %>
<%@ attribute name="pageName" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<div class="navbar-fixed">
    <nav>
        <div id="navbar_header">
            <div class="nav-wrapper">
        <span class="brand-logo left noselect">
            <c:if test="${sessionScope.user.roleId==1}">
                <fmt:message key="admin_control_panel"/>
            </c:if>
            <c:if test="${sessionScope.user.roleId==2}">
                <fmt:message key="subscriber_control_panel"/>
            </c:if>
        </span>
                <ul id="nav-mobile" class="right">
                    <c:if test="${sessionScope.user.roleId==1}">
                        <page-link
                            active="${pageName.equals("subscribers")}"
                            link="${root}/admin/subscriber_list"
                            icon="people"
                            message="<fmt:message key="subscriber_list"/>"
                        ></page-link>

                        <page-link
                            active="${pageName.equals("chats")}"
                            link="${root}/admin/chat_list"
                            icon="message"
                            message="<fmt:message key="chats"/>"
                        ></page-link>

                        <page-link
                            active="${pageName.equals("services")}"
                            link="${root}/admin/service_list"
                            icon="attach_money"
                            message="<fmt:message key="service_list"/>"
                        ></page-link>
                    </c:if>
                    <c:if test="${sessionScope.user.roleId==2}">
                    </c:if>
                    <page-link
                        command="logout"
                        method="post"
                        icon="exit_to_app"
                        message="<fmt:message key="logout"/>"
                    ></page-link>
                </ul>
            </div>

        </div>
    </nav>
</div>
<%@ include file="/jsp/vue/vue-integration.jspf" %>