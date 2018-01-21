
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="services"
                 titleKey="list_of_services">

    <div class="container">
        <h2><fmt:message key="list_of_services"/></h2>
        <div class="row">
            <div class="left">
                <a href="/ServicesPDF" class="btn" target="_blank">
                    <fmt:message key="get_pdf"/>
                </a>
            </div>

            <div class="right">
                <fmt:message key="sort"/>:
                <a href="${root}/${roleName}/service_list?sort=title"
                   class="btn">
                    <fmt:message key="sort_by_name"/>
                </a>
                <a href="${root}/${roleName}/service_list?sort=price"
                   class="btn">
                    <fmt:message key="sort_by_price"/>
                </a>
            </div>
        </div>


        <mt:service_table services="${services}"/>

        <c:if test='${isAdmin}'>
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
        </c:if>
    </div>

</mt:admin_layout>