package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import cs4280.exception.BreakInException;
import util.ProjectUrl;
import util.SessionValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute(PageProgressBean.getBeanName()));

        //Break in checking
        try {
            SessionValidation.CheckBreakInAttempt(session, request, response);
        } catch (BreakInException e) {
            session.setAttribute(AckBean.getBeanName(), new AckBean("Warning", "Break-in attempt"));
            try {
                response.sendRedirect(ProjectUrl.getBaseUrl(request) + "/login");
                return;
            } catch (IOException ignored) {
            }
        }

        response.sendRedirect(ProjectUrl.getBaseUrl(request) + pageProgressBean.getmBreadcrumb());
        return;
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
