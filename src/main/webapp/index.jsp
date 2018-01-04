<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Hello World!</h2>
<p>Yo?</p>
$<c:forEach items="${messages}" var="message">
    <p>${message.id}:${message.message}</p>
</c:forEach>


</body>
</html>
