package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.GameProgressBean;
import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;
import util.ProjectUrl;
import util.SessionValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class GameServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        PlayerBean playerInfo = (PlayerBean)session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean =  ((PageProgressBean)session.getAttribute("pageInfo"));

        //Break in checking
        try {
            SessionValidation.CheckBreakInAttempt(session, request, response);
        } catch (BreakInException e) {
            session.setAttribute("ackMsg", new AckBean("Warning", "Break-in attempt"));
            try {
                response.sendRedirect(ProjectUrl.getBaseUrl(request) + "/login");
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        // kick back if didn't logged in
        try {
            SessionValidation.CheckBreakInAttempt(session, request, response);
        } catch (BreakInException e) {
            session.setAttribute("ackMsg", new AckBean("Warning", "Break-in attempt"));
            try {
                response.sendRedirect(ProjectUrl.getBaseUrl(request) + "/login");
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (playerInfo == null ||  pageProgressBean == null || pageProgressBean.getIsLoggedIn() != true){
            response.sendRedirect(ProjectUrl.getBaseUrl(request)+"/login");
            //dispatcher=request.getServletContext().getRequestDispatcher("/WEB-INF/pages/LoginPage.jsp");
            //dispatcher.forward(request,response);
            return;
        }
        pageProgressBean.setmBreadcrumb("/game");
        // set game progress bean to session
        GameProgressBean gameInfo = (GameProgressBean)session.getAttribute("gameInfo");
        if (gameInfo == null){
            gameInfo = new GameProgressBean();
            session.setAttribute("gameInfo",gameInfo);
        }

        //See if the player gave a choice

        int playerChoice = -1;
        if(request.getParameter("rock") != null && request.getParameter("rock").equals("1")){
            playerChoice= 3;
        }else if(request.getParameter("paper") != null && request.getParameter("paper").equals("1")){
            playerChoice= 1;
        }
        else if(request.getParameter("scissor") != null && request.getParameter("scissor").equals("1")){
            playerChoice= 2;
        }

        if(playerChoice!=-1){
            int npcChoice = 1 + (int)(Math.random()*3);

            gameInfo.updateCurrentRoundResult(playerChoice,npcChoice);
            session.setAttribute("gameInfo",gameInfo);
        }

        if(request.getParameter("BackToMain") != null && request.getParameter("BackToMain").equals("1")){
            if(gameInfo.getmScore()>gameInfo.getNpcScore()){
                playerInfo.setmWinCount(playerInfo.getmWinCount() + 1);
            }else if(gameInfo.getmScore()==gameInfo.getNpcScore()){
                playerInfo.setmDrawCount(playerInfo.getmDrawCount() + 1);
            }else{
                playerInfo.setmLoseCount(playerInfo.getmLoseCount() + 1);
            }
            try {
                playerInfo.update();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            gameInfo = new GameProgressBean();
            session.setAttribute("gameInfo",gameInfo);
            response.sendRedirect(ProjectUrl.getBaseUrl(request)+"/main");

        }else {
            dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pages/GamePage.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
