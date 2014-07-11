<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>MainPage</title>
    <link href="<c:url value="/css/MainPage.css"/>" rel="stylesheet">
    <script src="<c:url value="/js/mainPageAnimation.js"/>"></script>
</head>
<body>


<p align="right">Welcome, xxxxxxxxx</p>

<div id="mainContainer" class="centered">
    <div id="buttons">
        <div id="stats-round-button" class="round-button">
            <a href="http://example.com">
                <img src="/img/nav/UserProfile.png" alt="Home"/>
            </a>
        </div>
        <div id="play-round-button" class="round-button">
            <a href="http://example.com">
                <img src="/img/nav/Game.png" alt="Home"/>
            </a>
        </div>
        <div id="rank-round-button" class="round-button">
            <a href="http://example.com">
                <img src="/img/nav/Statistics.png" alt="Home"/>
            </a>
        </div>
        <div id="logout-round-button" class="round-button">
            <a href="/logout">
                <img src="/img/nav/Logout.png" alt="Home"/>
            </a>
        </div>
    </div>
    <div id="nameTagsDiv">
        <div id="nameTags">
            <div id="statsNameTag" class="nameTag">Stats</div>
            <div id="playNameTag" class="nameTag">Play</div>
            <div id="rankNameTag" class="nameTag">Rank</div>
            <div id="logoutNameTag" class="nameTag">Logout</div>
        </div>
    </div>
</div>
</body>
</html>
