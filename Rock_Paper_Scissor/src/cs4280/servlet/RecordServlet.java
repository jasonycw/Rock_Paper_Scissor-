package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;
import cs4280.model.Rank;
import util.DBCommonUsage;
import util.ProjectUrl;
import util.SessionValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class RecordServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        PlayerBean playerInfo = (PlayerBean) session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute("pageInfo"));

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

        ArrayList<Rank> win_lose_rank = DBCommonUsage.getWLRateRank();
        ArrayList<Rank> number_of_game_rank = DBCommonUsage.getNumberOfGameRank();
        ArrayList<Rank> win_rank = DBCommonUsage.getWinRank();
        ArrayList<Rank> lose_rank = DBCommonUsage.getLoseRank();
        int total_play_count = DBCommonUsage.getTotalGamePlayed();

        session.setAttribute("win_lose_rank", win_lose_rank);
        session.setAttribute("number_of_game_rank", number_of_game_rank);
        session.setAttribute("win_rank", win_rank);
        session.setAttribute("lose_rank", lose_rank);
        session.setAttribute("total_play_count", total_play_count);

        pageProgressBean.setmBreadcrumb("/record");
        dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pages/RecordPage.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}