<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="registration"/></title>
    <jsp:include page="/WEB-INF/jspf/head.jspf"/>
</head>
<body>

<div class="container">
    <div class="row">
        <form class="col s8 offset-s2" action="/MainController" method="post">
            <div class="center-align">
                <h1><fmt:message key="registration"/></h1>
            </div>
            <input type="hidden" name="command" value="registration">
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
                    <label for="password"><fmt:message key="password"/> </label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input type="password"
                           id="repeat_password"
                           class="validate"
                           name="repeat_password"
                           placeholder="<fmt:message key="repeat_password"/>">
                    <label for="repeat_password"><fmt:message key="repeat_password"/> </label>
                </div>
            </div>
            <div class="center-align">
                <div class="row">
                    <input type="submit"
                           value="<fmt:message key="register"/>"
                           class="btn waves-effect waves-light">
                </div>
                <div class="row">
                    <a href="/"
                       class="btn waves-effect waves-light">
                        <fmt:message key="login"/>
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>