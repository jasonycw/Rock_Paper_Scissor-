package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
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

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute(PageProgressBean.getBeanName()));

        if (session.getAttribute(AckBean.getBeanName()) == null) {
            session.setAttribute(AckBean.getBeanName(), new AckBean());
        }

        try {
            //Break in checking
            SessionValidation.CheckBreakInAttempt(session, request, response);

            response.sendRedirect(ProjectUrl.getBaseUrl(request) + pageProgressBean.getmBreadcrumb());
        } catch (BreakInException e) {
            try {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pages/LoginPage.jsp");
                dispatcher.forward(request, response);
                return;
            } catch (Exception ignored) {
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
