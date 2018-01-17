<%@tag description="Admin Page template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<%@attribute name="tariff"
             type="com.dimbo.model.Tariff" %>

<tr class="tariff-row" data-tid="${tariff.id}">
    <%--<td data-tfield="title">--%>
        <%--${tariff.title}--%>
    <%--</td>--%>
    <%--<td>${tariff.description}</td>--%>
    <%--<td>${tariff.numberOfDays}</td>--%>
    <%--<td>${tariff.cost}</td>--%>
    <%--<td>${tariff.currencyShortname}</td>--%>
    <%--<td class="button-data">--%>
        <%--<c:if test="${isAdmin}">--%>
            <%--<a href="#" onclick="makeEditable(${tariff.id})">click ples</a>--%>
        <%--</c:if>--%>
    <%--</td>--%>
    <%--<td>--%>
    <%--</td>--%>
    <tariff-row
        title="${tariff.title}"
        description="${tariff.description}"
        number_of_days="${tariff.numberOfDays}"
        cost="${tariff.cost}"
        currency="${tariff.currencyShortname}"
    ></tariff-row>
</tr>
