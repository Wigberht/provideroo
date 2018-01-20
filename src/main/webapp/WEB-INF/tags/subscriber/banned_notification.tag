<%@tag description="Header of the body tag" pageEncoding="UTF-8" %>
<%@ attribute name="pageName" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<c:set var="isBanned"
       value='${sessionScope.get("user").isBanned()}'/>
<c:set var="isProfilePage"
       value='${fn:contains(url,"/subscriber/profile")}'/>

<c:if test='${isBanned && !isProfilePage}'>
    <div class="m12 s12 col">
        <div class="card-panel red darken-2">
            <div class="row">
                <div class="col l8 white-text">
                    <h5>YOU ARE BANNED</h5>
                    <h6>You can no longer use internet</h6>
                </div>
                <div class="col l4 right-align">
                    <br>
                    <a href="${root}/subscriber/profile"
                       class="waves-effect waves-light modal-trigger btn btn-large white black-text btn-flat">
                        Replenish balance
                    </a>
                </div>
            </div>
        </div>
    </div>
</c:if>