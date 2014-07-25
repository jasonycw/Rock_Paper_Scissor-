package cs4280.servlet;

import cs4280.bean.AckBean;
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

public class ProfileServlet extends HttpServlet {
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
        pageProgressBean.setmBreadcrumb(PageURL.sProfileServletURL);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        RequestDispatcher dispatcher;
        try {
            dispatcher = request.getServletContext().getRequestDispatcher(PageURL.sProfileJSPURL);
            dispatcher.forward(request, response);
            return;
        } catch (Exception ignored) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;

        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute(PageProgressBean.getBeanName()));

        //Break in checking
        try {
            SessionValidation.CheckBreakInAttempt(session);
        } catch (BreakInException e) {
            session.setAttribute(AckBean.getBeanName(), new AckBean("Break-in attempt"));
            try {
                resp.sendRedirect(ProjectUrl.getBaseUrl(req) + PageURL.sLoginServletURL);
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


        pageProgressBean.setmBreadcrumb(PageURL.sProfileServletURL);

        String submited = req.getParameter("submitProfile");
        PlayerBean player = (PlayerBean) session.getAttribute(PlayerBean.getBeanName());

        if (submited != null && submited.equals("1")) {
            AckBean ack = new AckBean();

            String new_password = req.getParameter("new_password");
            if (new_password != null && !new_password.equals("")) {
                player.setmPassword(new_password);
            }
            String theme = req.getParameter("theme");
            if (theme != null) {
                player.setmPreferredTheme(theme);
            }
            try {
                player.update();
                ack.setmMessage("Received with thanks");
            } catch (SQLException e) {
                ack.setmMessage(e.getMessage());
            }

            session.setAttribute(AckBean.getBeanName(), ack);
        }

        try {
            dispatcher = req.getServletContext().getRequestDispatcher(PageURL.sProfileJSPURL);
            dispatcher.forward(req, resp);
            return;
        } catch (Exception ignored) {
        }
    }
}
