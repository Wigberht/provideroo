<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:subscriber_layout pageName="subscriber_profile"
                      titleKey="subscriber_profile">

    <div class="row">
        <h2><fmt:message key="subscriber_profile"/></h2>
    </div>
    <div class="row">
        <div class="col s6">
            <subscriber-profile-data
                text_update="<fmt:message key="update" />"
                text_update_success="<fmt:message key="update_success"/> "
                text_update_fail="<fmt:message key="update_fail"/> "
                text_first_name="<fmt:message key="first_name" />"
                text_last_name="<fmt:message key="last_name" />"
                text_login="<fmt:message key="login" />"
                subscriber='<mt:jsonify obj='${sessionScope.get("subscriber")}'/>'
            />
        </div>
        <div class="col s6">
            <subscriber-profile-balance
                text_balance="<fmt:message key="current_balance"/> "
                text_replenish="<fmt:message key="replenish" />"
                text_replenish_by="<fmt:message key="replenish_by"/>"
                text_replenish_success="<fmt:message key="replenish_success"/> "
                text_replenish_fail="<fmt:message key="replenish_fail"/> "
                subscriber='<mt:jsonify obj='${sessionScope.get("subscriber")}'/>'
            />
        </div>
    </div>

</mt:subscriber_layout>