<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<t:admin_layout pageName="services"
                titleKey="list_of_services">

    <div class="container">
        <h2><fmt:message key="list_of_services"/></h2>

        <t:service_table services="${services}"/>

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
    </div>

</t:admin_layout>