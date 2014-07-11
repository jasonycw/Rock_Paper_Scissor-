<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>MainPage</title>
    <link href="<c:url value="/css/MainPage.css"/>" rel="stylesheet">
</head>
<body>


<p align="right">Welcome, xxxxxxxxx</p>

<div id="mainContainer" class="centered">
    <div class="round-button">
        <a href="http://example.com">
            <img src="/img/nav/UserProfile.png" alt="Home"/>
        </a>
    </div>
    <div class="round-button">
        <a href="http://example.com">
            <img src="/img/nav/Game.png" alt="Home"/>
        </a>
    </div>
    <div class="round-button">
        <a href="http://example.com">
            <img src="/img/nav/Statistics.png" alt="Home"/>
        </a>
    </div>
    <div class="round-button">
        <a href="http://example.com">
            <img src="/img/nav/Logout.png" alt="Home"/>
        </a>
    </div>

</div>
</body>
</html>
