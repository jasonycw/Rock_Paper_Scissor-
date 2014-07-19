package cs4280.servlet;

import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RecordServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        PlayerBean playerInfo = (PlayerBean) session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute("pageInfo"));
        if (playerInfo == null || pageProgressBean == null || pageProgressBean.getIsLoggedIn() != true) {
            dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pages/LoginPage.jsp");
            dispatcher.forward(request, response);
            return;
        }

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
