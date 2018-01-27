<%@tag description="Header of the body tag for subscriber" pageEncoding="UTF-8" %>
<%@ attribute name="pageName" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<div class="brand-logo noselect">

    <a href="${root}/subscriber/control_panel">
        <fmt:message key="subscriber_control_panel"/>
    </a>
</div>

<ul id="nav-mobile" class="right">
    <page-link
        active="${pageName.equals("subscriber_profile")}"
        link="${root}/subscriber/profile"
        icon="person"
        message="<fmt:message key="subscriber_profile"/>"
    ></page-link>

    <page-link
        active="${pageName.equals("chats")}"
        link="${root}/subscriber/chat_list"
        icon="message"
        message="<fmt:message key="chats"/>"
    ></page-link>

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