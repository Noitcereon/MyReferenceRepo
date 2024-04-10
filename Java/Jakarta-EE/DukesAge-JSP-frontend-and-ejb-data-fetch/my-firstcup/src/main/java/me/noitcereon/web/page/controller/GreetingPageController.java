package me.noitcereon.web.page.controller;

import me.noitcereon.web.JspPages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/greetingPage")
public class GreetingPageController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try{
            handleRequest(request, response);
        }
        catch (Exception e){
            System.err.println("Something went wrong in greetingPage doGet: " + e.getMessage());
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            handleRequest(request, response);
        }
        catch (Exception e){
            System.err.println("Something went wrong in greetingPage doPost: " + e.getMessage());
        }
    }
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(JspPages.GREETING_PAGE);
        request.setAttribute("noitcereon", "Noitcereon");

        dispatcher.forward(request, response);
    }
}
