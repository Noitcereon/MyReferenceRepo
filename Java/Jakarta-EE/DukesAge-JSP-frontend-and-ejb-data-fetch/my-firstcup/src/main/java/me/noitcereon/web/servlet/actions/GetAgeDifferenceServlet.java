package me.noitcereon.web.servlet.actions;

import me.noitcereon.web.DukesBDay;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An action, which should retrieve the age difference between a given date and the Duke mascot.
 */
@WebServlet("/getAgeDifference")
public class GetAgeDifferenceServlet extends HttpServlet {

    @Inject
    private DukesBDay dukesBDay;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("You've accessed the doGet of the /getAgeDifference servlet");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println("You've accessed the doPost of the /getAgeDifference servlet");
            String userBirthday = req.getParameter("user-birthday");
            System.out.println("userBirthday attribute is: " + userBirthday);
            LocalDate date = LocalDate.parse(userBirthday, DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.println("dukesBDay: " + dukesBDay);
            dukesBDay.setYourBirthday(date);
            String redirectUrl = dukesBDay.processBirthday();
            System.out.println("dukesBDay: " + dukesBDay);
            req.setAttribute("DukesBDay", dukesBDay);
            req.getRequestDispatcher(redirectUrl)
                    .forward(req, resp);
        } catch (Exception e) {
            System.err.println("Something went wrong in GetAgeDifferenceServlet doPost. Printing stacktrace.");
            e.printStackTrace();
        }
    }
}
