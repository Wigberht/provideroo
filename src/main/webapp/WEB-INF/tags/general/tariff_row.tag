<%@tag description="Admin Page template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<%@attribute name="tariff"
             type="com.dimbo.model.Tariff" %>


<c:if test="${isAdmin}">
    <tariff-row-admin
        id="${tariff.id}"
        title="${tariff.title}"
        description="${tariff.description}"
        number_of_days="${tariff.numberOfDays}"
        cost="${tariff.cost}"
        currency="${tariff.currencyShortname}"
        subscribers="${tariff.subscriberAmount}"
    ></tariff-row-admin>
</c:if>

<c:if test="${isSubscriber}">
    <tariff-row-subscriber
        banned="${sessionScope.get("user").isBanned()}"
        id="${tariff.id}"
        title="${tariff.title}"
        description="${tariff.description}"
        number_of_days="${tariff.numberOfDays}"
        cost="${tariff.cost}"
        currency="${tariff.currencyShortname}"
        subscriptions='${subscriptions}'
        subscribed='${subscribed}'
        user_id='${sessionScope.user.id}'
    ></tariff-row-subscriber>
</c:if>
