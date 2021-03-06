<%@tag description="Service list template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<%@attribute name="services"
             type="java.util.List<com.d_cherkashyn.epam.model.Service>" %>

<ul class="collapsible" data-collapsible="expandable" id="service-table">
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

                    <div class="row center-align">
                        <div class="col s2">
                            <p><b><fmt:message key="title"/></b></p>
                        </div>
                        <div class="col s3">
                            <p><b><fmt:message key="description"/></b></p>
                        </div>
                        <div class="col s1">
                            <p><b><fmt:message key="number_of_days"/></b></p>
                        </div>
                        <div class="col s2">
                            <p><b><fmt:message key="cost"/></b></p>
                        </div>
                        <c:if test="${isSubscriber}">
                            <div class="col s2">
                                <p><b><fmt:message key="due_date"/></b></p>
                            </div>
                        </c:if>
                        <c:if test="${isAdmin}">
                            <div class="col s1">
                                <p><b><fmt:message
                                    key="subscribers_amount"/></b></p>
                            </div>
                        </c:if>
                    </div>

                    <c:forEach items="${service.tariffs}"
                               var="tariff">
                        <tariff-row
                            tariff='<mt:jsonify obj="${tariff}"/>'
                            user='<mt:jsonify obj="${user}"/>'
                            subscriptions='<mt:jsonify obj="${subscriptions}"/>'
                        ></tariff-row>
                    </c:forEach>
                </c:if>
            </div>
        </li>
    </c:forEach>
</ul>