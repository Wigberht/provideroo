<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="registration"/></title>
    <jsp:include page="/WEB-INF/jspf/head.jspf"/>
</head>
<body>

<div id="app">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container">
        <div class="row">
            <form class="col s8 offset-s2" action="/MainController"
                  method="post">
                <div class="center-align">
                    <h2><fmt:message key="new_tariff"/></h2>
                </div>
                <input type="hidden" name="command" value="add_tariff">

                <%--title--%>
                <div class="row">
                    <div class="input-field col s12">
                        <input type="text"
                               id="title"
                               class="validate"
                               name="title"
                               placeholder="<fmt:message key="title"/>">
                        <label for="title"><fmt:message key="title"/> </label>
                    </div>
                </div>

                <%--description--%>
                <div class="row">
                    <div class="input-field col s12">
                        <textarea
                            id="description"
                            class="materialize-textarea"
                            name="description"
                            placeholder="<fmt:message key="description"/>"></textarea>
                        <label for="description"><fmt:message key="description"/> </label>
                    </div>
                </div>

                <%--number of days--%>
                <div class="row">
                    <div class="input-field col s12">
                        <input type="number"
                               id="days"
                               class="validate"
                               name="days"
                               value="10"
                               placeholder="<fmt:message key="number_of_days"/>">
                        <label for="days"><fmt:message
                            key="number_of_days"/> </label>
                    </div>
                </div>

                <%--cost--%>
                <div class="row">
                    <div class="input-field col s4">
                        <input type="number"
                               id="cost"
                               class="validate"
                               name="cost"
                               value="150"
                               placeholder="<fmt:message key="cost"/>">
                        <label for="cost"><fmt:message key="cost"/> </label>
                    </div>

                    <%--currency--%>
                    <div class="input-field col s4">
                        <input type="text"
                               id="currency"
                               class="validate"
                               name="currency"
                               placeholder="<fmt:message key="currency"/>">
                        <label for="currency"><fmt:message
                            key="currency"/> </label>
                    </div>

                    <%--service--%>
                    <div class="input-field col s4">
                        <select id="service-select" name="service_id">
                            <c:forEach items="${applicationScope['services']}"
                                       var="service"
                                       begin="1">
                                <option selected value="${service.id}">${service.title}</option>
                            </c:forEach>
                        </select>
                        <label for="service-select"><fmt:message key="service"/> </label>
                    </div>
                </div>


                <%--submit--%>
                <div class="center-align">
                    <div class="row">
                        <input type="submit"
                               value="<fmt:message key="add_tariff"/>"
                               class="btn waves-effect waves-light">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>