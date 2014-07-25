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
    <link href="<c:url value="/css/ack.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/backbutton.css"/>" rel="stylesheet">
</head>
<jsp:useBean id="playerInfo" type="cs4280.bean.PlayerBean" scope="session"/>
<jsp:useBean id="gameInfo" type="cs4280.bean.GameProgressBean" scope="session"/>
<jsp:useBean id="ackMsg" type="cs4280.bean.AckBean" scope="session"/>

<%! private String computerChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";%>
<%! private String yourChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";%>

<body class="background<%=playerInfo.getmPreferredTheme()%>" onload="changeImage();">
<%@ include file="../header.jsp" %>
<div id="backButton" class="backbutton">
    <a href="<c:url value="/main"/>">
        <img src="<c:url value="/img/nav/backbutton.png"/>" alt="Home"/>
    </a>
</div>
<div id="mainContainer">
    <div id="title" class="centered">
        <jsp:getProperty name="playerInfo" property="mUsername"/>
        V.S. <span font-style="italic">THE COMPUTER</span>
    </div>

    <div id="gameDiv" class="centered">
        <div id="score">
            <div id="playerScore">
                <jsp:getProperty name="playerInfo" property="mUsername"/>
                's Score: <%=gameInfo.getmScore()%>
            </div>

            <div id="npcScore">
                <span font-style="italic">THE COMPUTER</span>'s Score: <%=gameInfo.getNpcScore()%>
            </div>
        </div>

        <div id="game">
            <form action="" method="post">
                <div id="playerGame">
                    <div id="playerCurrentAction" class="currentAction">
                        <button id="1" value="1" name="rock" class="rock image"></button>
                        <button id="2" value="1" name="paper" class="paper image"></button>
                        <button id="3" value="1" name="scissor" class="scissor image"></button>
                    </div>
                    <div id="playerPreviousAction" class="previousAction">
                        <% for (RoundResult result : gameInfo.getmResult()) {
                            if (result.getmPlayerDecision() == gameInfo.getROCKCODE()) {
                                yourChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";
                                out.print("<img class=\"prevChoice\" src=\"" + yourChoiceSrc + "\"/>");
                            } else if (result.getmPlayerDecision() == gameInfo.getPAPERCODE()) {
                                yourChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png";
                                out.print("<img class=\"prevChoice\" src=\"" + yourChoiceSrc + "\"/>");
                            } else {
                                yourChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png";
                                out.print("<img class=\"prevChoice\" src=\"" + yourChoiceSrc + "\"/>");
                            }
                        }%>
                    </div>
                </div>

                <div id="npcGame">
                    <div id="npcPreviousAction" class="previousAction">
                        <% for (RoundResult result : gameInfo.getmResult()) {
                            if (result.getmNPCDecision() == gameInfo.getROCKCODE()) {
                                computerChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";
                                out.print("<img class=\"prevChoice\" src=\"" + computerChoiceSrc + "\"/>");
                            } else if (result.getmNPCDecision() == gameInfo.getPAPERCODE()) {
                                computerChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png";
                                out.print("<img class=\"prevChoice\" src=\"" + computerChoiceSrc + "\"/>");
                            } else {
                                computerChoiceSrc = "./img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png";
                                out.print("<img class=\"prevChoice\" src=\"" + computerChoiceSrc + "\"/>");
                            }
                        }%>
                    </div>
                    <div id="npcCurrentAction" class="currentAction">
                        <img id="img" src="<%=computerChoiceSrc%>" class="image"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>

