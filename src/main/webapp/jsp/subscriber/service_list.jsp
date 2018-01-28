<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:subscriber_layout pageName="services"
                      titleKey="list_of_services">

    <h2><fmt:message key="list_of_services"/></h2>
    <div class="row">
        <div class="left">
            <a href="/ServicesPDF" class="btn" target="_blank">
                <fmt:message key="get_pdf"/>
            </a>
        </div>

        <div class="right">
            <%@include file="/WEB-INF/jspf/service_sort_block.jspf"%>
        </div>
    </div>

    <tariff-search-block
        user='<mt:jsonify obj="${user}"/>'
        is_admin="${isAdmin}"
        subscriptions='<mt:jsonify obj="${subscriptions}"/>'
        user_id="${user.id}"
        is_banned="${user.banned}"
    ></tariff-search-block>

    <mt:service_table services="${services}"/>

</mt:subscriber_layout>