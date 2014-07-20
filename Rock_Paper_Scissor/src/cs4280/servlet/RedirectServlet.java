package cs4280.servlet;

import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import util.ProjectUrl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        PlayerBean playerInfo = (PlayerBean) session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute("pageInfo"));
        if (playerInfo == null || pageProgressBean == null || pageProgressBean.getIsLoggedIn() != true || pageProgressBean.getmBreadcrumb() == null) {
            dispatcher = request.getServletContext().getRequestDispatcher("/login");
            dispatcher.forward(request, response);
            return;
        } else {
            response.sendRedirect(ProjectUrl.getBaseUrl(request) + pageProgressBean.getmBreadcrumb());
        }
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
