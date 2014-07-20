package util;

import java.util.Date;

public class Time {
    public static String getCurrentTimeInUnix() {
        long time;
        Date date = new Date();
        time = date.getTime();
        return String.valueOf(time/1000);
    }
}
