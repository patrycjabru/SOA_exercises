package src.com.example;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

@javax.servlet.annotation.WebServlet(name = "src.com.example.average")
public class Servlet extends javax.servlet.http.HttpServlet {
    Vector<PersonalData> users = new Vector<PersonalData>();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1250");
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("")) {
            String error = "Login cannot be empty";
            redirectToTryAgain(error,request,response);
        }

        if (password.equals("")) {
            String error = "Password cannot be empty";
            redirectToTryAgain(error,request,response);
        }

        if (!checkLogin(login,password)) {
            String error = "Invalid login or password";
            redirectToTryAgain(error,request,response);
        }

        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/guests.jsp");
        rd.forward(request, response);
    }

    private boolean checkLogin(String login, String password) {
        if (users.isEmpty())
            initUsers();
        for (PersonalData data:users) {
            if (data.login.equals(login))
                return data.password.equals(password);
        }
        return false;
    }

    private void redirectToTryAgain(String error, ServletRequest request, ServletResponse response) throws ServletException, IOException {
        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/tryAgain.jsp?error=" + error);
        rd.forward(request, response);
    }

    private void initUsers() {
        PersonalData data = new PersonalData("test","test","testName","testSurname");
        users.add(data);
    }
}
