<%@tag description="Head of page tag" pageEncoding="UTF-8" %>
<%@ attribute name="title" %>
<%@ include file="/WEB-INF/jspf/pre_html.jspf" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!--materialize css-->
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

<!--jquery-->
<script type="text/javascript"
        src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<!--materialize js-->
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

<!--materialize icons-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">

<!--vuejs-->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vue-select@latest"></script>

<!-- axios -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<!-- moment -->
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment-with-locales.min.js"></script>

<!--custom js scripts-->
<script src="/js/app.js"></script>
<script src="/js/tariff.js"></script>

<!-- custom css -->
<link href="/css/style.css" rel="stylesheet">

<script type="text/javascript">
    console.log(${messages.getString('logout')});
    window.msg = {};
    <c:forEach items="${messages.keySet()}" var="key">
    try {
        msg['${key}'] = "${messages[key]}";
    } catch (e) {
        console.log("error fullfilling the msgForms resource from bundle " + e);
    }
    </c:forEach>
    console.log(msg);
</script>