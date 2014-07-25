package cs4280.bean;

public class AckBean {
    private String mMessage;
    public static final String disclaimer = "This web site exists to fulfill the coursework requirement of CS4280.\nDo not use your real personal data as input.";

    public AckBean() {
        this.mMessage = "";
    }

    public AckBean(String mMessage) {
        this.mMessage = mMessage;
    }

    public void setmMessage(String mMessage) {

        if (mMessage != null) {
            this.mMessage = mMessage;
        }
    }

    private String generateHTML(String message) {
        if (message.equals("")) {
            return message;
        } else {
            return "<div class=\"ack-box\"><p>" + message + "</p><div class=\"ack-progress\"></div></div>";
        }
    }

    public String getHTMLOutput() {
        String realMsg = generateHTML(mMessage);
        setmMessage("");
        return realMsg + generateHTML(disclaimer);
    }

    public static String getBeanName() {
        return "ackMsg";
    }
}
