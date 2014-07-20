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

<%! private String computerChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";%>
<%! private String yourChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";%>
<%if(gameInfo.getmCurrentRound() == gameInfo.getMAXROUND()){%>
    <body class="background<%=playerInfo.getmPreferredTheme()%>" >
    <div id="backButton" class="backbutton">
        <a href="<c:url value="/main"/>">
            <img src="<c:url value="/img/nav/backbutton.png"/>" alt="Home"/>
        </a>
    </div>
    <h1>Game Result</h1>
    <form action="" method="post">

        <div class="winLoseTitle">
            <% if(gameInfo.getmScore()>gameInfo.getNpcScore()){
                out.print("YOU WIN!");
            }else if(gameInfo.getmScore()==gameInfo.getNpcScore()){
                out.print("DRAW");
            }else{
                out.print("YOU LOSE :(");
            }
            %>
        </div>
        <div class="FL resultBlock1">
            <h2>Computer Choices</h2>
            <% for(RoundResult result : gameInfo.getmResult()) {
                if (result.getmNPCDecision() ==  gameInfo.getROCKCODE()) {
                    computerChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";
                    out.print("<img class=\"FL image\" src=\""+ computerChoiceSrc + "\"/>");
                } else if (result.getmNPCDecision() ==  gameInfo.getPAPERCODE()) {
                    computerChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png";
                    out.print("<img class=\"FL image\" src=\""+ computerChoiceSrc + "\"/>");
                } else {
                    computerChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png";
                    out.print("<img class=\"FL image\" src=\""+ computerChoiceSrc + "\"/>");
                }
            }%>
        </div>
        <div class="FL">
            <h2>Your Choices</h2>
            <% for(RoundResult result : gameInfo.getmResult()) {
                if (result.getmPlayerDecision() ==  gameInfo.getROCKCODE()) {
                    yourChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";
                    out.print("<img class=\"FL image\" src=\""+ yourChoiceSrc + "\"/>");
                } else if (result.getmPlayerDecision() ==  gameInfo.getPAPERCODE()) {
                    yourChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png";
                    out.print("<img class=\"FL image\" src=\""+ yourChoiceSrc + "\"/>");
                } else {
                    yourChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png";
                    out.print("<img class=\"FL image\" src=\""+ yourChoiceSrc + "\"/>");
                }
            }%>

        </div>
        <div class="clearfix"></div>
        <button type="submit" value="1" name="BackToMain" class="BackToMain">Back To Main</button>
    </form>
    </body>
<%}else{%>

    <body class="background<%=playerInfo.getmPreferredTheme()%>" onload="changeImage();">
        <h1>Let's Play!</h1>
        <form action="" method="post">
            <div class="FL compBlock">
                <div>
                    <% for(RoundResult result : gameInfo.getmResult()) {
                        if (result.getmNPCDecision() ==  gameInfo.getROCKCODE()) {
                            computerChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";
                            out.print("<img class=\"FL prevChoice\" src=\""+ computerChoiceSrc + "\"/>");
                        } else if (result.getmNPCDecision() ==  gameInfo.getPAPERCODE()) {
                            computerChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png";
                            out.print("<img class=\"FL prevChoice\" src=\""+ computerChoiceSrc + "\"/>");
                        } else {
                            computerChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png";
                            out.print("<img class=\"FL prevChoice\" src=\""+ computerChoiceSrc + "\"/>");
                        }
                    }%>
                </div>
                <img id="img" src="<%=computerChoiceSrc%>" class="computerImage"/>

            </div>
            <div class="FL scoreBlock">
                <div class="FL">
                    <h2>NPC Round result</h2>
                    <h2><%=gameInfo.getNpcScore()%></h2>
                </div>

                <h2 class="FL">V.S.</h2>
                <div class="FL">
                    <h2>Your Round result</h2>
                    <h2><%=gameInfo.getmScore()%></h2>
                </div>
            </div>
            <div class="FL playerBlock">
                <div class="FR">
                <% for(RoundResult result : gameInfo.getmResult()) {
                    if (result.getmPlayerDecision() ==  gameInfo.getROCKCODE()) {
                        yourChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png";
                        out.print("<img class=\"FL prevChoice\" src=\""+ yourChoiceSrc + "\"/>");
                    } else if (result.getmPlayerDecision() ==  gameInfo.getPAPERCODE()) {
                        yourChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png";
                        out.print("<img class=\"FL prevChoice\" src=\""+ yourChoiceSrc + "\"/>");
                    } else {
                        yourChoiceSrc = "/img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png";
                        out.print("<img class=\"FL prevChoice\" src=\""+ yourChoiceSrc + "\"/>");
                    }
                }%>
                </div>
                <div class="clearfix"></div>
                <button id="1" value="1" name="rock" class="rock image"></button>
                <button id="2" value="1" name="paper" class="paper image"></button>
                <button id="3" value="1" name="scissor" class="scissor image"></button>
                <div class="clearfix"></div>
           </div>
        </form>
    </body>
   <%}%>

</html>

