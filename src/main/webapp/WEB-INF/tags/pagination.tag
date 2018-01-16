<%@tag description="Admin Page template" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<%@attribute name="pagination"
             type="com.dimbo.helper.Pagination" %>

<ul class="pagination">
    <li class="disabled"><a href="#!">
        <i class="material-icons">chevron_left</i></a>
    </li>

    <%--TODO: uncomment when pagination done on back-end --%>
    <%--<c:forEach items="${pagination.pages}" var="page">--%>
    <%--<li class="${param.page==page.number?"active":"waves-effect"}">--%>
    <%--<a href="${root}${page.link}?page=${page.number}">${page.number}</a>--%>
    <%--</li>--%>
    <%--</c:forEach>--%>

    <%--TODO: change to real list of pages--%>
    <c:forEach items="${[1,2,3,4]}" var="i">
        <li class="${param.page==i?"active":"waves-effect"}">
            <a href="${root}/admin/subscriber_list?page=${i}">${i}</a>
        </li>
    </c:forEach>

    <li class="waves-effect"><a href="#!">
        <i class="material-icons">chevron_right</i></a>
    </li>
</ul>