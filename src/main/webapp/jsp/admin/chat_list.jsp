<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="chats"
                 titleKey="chats">


    <h2><fmt:message key="list_of_chats"/></h2>

    <chat-send-message
        user_id="${user.id}">
    </chat-send-message>
    <div class="row">
        <a href="/admin/register_user"
           class="btn waves-effect waves-light">
            <fmt:message key="register_new_user"/>
        </a>
    </div>


</mt:admin_layout>