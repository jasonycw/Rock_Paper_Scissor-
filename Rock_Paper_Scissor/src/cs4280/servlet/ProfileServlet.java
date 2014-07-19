package cs4280.servlet;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
Responsible to display user info, provide function to change user preference like theme
 */
public class ProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /////////////////////////////////////////////
        /*
        Renee Workspace, check session here, kick the user back if needed
        */
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        PlayerBean playerInfo = (PlayerBean)session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean =  ((PageProgressBean)session.getAttribute("pageInfo"));
        if (playerInfo == null ||  pageProgressBean == null || pageProgressBean.getIsLoggedIn() != true){
            dispatcher=request.getServletContext().getRequestDispatcher("/WEB-INF/pages/LoginPage.jsp");
            dispatcher.forward(request,response);
            return;
        }
        pageProgressBean.setmBreadcrumb("/profile");

        String submited = request.getParameter("submitProfile");
        if (submited != null && submited.equals("1")){
            AckBean ack  = (AckBean) session.getAttribute("ackMsg");
            /**
             * to do: set password + password checking
             */

                if(ack == null){
                    ack = new AckBean();
                }

                ack.setmMessage("Your submit has been well received, Thank you!");
                String theme = request.getParameter("theme");
                if(theme != null){
                    playerInfo.setmPreferredTheme(theme);
                }
                session.setAttribute("ackMsg",ack);
                session.setAttribute("playerInfo",playerInfo);
        }



        dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pages/UserProfilePage.jsp");
        dispatcher.forward(request,response);
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
