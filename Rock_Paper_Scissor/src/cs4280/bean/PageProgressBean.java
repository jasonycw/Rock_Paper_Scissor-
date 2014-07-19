package cs4280.bean;

public class PageProgressBean {
    private String mBreadcrumb = "/main";
    public static final String sBeanName = "pageInfo";
    private boolean isLoggedIn = false;

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getmBreadcrumb() {
        return mBreadcrumb;
    }

    public void setmBreadcrumb(String mBreadcrumb) {
        this.mBreadcrumb = mBreadcrumb;
    }
}
