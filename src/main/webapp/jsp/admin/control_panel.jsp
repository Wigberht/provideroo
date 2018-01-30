<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="control_panel"
                 titleKey="admin_control_panel">


        <div class="row">
            <div class="col s8 offset-s2">
                <h1><fmt:message key="welcome"/>
                    <c:out value="${sessionScope.user.login}"/>
                </h1>
            </div>
        </div>

</mt:admin_layout>