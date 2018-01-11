<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="admin_control_panel"/></title>
    <jsp:include page="/WEB-INF/jspf/head.jspf"/>
</head>
<body>
<div id="app">
    <c:set var="pagekey" value="subscribers"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="container">
        <h2><fmt:message key="list_of_subscribers"/></h2>

        <%@ include file="/WEB-INF/jspf/pagination.jsp" %>
        <table class="striped responsive-table highlight bordered">
            <thead>
            <tr>
                <td><fmt:message key="first_name"/></td>
                <td><fmt:message key="last_name"/></td>
                <td><fmt:message key="login"/></td>
                <td><fmt:message key="banned"/></td>
                <td><fmt:message key="balance"/></td>
                <td><fmt:message key="birth_date"/></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${subscribers}" var="subscriber">
                <tr>
                    <td class="col s2">${subscriber.firstName}</td>
                    <td class="col s2">${subscriber.lastName}</td>
                    <td class="col s2">${subscriber.user.login}</td>
                    <td class="col s2">
                        <c:if test="${subscriber.user.banned}">
                            <fmt:message key="yes"/>
                        </c:if>
                        <c:if test="${!subscriber.user.banned}">
                            <fmt:message key="no"/>
                        </c:if>
                    </td>
                    <td class="col s2">${subscriber.account.balance}</td>
                    <td class="col s2">${subscriber.birthDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <%@ include file="/WEB-INF/jspf/pagination.jsp" %>

        <div class="row">
            <a href="${root}/jsp/admin/register_user.jsp"
               class="btn waves-effect waves-light">
                <fmt:message key="register_new_user"/>
            </a>
        </div>
    </div>
</div>
</body>
</html>