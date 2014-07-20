package util;

import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionValidation {

    public static void CheckBreakInAttempt(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws BreakInException {
        PlayerBean player = (PlayerBean) session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute("pageInfo"));

        if (player == null || pageProgressBean == null || !pageProgressBean.getIsLoggedIn()) {
            throw new BreakInException();
        }

        String username = player.getmUsername();
        String password = player.getmPassword();
        String sessionKey = player.getmLoginTime();
        String dbKey = DBCommonUsage.getLoginTime(username, password);


        if (!sessionKey.equals(dbKey)) {
            System.out.println("HAHA");

            throw new BreakInException();
        }
    }
}

