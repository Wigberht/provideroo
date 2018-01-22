<%@tag description="Admin Page template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<c:if test="${not empty sessionScope.validationError}">

    <div class="row" id="alert_box">
        <div class="col s12 m12">
            <div class="card red darken-1">
                <div class="row">
                    <div class="col s12 m10">
                        <div class="card-content white-text">
                            <jsp:doBody/>
                        </div>
                    </div>
                    <div class="col s12 m2">
                        <i class="fa fa-times icon_style" id="alert_close"
                           aria-hidden="true"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>

<c:remove var="validationError" scope="session"/>