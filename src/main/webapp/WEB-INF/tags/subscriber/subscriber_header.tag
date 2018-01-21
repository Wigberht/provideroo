<%@tag description="Header of the body tag" pageEncoding="UTF-8" %>
<%@ attribute name="pageName" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<div class="brand-logo noselect">

    <a href="${root}/subscriber/control_panel">
        <fmt:message key="subscriber_control_panel"/>
    </a>
</div>

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
        active="${pageName.equals("subscriber_profile")}"
        link="${root}/subscriber/subscriber_profile"
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