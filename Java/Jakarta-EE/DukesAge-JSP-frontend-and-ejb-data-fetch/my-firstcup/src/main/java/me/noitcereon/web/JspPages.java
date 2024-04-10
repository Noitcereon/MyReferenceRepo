package me.noitcereon.web;

/**
 * A catalogue of the available JSP pages you can forward to.
 */
public class JspPages {
    private JspPages() {
        // Don't instantiate.
    }

    private static final String PAGE_DIR = "/pages";

    public static final String LANDING_PAGE = "/";
    public static final String TEMP_HTML_TEST_PAGE = PAGE_DIR + "/tmp.html";
    public static final String GREETING_PAGE = PAGE_DIR + "/greetingPage.jsp";
}
