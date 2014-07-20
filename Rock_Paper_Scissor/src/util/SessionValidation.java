package util;

import cs4280.bean.PageProgressBean;
import cs4280.bean.PlayerBean;
import cs4280.exception.BreakInException;

import javax.servlet.http.HttpSession;

public class SessionValidation {

    public static void CheckBreakInAttempt(HttpSession session) throws BreakInException {
        PlayerBean player = (PlayerBean) session.getAttribute("playerInfo");
        PageProgressBean pageProgressBean = ((PageProgressBean) session.getAttribute("pageInfo"));

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
    }
}
