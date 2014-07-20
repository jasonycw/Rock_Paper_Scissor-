package util;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionValidation {

    public static void CheckBreakInAttempt(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        PlayerBean player = (PlayerBean) session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute("pageInfo"));
        try {
            if (player == null || pageProgressBean == null) {
                throw new BreakInException();
            }

            String username = player.getmUsername();
            String password = player.getmPassword();
            String sessionKey = SessionTimeConversion.encrypt(player.getmLoginTime());
            String dbTime = DBCommonUsage.getLoginTime(username, password);

            String encryptedKey = SessionTimeConversion.encrypt(dbTime);
            if (!encryptedKey.equals(sessionKey)) {
                throw new BreakInException();
            }
        } catch (BreakInException e) {
            session.setAttribute("ackMsg", new AckBean("Warning", "Break-in attempt"));
            try {
                response.sendRedirect(ProjectUrl.getBaseUrl(request) + "/login");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
