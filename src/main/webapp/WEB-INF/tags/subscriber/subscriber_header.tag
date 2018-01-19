<%@tag description="Header of the body tag" pageEncoding="UTF-8" %>
<%@ attribute name="pageName" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<span class="brand-logo left noselect">
    <a href="${root}/subscriber/control_panel">
        <fmt:message key="subscriber_control_panel"/>
    </a>
</span>
<ul id="nav-mobile" class="right">
    <page-link
        active="${pageName.equals("services")}"
        link="${root}/subscriber/service_list"
        icon="attach_money"
        message="<fmt:message key="service_list"/>"
    ></page-link>

    <page-link
        command="logout"
        method="post"
        icon="exit_to_app"
        message="<fmt:message key="logout"/>"
    ></page-link>
</ul>