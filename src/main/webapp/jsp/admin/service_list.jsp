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
            <fmt:message key="sort"/>:

            <div class="row">
                <div class="col s6">
                    <a href="${root}/${roleName}/service_list?sortField=title&sortOrder=ASC"
                       class="btn btn-small">
                        <fmt:message key="sort.title.asc"/>
                    </a>
                </div>
                <div class="col s6">
                    <a
                        href="${root}/${roleName}/service_list?sortField=title&sortOrder=DESC"
                        class="btn btn-small">
                        <fmt:message key="sort.title.desc"/>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col s6">
                    <a
                        href="${root}/${roleName}/service_list?sortField=cost&sortOrder=ASC"
                        class="btn btn-small">
                        <fmt:message key="sort.cost.asc"/>
                    </a>
                </div>
                <div class="col s6">
                    <a
                        href="${root}/${roleName}/service_list?sortField=cost&sortOrder=DESC"
                        class="btn btn-small">
                        <fmt:message key="sort.cost.desc"/>
                    </a>
                </div>
            </div>
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