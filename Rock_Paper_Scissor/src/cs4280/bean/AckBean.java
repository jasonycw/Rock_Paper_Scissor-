package cs4280.bean;

public class AckBean {
    private String mMessage;
    public static final String disclaimer = "This web site exists to fulfill the coursework requirement of CS4280.\nDo not use your real personal data as input.";

    public AckBean() {
        this.mMessage = disclaimer;
    }

    public AckBean(String mMessage) {
        this.mMessage = mMessage;
    }

    public void setmMessage(String mMessage) {

        if (mMessage != null) {
            this.mMessage = mMessage;
        }
    }

    public String getHTMLOutput() {
        String temp = "<div class=\"ack-box\"><p>" + mMessage + "</p><div class=\"ack-progress\"></div></div>";
        setmMessage(disclaimer);
        return temp;
    }

    public static String getBeanName() {
        return "ackMsg";
    }
}
