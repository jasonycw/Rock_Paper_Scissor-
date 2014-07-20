package cs4280.servlet;

import cs4280.bean.AckBean;
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

/*
Responsible to display user info, provide function to change user preference like theme
 */
public class ProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        PlayerBean player = (PlayerBean) session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute("pageInfo"));

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

        pageProgressBean.setmBreadcrumb("/profile");
        String submited = request.getParameter("submitProfile");


        if (submited != null && submited.equals("1")) {
            AckBean ack = new AckBean();

            String new_password = request.getParameter("new_password");
            if (new_password != null) {
                player.setmPassword(new_password);
            }
            String theme = request.getParameter("theme");
            if (theme != null) {
                player.setmPreferredTheme(theme);
            }
            try {
                player.update();
                ack.setmMessage("Received with thanks");
            } catch (SQLException e) {
                ack.setmMessage(e.getMessage());
            }

            session.setAttribute("ackMsg", ack);
        }
        dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pages/UserProfilePage.jsp");
        dispatcher.forward(request, response);
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
