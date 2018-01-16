<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<t:admin_layout pageName="chats"
                titleKey="chats">

    <div class="container">
        <h2><fmt:message key="list_of_chats"/></h2>

        <div class="row">
            <a href="/admin/register_user"
               class="btn waves-effect waves-light">
                <fmt:message key="register_new_user"/>
            </a>
        </div>
    </div>

</t:admin_layout>