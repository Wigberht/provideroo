<%@tag description="Lang selector tag tag" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<div class="lang-block">
    <select id="lang-select" class="browser-default">
        <option value="ru_RU"
        ${(sessionScope.get("locale") eq "ru_RU")?"selected":""}>
            <fmt:message key="russian"/>
        </option>
        <option value="en_US"
        ${(sessionScope.get("locale") eq "en_US")?"selected":""}>
            <fmt:message key="english"/>
        </option>
    </select>   
</div>