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
        edit_text='<fmt:message key="edit"/>'
        save_text='<fmt:message key="save"/>'
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
        subscribe_text='<fmt:message key="subscribe"/>'
        unsubscribe_text='<fmt:message key="unsubscribe"/>'
        subscriptions='${subscriptions}'
        subscribed='${subscribed}'
        user_id='${sessionScope.user.id}'
        subscription_fail_text='<fmt:message key="subscription.fail" />'
        subscription_success_text='<fmt:message key="subscription.success" />'
        subscription_prolong_success_text='
            <fmt:message key="subscription.prolong.success" />'
        subscription_prolong_fail_text='
            <fmt:message key="subscription.prolong.fail" />'
    ></tariff-row-subscriber>
</c:if>
