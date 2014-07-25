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
            SessionValidation.CheckBreakInAttempt(session, request);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute(PageProgressBean.getBeanName()));

        //Break in checking
        try {
            SessionValidation.CheckBreakInAttempt(session, request);
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

        String submited = request.getParameter("submitProfile");
        PlayerBean player = (PlayerBean) session.getAttribute(PlayerBean.getBeanName());

        if (submited != null && submited.equals("1")) {
            AckBean ack = new AckBean();

            String new_password = request.getParameter("new_password");
            if (new_password != null && !new_password.equals("")) {
                player.setmPassword(new_password);
            }
            String theme = request.getParameter("theme");
            if (theme != null) {
                player.setmPreferredTheme(theme);
            }
            try {
                String devMode = (String) session.getAttribute("test");
                if (devMode != null && !devMode.equals("true")) {
                    player.pushToDB();
                }
                ack.setmMessage("Received with thanks");
            } catch (SQLException e) {
                ack.setmMessage(e.getMessage());
            }

            session.setAttribute(AckBean.getBeanName(), ack);
        }

        try {
            dispatcher = request.getServletContext().getRequestDispatcher(PageURL.sProfileJSPURL);
            dispatcher.forward(request, response);
            return;
        } catch (Exception ignored) {
        }
    }
}
