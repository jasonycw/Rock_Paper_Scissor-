<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="<c:url value="/css/ack.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/loginPage.css"/>" rel="stylesheet">
    <script src="<c:url value="/js/prefixfree.min.js"/>"></script>
</head>
<body>

<div class="mainContainer">
    <div class="header">
        <div>Rock<span>Paper</span>Scissor</div>
    </div>
    <div class="login">
        <%--<form action="j_security_check" method="POST">--%>
        <form action="/validate" method="POST">
            <input type="text" placeholder="Username" name="j_username" value="GhostPlayer"><br>
            <input type="password" placeholder="Password" name="j_password" value="123"><br>
            <input type="submit" value="Login">
        </form>

        <%--<p class="error">Wrong username-password pair</p>--%>

        <%--<p class="warning">SQL Injection Detected</p>--%>

        <%--<p class="notice">First connect to CSLab network</p>--%>

        <%--<p class="success">Welcome back</p>--%>
    </div>

</div>
<script src="<c:url value="http://codepen.io/assets/libs/fullpage/jquery.js"/>"></script>
</body>
</html>