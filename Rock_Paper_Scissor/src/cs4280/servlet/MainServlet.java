package cs4280.servlet;


import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
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

public class MainServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;
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
            } catch (IOException ignored) {
            }
        }
        pageProgressBean.setmBreadcrumb(PageURL.sMainServletURL);

        //Forward response to jsp for display
        dispatcher = request.getServletContext().getRequestDispatcher(PageURL.sMainJSPURL);
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
