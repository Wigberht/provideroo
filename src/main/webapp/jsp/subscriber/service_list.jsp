<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:subscriber_layout pageName="services"
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


        <div class="search-block">
            <div class="row">
                <div class="col s10">
                    <input type="text">
                </div>
                <div class="btn col s2">
                    <fmt:message key="search"/>
                </div>
            </div>

        </div>

        <mt:service_table services="${services}"/>
    </div>

</mt:subscriber_layout>