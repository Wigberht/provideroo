<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="admin_control_panel"/></title>
    <jsp:include page="/WEB-INF/jspf/head.jspf"/>
</head>
<body>
<div id="app">
    <c:set var="pagekey" value="services"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="container">
        <h2><fmt:message key="list_of_services"/></h2>

        <c:forEach items="${services}" var="service">

            <div class="row">
                <div class="col s12">${service.title}</div>
            </div>
            <table class="responsive-table striped bordered">
                <thead>
                <tr>
                    <th><fmt:message key="title"/></th>
                    <th><fmt:message key="description"/></th>
                    <th><fmt:message key="number_of_days"/></th>
                    <th><fmt:message key="cost"/></th>

                </tr>
                </thead>

                <tbody>
                <c:forEach items="${service.tariffs}" var="tariff">
                    <tr>
                        <td>${tariff.title}</td>
                        <td>${tariff.description}</td>
                        <td>${tariff.numberOfDays}</td>
                        <td>${tariff.cost}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:forEach>

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
</div>
</body>
</html>