package util;

import cs4280.bean.AckBean;
import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SessionValidation {

    public static void CheckBreakInAttempt(HttpSession session) throws BreakInException {
        PlayerBean player = (PlayerBean) session.getAttribute(PlayerBean.getBeanName());
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute(PageProgressBean.getBeanName()));

        if (player == null || pageProgressBean == null || !pageProgressBean.getIsLoggedIn()) {
            throw new BreakInException();
        }

        String username = player.getmUsername();
        String password = player.getmPassword();
        String sessionKey = player.getmLoginTime();

        try {
            String dbKey = DBCommonUsage.getLoginTime(username, password);
            if (!sessionKey.equals(dbKey)) {
                throw new BreakInException();
            }
        } catch (SQLException e) {
            session.setAttribute(AckBean.getBeanName(), new AckBean());
        }
    }
}

