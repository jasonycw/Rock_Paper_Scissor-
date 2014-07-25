<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="<c:url value="/css/disclaimer.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/ack.css"/>" rel="stylesheet">
    <script src="<c:url value="/js/prefixfree.min.js"/>"></script>

    <link href="<c:url value="/css/loginPage.css"/>" rel="stylesheet">
</head>
<jsp:useBean id="ackMsg" type="cs4280.bean.AckBean" scope="session"/>
<body>
<%@ include file="../header.jsp" %>

<jsp:getProperty name="ackMsg" property="HTMLOutput"/>
<div class="mainContainer">
    <div class="header">
        <div>Rock<span>Paper</span>Scissor</div>
    </div>
    <div class="login">
        <form action="<c:url value="/validate"/>" method="POST">
            <input type="text" placeholder="Username" name="j_username" value="GhostPlayer"><br>
            <input type="password" placeholder="Password" name="j_password" value="123"><br>
            <input type="hidden" name="test" value="false">
            <input type="submit" value="Login">
        </form>
        <form action="<c:url value="/validate"/>" method="POST">
            <input type="hidden" name="test" value="true">
            <input type="submit" value="Dev Mode">
        </form>
    </div>

</div>
<%@ include file="../footer.jsp" %>
</body>
</html>