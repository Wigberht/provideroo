
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<mt:admin_layout pageName="new_service"
                titleKey="new_service">

    <div class="container">
        <div class="row">
            <form class="col s8 offset-s2" action="/MainController"
                  method="post">
                <div class="center-align">
                    <h2><fmt:message key="new_service"/></h2>
                </div>
                <input type="hidden" name="command" value="add_service">

                <div class="row">
                    <div class="input-field col s12">
                        <input type="text"
                               id="title"
                               class="validate"
                               name="title"
                               placeholder="<fmt:message key="service_title"/>">
                        <label for="title"><fmt:message key="service_title"/> </label>
                    </div>
                </div>

                <div class="center-align">
                    <div class="row">
                        <input type="submit"
                               value="<fmt:message key="add_service"/>"
                               class="btn waves-effect waves-light">
                    </div>
                </div>
            </form>
        </div>
    </div>

</mt:admin_layout>