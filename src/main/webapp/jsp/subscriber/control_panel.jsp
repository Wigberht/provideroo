
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:subscriber_layout pageName="control_panel"
                     titleKey="admin_control_panel">

    <div class="container">
        <div class="row">
            <form class="col s8 offset-s2" action="/MainController"
                  method="post">
                <h1>WELCOME <c:out value="${sessionScope.user.login}"/></h1>
                Locale: <c:out value='${sessionScope.get("locale")}'/>
            </form>
        </div>
    </div>

</mt:subscriber_layout>