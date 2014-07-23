<%--
  Created by IntelliJ IDEA.
  User: Louis,Renee ;)
  Date: 2014/7/11
  Time: 下午 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cs4280.model.RoundResult" %>
<script src="<c:url value="/js/game.js"/>"></script>
<html>

<head>
    <title>Game</title>
    <link href="<c:url value="/css/Background.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/Game.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/ack2.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/backbutton.css"/>" rel="stylesheet">
</head>
<jsp:useBean id="playerInfo" type="cs4280.bean.PlayerBean" scope="session"/>
<jsp:useBean id="gameInfo" type="cs4280.bean.GameProgressBean" scope="session"/>
<jsp:useBean id="ackMsg" type="cs4280.bean.AckBean" scope="session"/>

<%! private String computerChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";%>
<%! private String yourChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";%>

<body class="background<%=playerInfo.getmPreferredTheme()%>">
<div id="backButton" class="backbutton">
    <a href="<c:url value="/main"/>">
        <img src="<c:url value="/img/nav/backbutton.png"/>" alt="Home"/>
    </a>
</div>

<div id="resultContainer" class="centered">
    <form action="" method="post">

        <div class="winLoseTitle">
            <% if (gameInfo.getmScore() > gameInfo.getNpcScore()) {
                out.print("YOU WIN!");
            } else if (gameInfo.getmScore() == gameInfo.getNpcScore()) {
                out.print("DRAW");
            } else {
                out.print("YOU LOSE :(");
            }
            %>
        </div>
        <div class="FL resultBlock">
            <h2>Your Choices</h2>
            <% for (RoundResult result : gameInfo.getmResult()) {
                if (result.getmPlayerDecision() == gameInfo.getROCKCODE()) {
                    yourChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";
                    out.print("<img class=\"image\" src=\"" + yourChoiceSrc + "\"/>");
                } else if (result.getmPlayerDecision() == gameInfo.getPAPERCODE()) {
                    yourChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png";
                    out.print("<img class=\"image\" src=\"" + yourChoiceSrc + "\"/>");
                } else {
                    yourChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png";
                    out.print("<img class=\"image\" src=\"" + yourChoiceSrc + "\"/>");
                }
            }%>

        </div>
        <div class="FL resultBlock">
            <h2>Computer Choices</h2>
            <% for (RoundResult result : gameInfo.getmResult()) {
                if (result.getmNPCDecision() == gameInfo.getROCKCODE()) {
                    computerChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";
                    out.print("<img class=\"image\" src=\"" + computerChoiceSrc + "\"/>");
                } else if (result.getmNPCDecision() == gameInfo.getPAPERCODE()) {
                    computerChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png";
                    out.print("<img class=\"image\" src=\"" + computerChoiceSrc + "\"/>");
                } else {
                    computerChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png";
                    out.print("<img class=\"image\" src=\"" + computerChoiceSrc + "\"/>");
                }
            }%>
        </div>

    </form>
</div>
</body>


</html>

