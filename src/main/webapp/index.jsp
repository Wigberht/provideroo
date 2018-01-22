<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="enter_to_system"/></title>
    <mt:head/>
</head>
<body>
<div class="container">

    <div class="row">
        <form class="col s8 offset-s2" action="/MainController" method="post">
            <div class="center-align">
                <h1><fmt:message key="enter_to_system"/></h1>
            </div>
            <input type="hidden" name="command" value="login">

            <mt:alert_box_error>
                <c:if test="${not empty sessionScope.authError}">
                    <p><fmt:message key="validation.error.auth"/></p>
                </c:if>

                <c:remove var="authError" scope="session"/>

            </mt:alert_box_error>
            <div class="row">
                <div class="input-field col s12">
                    <input placeholder="<fmt:message key="login"/>" id="login"
                           name="login"
                           type="text" class="validate">
                    <label for="login"><fmt:message key="login"/> </label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input placeholder="<fmt:message key="password"/>"
                           id="password"
                           name="password"
                           type="password" class="validate">
                    <label for="password"><fmt:message key="password"/> </label>
                </div>
                <c:if test='${sessionScope.get("loginError")}'>
                    kek
                </c:if>
            </div>
            <div class="center-align">
                <div class="row">
                    <input type="submit"
                           value="<fmt:message key="login"/>"
                           class="btn waves-effect waves-light">
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
