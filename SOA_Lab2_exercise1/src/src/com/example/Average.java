package src.com.example;

import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(name = "src.com.example.average")
public class Average extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1250");
        PrintWriter out = response.getWriter();
        String firstInput = request.getParameter("firstInput");
        float firstNumber = Float.parseFloat(firstInput);
        String secondInput = request.getParameter("secondInput");
        float secondNumber = Float.parseFloat(secondInput);
        String thirdInput = request.getParameter("thirdInput");
        float thirdNumber = Float.parseFloat(thirdInput);
        String forthInput = request.getParameter("forthInput");
        float forthNumber = Float.parseFloat(forthInput);
        String fifthInput = request.getParameter("fifthInput");
        float fifthNumber = Float.parseFloat(fifthInput);
        float average = (firstNumber + secondNumber + thirdNumber + forthNumber + fifthNumber)/5;
        out.println("<html>");
        out.println("<head><title>Average</title></head>");
        out.println("<body>");
        out.println("<p>Average: " + average + "</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
