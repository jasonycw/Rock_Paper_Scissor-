package util;

public interface PageURL {
    public String sRedirectServletURL = "/redirect";

    public String sLoginServletURL = "/login";
    public String sLoginJSPURL = "/WEB-INF/pages/LoginPage.jsp";

    public String sValidateIdentityServletURL = "/validate";

    public String sMainServletURL = "/main";
    public String sMainJSPURL = "/WEB-INF/pages/MainPage.jsp";

    public String sProfileServletURL = "/profile";
    public String sProfileJSPURL = "/WEB-INF/pages/UserProfilePage.jsp";

    public String sGameServletURL = "/game";
    public String sGameJSPURL = "/WEB-INF/pages/GamePage.jsp";
    public String sGameResultJSPURL = "/WEB-INF/pages/GameResultPage.jsp";

    public String sRecordServletURL = "/record";
    public String sRecordJSPURL = "/WEB-INF/pages/RecordPage.jsp";

    public String sLogoutServletURL = "/logout";
}
