# Java EE 8 First Cup Tutorial

This project is Noitcereon's implementation of the First Cup tutorial.

The project includes a Web API and a frontend:

* Web API: my-dukes-age
    - my-dukes-age-ear is just to generate an .ear deployment file, which encapsulates the .war file generated by my-dukes-age.
* Frontend: my-firstcup


## Install
- Prerequisites:
  * Java 8
  * Apache Maven 3.9.2+
  * npm 10+ (or pnpm)

1. Install npm dependencies 
   1.1 `cd java-ee8-firstcup-tutorial\my-firstcup\src\main\webapp`
   1.2 `npm install`
2. Compile tailwindcss: 
  2.1 `npx tailwindcss -i ./assets/css/tailwind.css -o ./assets/css/tailwindCompiled.css --watch`
      * --watch is optional. It reloads when it sees changes in css class usage in components defined in [tailwind.config.js](my-firstcup/src/main/webapp/tailwind.config.js)
3. Install with Maven (generates deployment artifacts (.war files)
  3.1 `cd ../../../` (assuming you did the other steps. You need to be at the root of the project.)
  3.2 `mvn clean install`

### Deployment

> Note: I've used WebLogic 12c to work with this, but other Java EE compliant application servers should work as well.

1. Deploy to WebLogic or similar Application Server.

See the convenience scripts:

- [copy-to-autodeploy](example-copy-to-autodeploy.sh)
- [remove-from-autodeploy](example-remove-from-autodeploy.sh)

## Usage
- Frontend should be accessible at `host:port/my-firstcup-1.0-SNAPSHOT/`
- Backend should be accessible at `host:port/my-dukes-age-1.0-SNAPSHOT/`

The app uses some configuration files via ConfigurationHelper.java:
- noitcereon.conf (should be in the application server domain)
- reference.conf (fallback/default values)

### Documentation
Throughout the code there will be scattered comments to explain what's going on, however it is fairly limited in scope (far from everywhere).

I will here explain what this project is and what I've tried to practice, so future me can understand the what, why and how of this project.

TODO: write better version of summary
Summarised:
- I wanted to understand Java EE (such as EJBs, JSP, Servlets and Dependency Injection from Application Server)
- Found the Java EE tutorial firstcup https://javaee.github.io/firstcup/ (I know it's outdated, but figured I could still learn from it)
- How to make WebApi and access it
- How to make Java frontend with JSP and get data into it (see [JSP flow](#jsp-flow))
- How to change context path (.ear file deployment or change file name of deployment file)
- Internationalization (select language in app. At the time of writing this was never implemented in the app, but it has someting to do with MessageBundle and .properties files)

#### JSP flow
- So in summary:
    * JSP page with html form (with `name` attribute on input tags) sends an action to an HttpServlet
    * The HttpServlet uses @Inject to get a Bean object, which can hold the data retrieved from the form.
        * @Inject is used, so that the EJB dependency (`@Stateless public class DukesBirthdayBean`) inside the DukesBDay bean can be retrieved from Weblogic. If DukesBDay is instantiated witout depdendency injection the EJB private field is null.
        * ```java
            @Inject // Inject can be used for POJOs and Java Beans
            private DukesBDay dukesBDay;
            ```
    * The HttpServlet reads the parameters from the HttpServletRequest and puts them in the Bean (DukesBDay)
        * ```java
            String userBirthday = req.getParameter("user-birthday"); // user-birthday is value from the name attribute in the html form
            LocalDate date = LocalDate.parse(userBirthday, DateTimeFormatter.ISO_LOCAL_DATE);
            dukesBDay.setYourBirthday(date);
            ```
    * A method on the DukesBDay bean is called to perform business logic.
    * We set the DukesBDay bean object as an attribute before forwarding to a new page in the frontend
        * `req.setAttribute("DukesBDay", dukesBDay);`
    * The jsp page uses the attribute via: <jsp:useBean id="DukesBDay" class="me.noitcereon.web.DukesBDay" scope="session"/>
        * ```jsp
            <p>Duke is <span class="pink-500">${DukesBDay.getAge()}</span> years old</p>
            <c:if test="${DukesBDay.yourBirthday != null}">
                The age difference between the submitted date and the duke is: ${DukesBDay.getAgeDiff()}
            </c:if>
            ```
- The flow is then: GreetingPageController.doGet -> greetingPage.jsp -> GetAgeDifferenceServlet.doPost -> greetingPage.jsp


## Maintainer

- Noitcereon