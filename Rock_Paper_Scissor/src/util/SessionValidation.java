package util;

public class SessionValidation {

    public static boolean CheckBreakInAttempt(String username, String password, String sessionKey) {
        String dbTime = DBCommonUsage.getLoginTime(username, password);
        String encryptedKey = SessionTimeConversion.encrypt(dbTime);
        return !encryptedKey.equals(sessionKey);
    }
}
