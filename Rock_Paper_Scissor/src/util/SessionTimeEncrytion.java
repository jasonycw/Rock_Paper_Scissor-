package util;

public class SessionTimeEncrytion {
    public static String encrypt(String s){
        long temp = Long.parseLong(s);
        return String.valueOf(temp+100);
    }
}
