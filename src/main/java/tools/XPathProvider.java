package tools;

public class XPathProvider {

    // Form XPaths:
    public static final String DATE_XPATH = "(//input[contains(@type,'textArea')])[1]";
    public static final String YEAR_XPATH = "(//select[contains(@tabindex,'-1')])[1]";
    public static final String OPERATOR_XPATH = "(//select[contains(@tabindex,'-1')])[2]";
    public static final String DOC_XPATH = "(//input[contains(@type,'textArea')])[2]";
    public static final String LOC_XPATH = "(//select[contains(@tabindex,'-1')])[3]";
    public static final String INTERN_XPATH = "(//select[contains(@tabindex,'-1')])[4]";
    public static final String INITIALS_XPATH = "(//input[contains(@type,'textArea')])[3]";
    public static final String SEX_XPATH = "(//select[contains(@tabindex,'-1')])[5]";
    public static final String PROCEDURE_XPATH = "(//input[contains(@type,'textArea')])[5]";

    // Nav XPaths:
    public static final String ADD_XPATH = "(//button[contains(@type,'button')])[37]";
    public static final String USERNAME_HTML_NAME = "username";
    public static final String PASSWORD_HTML_NAME = "password";
    public static final String LOGIN_HTML_NAME = "login";

}
