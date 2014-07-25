package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import util.PageURL;
import util.ProjectUrl;

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

        if (pageProgressBean != null && pageProgressBean.getmBreadcrumb() != null) {
            response.sendRedirect(ProjectUrl.getBaseUrl(request) + pageProgressBean.getmBreadcrumb());

        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(PageURL.sLoginJSPURL);
            dispatcher.forward(request, response);
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
