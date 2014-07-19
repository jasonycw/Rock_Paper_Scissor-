<%--
  Created by IntelliJ IDEA.
  User: Louis,Renee ;)
  Date: 2014/7/11
  Time: 下午 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/js/userProfilePage.js"/>"></script>
<html>
    <head>
        <title>Personal Profile</title>
        <link href="<c:url value="/css/Background.css"/>" rel="stylesheet">
        <link href="<c:url value="/css/Profile.css"/>" rel="stylesheet">
        <link href="<c:url value="/css/ack2.css"/>" rel="stylesheet">
    </head>
    <jsp:useBean id="playerInfo" type="cs4280.bean.PlayerBean" scope="session"/>
    <jsp:useBean id="ackMsg" type="cs4280.bean.AckBean" scope="session"/>
    <body class="background<%=playerInfo.getmPreferredTheme()%>">
    <%if (request.getParameter("submitProfile") != null && request.getParameter("submitProfile").equals("1")){%>
    <jsp:getProperty name="ackMsg" property="HTMLOutput"/>
    <%}%>
    <h1 style="color: #fff">Personal Profile</h1>
        <form action="" method="post">

            <h3>Username</h3>
            <div><%=playerInfo.getmUsername()%></div>
            <h3>Password</h3>
            <input type="text" name="password"/>
            <h3>Theme</h3>
            <a href="javascript:changeTheme(1)"><div id="1" class="image background1 <%if(playerInfo.getmPreferredTheme().equals("1")){out.print("selected");}%>"></div></a>
            <a href="javascript:changeTheme(2)"><div id="2" class="image background2 <%if(playerInfo.getmPreferredTheme().equals("2")){out.print("selected");}%>"></div></a>
            <a href="javascript:changeTheme(3)"><div id="3" class="image background3 <%if(playerInfo.getmPreferredTheme().equals("3")){out.print("selected");}%>"></div></a>
            <div class="clearfix"></div>
            <input type="hidden" id="theme" name="theme" value=""<%=playerInfo.getmPreferredTheme()%>"/>
            <button type="submit" name="submitProfile" value="1">Save</button>
        </form>
    </body>
</html>

