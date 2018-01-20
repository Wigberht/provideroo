<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:subscriber_layout pageName="subscriber_profile"
                      titleKey="subscriber_profile">

    <div class="container">
        <div class="row">
            <h2><fmt:message key="subscriber_profile"/></h2>
        </div>
        <div class="row">
            <div class="col s6">
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Placeholder"
                               id="first_name"
                               type="text"
                               class="validate">
                        <label for="first_name">
                            <fmt:message key="first_name"/>
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Placeholder"
                               id="last_name"
                               type="text"
                               class="validate">
                        <label for="last_name">
                            <fmt:message key="last_name"/>
                        </label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Placeholder"
                               id="login"
                               type="text"
                               class="validate"
                               value="">
                        <label for="login">
                            <fmt:message key="login"/>
                        </label>
                    </div>
                </div>
            </div>
            <div class="col s6">
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Placeholder"
                               id="kek"
                               type="text"
                               class="validate">
                        <label for="first_name">
                            <fmt:message key="first_name"/>
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </div>

</mt:subscriber_layout>