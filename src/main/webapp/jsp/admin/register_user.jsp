<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="new_service"
                 titleKey="new_service">

    <div class="row">
        <form class="col s8 offset-s2" action="/MainController"
              method="post">
            <div class="center-align">
                <h2><fmt:message key="new_user_credentials"/></h2>
            </div>
            <input type="hidden" name="command" value="registration">
            <input type="hidden" name="role" value="admin">

            <%@include file="/WEB-INF/jspf/registration_error_alert.jspf" %>

            <div class="row">
                <div class="input-field col s12">
                    <input type="text"
                           id="login"
                           class="validate"
                           name="login"
                           placeholder="<fmt:message key="login"/>">
                    <label for="login"><fmt:message key="login"/> </label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <input type="password"
                           id="password"
                           class="validate"
                           name="password"
                           placeholder="<fmt:message key="password"/>">
                    <label for="password"><fmt:message
                        key="password"/> </label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <input type="text"
                           id="first_name"
                           class="validate"
                           name="first_name"
                           placeholder="<fmt:message key="first_name"/>">
                    <label for="first_name"><fmt:message
                        key="first_name"/> </label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <input type="text"
                           id="last_name"
                           class="validate"
                           name="last_name"
                           placeholder="<fmt:message key="last_name"/>">
                    <label for="last_name"><fmt:message
                        key="last_name"/> </label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <input type="text"
                           class="datepicker"
                           name="birth_date"
                           id="birth_datepicker">
                    <label for="birth_datepicker"><fmt:message
                        key="birth_date"/> </label>
                </div>
            </div>

            <div class="center-align">
                <div class="row">
                    <input type="submit"
                           value="<fmt:message key="register"/>"
                           class="btn waves-effect waves-light">
                </div>
            </div>
        </form>
    </div>

</mt:admin_layout>