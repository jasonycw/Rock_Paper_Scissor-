package util;

import static util.DBCommonUsage.getLoginTime;

public class SessionValidation {
    public static boolean validate(String username, String password, String sessionTime) {

        String dbTime = getLoginTime(username, password);
        String expected = SessionTimeEncrytion.encrypt(dbTime);
        return expected.equals(sessionTime);

    }

}
