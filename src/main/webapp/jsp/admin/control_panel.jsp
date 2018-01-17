<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="control_panel"
                titleKey="admin_control_panel">

    <div class="container">
        <div class="row">
            <form class="col s8 offset-s2" action="/MainController"
                  method="post">
                <h1>WELCOME <c:out value="${sessionScope.user.login}"/></h1>
            </form>
        </div>
    </div>

</mt:admin_layout>