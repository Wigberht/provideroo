<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="strings"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:message key="enter_to_system"/></title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

</head>


<div class="container">

    
        <form class="col s12">
            <div class="row">
                <div class="input-field col s12">
                    <input placeholder="<fmt:message key="login"/>" id="login" type="text" class="validate">
                    <label for="login"><fmt:message key="login"/> </label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input placeholder="<fmt:message key="password"/>" id="password" type="password" class="validate">
                    <label for="password"><fmt:message key="password"/> </label>
                </div>
            </div>
        </form>

    <c:forEach items="${messages}" var="message">
        <p>${message.id}:${message.message}</p>
    </c:forEach>
</div>

</body>
</html>
