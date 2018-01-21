<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="chats"
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

</mt:admin_layout>