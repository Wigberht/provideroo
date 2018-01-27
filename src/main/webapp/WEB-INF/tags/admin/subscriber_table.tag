<%@tag description="Admin Page template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<%@attribute name="subscribers"
             type="java.util.List<com.d_cherkashyn.epam.model.Subscriber>" %>

<div id="subscriber-list">
    <mt:pagination/>
    <table class="striped responsive-table highlight bordered">
        <thead>
        <tr>
            <td><fmt:message key="first_name"/></td>
            <td><fmt:message key="last_name"/></td>
            <td><fmt:message key="login"/></td>
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
                        ${subscriber.account.balance}
                        ${subscriber.account.currencyShortname}
                </td>
                <td class="col s2">${subscriber.birthDate}</td>
                <td class="col s2">
                    <ban-button
                        text_ban="<fmt:message key="ban"/>"
                        text_unban="<fmt:message key="unban"/>"
                        banned_state="${subscriber.user.banned}"
                        user_id="${subscriber.user.id}"
                    ></ban-button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <mt:pagination/>
</div>