package util;

public class SessionTimeConversion {
    public static String encrypt(String s){
        long temp = Long.parseLong(s);
        return String.valueOf(temp+100);
    }
    public static String decrypt(String s){
        long temp = Long.parseLong(s);
        return String.valueOf(temp-100);
    }
}
