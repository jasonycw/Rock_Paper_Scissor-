package util;

import static util.DBCommonUsage.getLoginTime;

public class SessionValidation {
public static boolean  test(String username,String password,String sessionTime) {

    String dbTime = getLoginTime(username, password);
    long temp = Long.parseLong(dbTime);
    String result = String.valueOf(temp+100);
    if (result.equals(sessionTime))
        return true;
    else
        return false;

}

}
