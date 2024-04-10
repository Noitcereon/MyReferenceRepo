<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- For internationalization (show app in different languages) -->

<c:set value="Thomas" var="myVariable"/>
<jsp:useBean id="DukesBDay" class="me.noitcereon.web.DukesBDay" scope="session"/>
<c:set var="ageDiffAction" value="${pageContext.request.contextPath}/getAgeDifference" scope="page"/>
<c:set var="noitcereonAttribute" value="${pageContext.request.getAttribute('noitcereon')}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Greeting Page</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon/favicon.ico"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tailwindCompiled.css" />
    </head>
    <body>
        <header class="grid grid-cols-3">
            <div class=""></div>
            <div class="grid text-blue-800 justify-center">
                <h1>Greetings Page!</h1>
            </div>
            <div class=""></div>
        </header>
        <main class="grid p-2 mx-2 bg-orange-50">
            <form action="${ageDiffAction}" method="post">
                <div>
                    <label for="user-birthday" class="text-blue-500" >Enter Your Birthday: </label>
                    <input type="date" id="user-birthday" name="user-birthday" value="2000-01-01"> <!-- yyyy-MM-dd -->
                </div>
                <button type="submit" class="bg-blue-700 rounded border-gray-700 text-amber-50 p-1">Submit</button>
            </form>
            <c:choose>
                <c:when test="${myVariable == 'Thomas'}">
                    <div>JSTL works! myVariable value=${myVariable}</div><br>
                    <c:if test="${noitcereonAttribute.toLowerCase() == 'noitcereon'}">
                        <div>"noitcereon" attribute value: ${noitcereonAttribute}</div>
                    </c:if>
                    <p>Duke is <span class="pink-500">${DukesBDay.getAge()}</span> years old</p>
                    <c:if test="${DukesBDay.yourBirthday != null}">
                       The age difference between the submitted date and the duke is: ${DukesBDay.getAgeDiff()}
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div>JSTL doesn't work (or I didn't set the myVariable correctly)</div>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>