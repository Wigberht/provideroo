<%@tag description="Admin Page template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<%@attribute name="services"
             type="java.util.List<com.dimbo.model.Service>" %>

<ul class="collapsible" data-collapsible="expandable">
    <c:forEach items="${services}" var="service">
        <li>
            <div class="collapsible-header">
                (${fn:length(service.tariffs)})
                    ${service.title}
            </div>
            <div class="collapsible-body">
                <c:if test="${fn:length(service.tariffs)==0}">
                    <h4><fmt:message key="no_tariffs"/></h4>
                </c:if>
                <c:if test="${fn:length(service.tariffs)>0}">
                    <table class="responsive-table striped bordered">
                        <thead>
                        <tr>
                            <th><fmt:message key="title"/></th>
                            <th><fmt:message key="description"/></th>
                            <th><fmt:message key="number_of_days"/></th>
                            <th><fmt:message key="cost"/></th>
                            <th><fmt:message key="currency"/></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${service.tariffs}" var="tariff">
                            <tr>
                                <td>${tariff.title}</td>
                                <td>${tariff.description}</td>
                                <td>${tariff.numberOfDays}</td>
                                <td>${tariff.cost}</td>
                                <td>${tariff.currencyShortname}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>

            </div>
        </li>
    </c:forEach>
</ul>