<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="<c:url value="/css/ack.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/loginPage.css"/>" rel="stylesheet">
    <script src="<c:url value="/js/ack.js"/>"></script>
    <script src="<c:url value="/js/prefixfree.min.js"/>"></script>
</head>
<body>
<div class="header">
    <div>Rock<span>Paper</span>Scissor</div>
</div>
<br>
<div class="login">
    <form action="" method="POST">
        <input type="text" placeholder="Username" name="username"><br>
        <input type="password" placeholder="Password" name="password"><br>
        <input type="submit" value="Login">
    </form>
    <div id="ackArea"></div>
    <script> ackError(window.document.getElementById('ackArea'),'Wrong pair of username and password'); </script>
    <script> ackSuccess(window.document.getElementById('ackArea'),'Welcome back'); </script>
    <script> ackWarning(window.document.getElementById('ackArea'),'Do not skip the login page'); </script>
    <script> ackNotice(window.document.getElementById('ackArea'),'Announcement:'); </script>
</div>
<script src="<c:url value="http://codepen.io/assets/libs/fullpage/jquery.js"/>"></script>
</body>
</html>