<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="services"
                 titleKey="list_of_services">

    <h2><fmt:message key="list_of_services"/></h2>
    <div class="row">
        <div class="left">
            <a href="/ServicesPDF" class="btn" target="_blank">
                <fmt:message key="get_pdf"/>
            </a>
        </div>

        <div class="right">
            <%@include file="/WEB-INF/jspf/service_sort_block.jspf" %>
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


    <div class="row">
        <div class="col s3">
            <a href="/admin/new_service"
               class="btn waves-effect waves-light">
                <fmt:message key="new_service"/>
            </a>
        </div>
        <div class="col s3">
            <a href="/admin/new_tariff"
               class="btn waves-effect waves-light">
                <fmt:message key="new_tariff"/>
            </a>
        </div>
    </div>

</mt:admin_layout>