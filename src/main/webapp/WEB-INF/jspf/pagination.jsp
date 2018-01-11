<ul class="pagination">
    <li class="disabled"><a href="#!">
        <i class="material-icons">chevron_left</i></a>
    </li>

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