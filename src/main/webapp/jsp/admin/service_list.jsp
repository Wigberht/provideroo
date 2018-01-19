<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="services"
                 titleKey="list_of_services">

    <div class="container">
        <h2><fmt:message key="list_of_services"/></h2>

        <a href="/ServicesPDF" class="btn" target="_blank">
            <fmt:message key="get_pdf"/>
        </a>

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