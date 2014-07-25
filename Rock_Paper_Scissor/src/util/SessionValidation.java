package util;

import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SessionValidation {

    public static void CheckBreakInAttempt(HttpSession session, HttpServletRequest request) throws BreakInException {
        PlayerBean player = (PlayerBean) session.getAttribute(PlayerBean.getBeanName());
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute(PageProgressBean.getBeanName()));
        String devMode= (String) request.getAttribute("test");

        if (player == null || pageProgressBean == null) {
            throw new BreakInException();
        }

        String username = player.getmUsername();
        String password = player.getmPassword();
        String sessionKey = player.getmLoginTime();
        if (devMode!=null && !devMode.equals("true")) {
            try {
                String dbKey = DBCommonUsage.getLoginTime(username, password);
                if (!sessionKey.equals(dbKey)) {
                    throw new BreakInException();
                }
            } catch (SQLException ignored) {
            }
        }
    }
}

