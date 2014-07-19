package util;

import static util.DBCommonUsage.getLoginTime;

public class SessionValidation {
public static boolean CheckBreakInAttempt(String username,String password,String sessionKey) {

    String encryptedKey = "";


    String key = DBCommonUsage.getLoginTime(username, password);
    try {
        long temp = Long.valueOf(key);
        encryptedKey = Long.toString(temp + 100);
        if (encryptedKey.equals(sessionKey))
            return false;
        else
            return true;
    } catch (NumberFormatException n) {

    }
    return false;

}}
