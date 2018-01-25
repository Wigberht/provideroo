<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="new_service"
                 titleKey="new_service">

        <div class="row">
            <form class="col s8 offset-s2" action="/MainController"
                  method="post">
                <div class="center-align">
                    <h2><fmt:message key="new_tariff"/></h2>
                </div>
                <input type="hidden" name="command" value="add_tariff">

                <mt:alert_box_error>
                    <c:if test="${not empty sessionScope.titleError}">
                        <p><fmt:message key="validation.error.tariff_title"/></p>
                    </c:if>
                    <c:if test="${not empty sessionScope.descriptionError}">
                        <p><fmt:message
                            key="validation.error.tariff_description"/></p>
                    </c:if>
                    <c:if test="${not empty sessionScope.daysError}">
                        <p><fmt:message key="validation.error.days"/></p>
                    </c:if>
                    <c:if test="${not empty sessionScope.costError}">
                        <p><fmt:message key="validation.error.cost"/></p>
                    </c:if>
                    <c:if test="${not empty sessionScope.currencyError}">
                        <p><fmt:message key="validation.error.currency"/></p>
                    </c:if>

                    <c:remove var="titleError" scope="session"/>
                    <c:remove var="descriptionError" scope="session"/>
                    <c:remove var="daysError" scope="session"/>
                    <c:remove var="costError" scope="session"/>
                    <c:remove var="currencyError" scope="session"/>
                </mt:alert_box_error>

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
                        <label for="description"><fmt:message
                            key="description"/> </label>
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
                               value="UAH"
                               readonly="readonly"
                               placeholder="<fmt:message key="currency"/>">
                        <label for="currency"><fmt:message
                            key="currency"/> </label>
                    </div>

                        <%--service--%>
                    <div class="input-field col s4">
                        <select id="service-select" name="service_id">
                            <c:forEach items="${sessionScope.get('services')}"
                                       var="service">
                                <option selected
                                        value="${service.id}">${service.title}</option>
                            </c:forEach>
                        </select>
                        <label for="service-select">
                            <fmt:message key="service"/>
                        </label>
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

</mt:admin_layout>