package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import cs4280.exception.BreakInException;
import cs4280.model.Rank;
import util.DBCommonUsage;
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
import java.util.ArrayList;

public class RecordServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute(PageProgressBean.getBeanName()));

        //Break in checking
        try {
            SessionValidation.CheckBreakInAttempt(session, request);
        } catch (BreakInException e) {
            session.setAttribute(AckBean.getBeanName(), new AckBean("Break-in attempt"));
            try {
                response.sendRedirect(ProjectUrl.getBaseUrl(request) + PageURL.sLoginServletURL);
                return;
            } catch (IOException ignored) {
            }
        }

        String devMode = (String) session.getAttribute("test");
        ArrayList<Rank> win_lose_rank = new ArrayList<Rank>();
        ArrayList<Rank> number_of_game_rank = new ArrayList<Rank>();
        ArrayList<Rank> win_rank = new ArrayList<Rank>();
        ArrayList<Rank> lose_rank = new ArrayList<Rank>();
        int total_play_count = 0;
        if (devMode != null && !devMode.equals("true")) {
            win_lose_rank = DBCommonUsage.getWLRateRank();
            number_of_game_rank = DBCommonUsage.getNumberOfGameRank();
            win_rank = DBCommonUsage.getWinRank();
            lose_rank = DBCommonUsage.getLoseRank();
            total_play_count = DBCommonUsage.getTotalGamePlayed();
        } else {
            win_lose_rank.add(new Rank(1, "JustKidding", 83 + ""));
            win_lose_rank.add(new Rank(2, "One2Tree", 50 + ""));

            number_of_game_rank.add(new Rank(1, "JustKidding", 83 + ""));
            number_of_game_rank.add(new Rank(2, "One2Tree", 50 + ""));

            win_rank.add(new Rank(1, "JustKidding", 100 + ""));
            win_rank.add(new Rank(2, "One2Tree", 3 + ""));

            lose_rank.add(new Rank(1, "JustKidding", 20 + ""));
            lose_rank.add(new Rank(2, "One2Tree", 3 + ""));

            total_play_count = 999;
        }


        session.setAttribute("win_lose_rank", win_lose_rank);
        session.setAttribute("number_of_game_rank", number_of_game_rank);
        session.setAttribute("win_rank", win_rank);
        session.setAttribute("lose_rank", lose_rank);
        session.setAttribute("total_play_count", total_play_count);

        pageProgressBean.setmBreadcrumb(PageURL.sRecordServletURL);
        dispatcher = request.getServletContext().getRequestDispatcher(PageURL.sRecordJSPURL);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}