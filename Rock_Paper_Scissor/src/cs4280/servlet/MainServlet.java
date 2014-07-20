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

public class MainServlet extends HttpServlet {

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
        pageProgressBean.setmBreadcrumb("/main");

        //if(SessionValidation.test("sdf","safd","dsaf")) lewis

        /////////////////////////////////////////////
        /*
        Renee Workspace, check session here, kick the user back if needed
        */

        /////////////////////////////////////////////


        //Forward response to jsp for display
        dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pages/MainPage.jsp");
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
