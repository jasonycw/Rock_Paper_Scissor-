package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;
import util.PageURL;
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
        PlayerBean player = (PlayerBean) session.getAttribute(PlayerBean.getBeanName());

        //Break in checking
        try {
            SessionValidation.CheckBreakInAttempt(session, request);
        } catch (BreakInException e) {
            if (player != null || pageProgressBean != null) {
                session.setAttribute(AckBean.getBeanName(), new AckBean("Break-in attempt"));

            } else {
                session.setAttribute(AckBean.getBeanName(), new AckBean());
            }
            try {
                response.sendRedirect(ProjectUrl.getBaseUrl(request) + PageURL.sLoginServletURL);
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
