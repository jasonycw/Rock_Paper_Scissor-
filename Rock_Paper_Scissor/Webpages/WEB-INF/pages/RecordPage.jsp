<%--
  Created by IntelliJ IDEA.
  User: Louis
  Date: 2014/7/11
  Time: 下午 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Records</title>
    <link href="<c:url value="/css/Background.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/RecordPage.css"/>" rel="stylesheet">
    <script src="<c:url value="/js/RecordPage.js"/>"></script>
</head>
<jsp:useBean id="playerInfo" type="cs4280.bean.PlayerBean" scope="session"/>
<body class="background<%=playerInfo.getmPreferredTheme()%>">
<div id="mainContainer">
    <div id="personalRankContainer">
        <%--
            TODO: User name, W/L ratio, no. of game played, no. of win, no. of lose, total game time
            Out of window, only shows while personalStateButton is clicked
        --%>
        <div id="personalRankTable" class="centered" onclick="">
            <div class="oneColumn">Total games</div>
            <div class="oneColumn">Time played</div>
            <div class="twoColumn">W/L Ratio</div>
            <div class="oneColumn">Total win</div>
            <div class="oneColumn">Total lose</div>
        </div>
    </div>
    <div id="personalStateButton">
        <%--
            TODO: Click to bring up personalRankContainer
            Located at the middle top
            Onclick will bring up the personalRankContainer
        --%>
        Personal State
    </div>
    <div id="globalRankContainer" class="centered">
        <%--
            TODO: Table showing all user ranking. Sort by W/L ratio, no. of game played, no. of win, no. of lose, total game time. Highlighting current user
            Located at middle
        --%>
        <div id="rankTagContainer">
            <div id="ratioTag" class="nameTag">W/L Ratio</div>
            <div id="totalGamesTag" class="nameTag"># of Game</div>
            <div id="totalWinTag" class="nameTag">Win</div>
            <div id="totalLoseTag" class="nameTag">Lose</div>
            <div id="gameTimeTag" class="nameTag">Time</div>
        </div>
        <div id="rankTableContainer">
            <div id="allTableContainer">
                <div id="ratioTable" class="rankTable">
                    ratioTable
                </div>
                <div id="totalGamesTable" class="rankTable">
                    totalGamesTable
                </div>
                <div id="totalWinTable" class="rankTable">
                    totalWinTable
                </div>
                <div id="totalLoseTable" class="rankTable">
                    totalLoseTable
                </div>
                <div id="gameTimeTable" class="rankTable">

                </div>
            </div>
        </div>
    </div>
    <div id="siteStatisticContainer">
        <%--
            TODO: Showing how many games has been played and current logined user account.
            Located at bottom right
        --%>
        Total number of games played: 82
        <br/>
        Current online user: 3
    </div>
</div>
</body>
</html>
