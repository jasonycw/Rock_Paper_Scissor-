package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.GameProgressBean;
import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;
import util.PageURL;
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

        HttpSession session = request.getSession();

        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute(PageProgressBean.getBeanName()));

        //Break in checking
        try {
            SessionValidation.CheckBreakInAttempt(session);
        } catch (BreakInException e) {
            session.setAttribute(AckBean.getBeanName(), new AckBean("Break-in attempt"));
            try {
                response.sendRedirect(ProjectUrl.getBaseUrl(request) + PageURL.sLoginServletURL);
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        pageProgressBean.setmBreadcrumb(PageURL.sGameServletURL);
        // set game progress bean to session
        GameProgressBean gameInfo = (GameProgressBean) session.getAttribute(GameProgressBean.getBeanName());
        if (gameInfo == null) {
            gameInfo = new GameProgressBean();
            session.setAttribute(GameProgressBean.getBeanName(), gameInfo);
        } else {
            if (gameInfo.getmCurrentRound() >= gameInfo.getMAXROUND()) {
                gameInfo = new GameProgressBean();
                session.setAttribute(GameProgressBean.getBeanName(), gameInfo);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

        RequestDispatcher dispatcher;
        try {
            dispatcher = request.getServletContext().getRequestDispatcher(PageURL.sGameJSPURL);
            dispatcher.forward(request, response);
            return;
        } catch (Exception ignored) {
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        GameProgressBean gameInfo = (GameProgressBean) session.getAttribute(GameProgressBean.getBeanName());
        //See if the player gave a choice
        int playerChoice = -1;
        if (request.getParameter("rock") != null && request.getParameter("rock").equals("1")) {
            playerChoice = 3;
        } else if (request.getParameter("paper") != null && request.getParameter("paper").equals("1")) {
            playerChoice = 1;
        } else if (request.getParameter("scissor") != null && request.getParameter("scissor").equals("1")) {
            playerChoice = 2;
        }

        if (playerChoice != -1) {
            int npcChoice = 1 + (int) (Math.random() * 3);

            gameInfo.updateCurrentRoundResult(playerChoice, npcChoice);
            session.setAttribute(GameProgressBean.getBeanName(), gameInfo);
        }

        if (gameInfo.getmCurrentRound() >= gameInfo.getMAXROUND()) {
            PlayerBean playerInfo = (PlayerBean) session.getAttribute(PlayerBean.getBeanName());
            setPlayerScore(gameInfo, playerInfo, session);
            dispatcher = request.getServletContext().getRequestDispatcher(PageURL.sGameResultJSPURL);

        } else {
            dispatcher = request.getServletContext().getRequestDispatcher(PageURL.sGameJSPURL);
        }


        try {
            dispatcher.forward(request, response);
            return;
        } catch (Exception ignored) {
        }
    }

    private void setPlayerScore(GameProgressBean gameInfo, PlayerBean playerInfo, HttpSession session) {
        if (gameInfo.getmScore() > gameInfo.getNpcScore()) {
            playerInfo.setmWinCount(playerInfo.getmWinCount() + 1);
        } else if (gameInfo.getmScore() == gameInfo.getNpcScore()) {
            playerInfo.setmDrawCount(playerInfo.getmDrawCount() + 1);
        } else {
            playerInfo.setmLoseCount(playerInfo.getmLoseCount() + 1);
        }
        try {
            playerInfo.update();
            session.setAttribute(AckBean.getBeanName(), new AckBean("Your game result has been updated"));

        } catch (SQLException e) {
            session.setAttribute(AckBean.getBeanName(), new AckBean(e.getMessage()));
            e.printStackTrace();
        }
    }

}
