<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="subscribers"
                 titleKey="list_of_chats">

    <h2><fmt:message key="list_of_subscribers"/></h2>

    <mt:subscriber_table subscribers="${subscribers}"/>

    <div class="row">
        <a href="${root}/admin/register_user"
           class="btn waves-effect waves-light">
            <fmt:message key="register_new_user"/>
        </a>
    </div>

</mt:admin_layout>