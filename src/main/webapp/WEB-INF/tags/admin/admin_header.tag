<%@tag description="Header of the body tag" pageEncoding="UTF-8" %>
<%@ attribute name="pageName" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<span class="brand-logo left noselect">
    <a href="${root}/admin/control_panel">
        <fmt:message key="admin_control_panel"/>
    </a>
</span>

<div class="lang-block">
    <select id="lang-select" class="browser-default">

        <option value="ru_RU"
        ${(sessionScope.get("locale") eq "ru_RU")?"selected":""}>
            <fmt:message key="russian"/>
        </option>
        <option value="en_US"
        ${(sessionScope.get("locale") eq "en_US")?"selected":""}>
            <fmt:message key="english"/>
        </option>
    </select>
</div>

<ul id="nav-mobile" class="right">
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

    <page-link
        command="logout"
        method="post"
        icon="exit_to_app"
        message="<fmt:message key="logout"/>"
    ></page-link>
</ul>