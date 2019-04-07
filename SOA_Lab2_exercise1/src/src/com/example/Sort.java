package src.com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "Sort")
public class Sort extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1250");
        PrintWriter out = response.getWriter();

        List<Float> list = new ArrayList<Float>();

        Enumeration parametres = request.getParameterNames();
        while (parametres.hasMoreElements()) {
            String paramName = parametres.nextElement().toString();
            String param = request.getParameter(paramName);
            float number = (float)0.0;
            try {
                number  = Float.parseFloat(param);
            } catch (Exception e) {
                out.println("<html>");
                out.println("<head><title>Sort</title></head>");
                out.println("<body>");
                out.println("Invalid arguments: all argument have to be numbers!");
                out.println("</body>");
                out.println("</html>");
                out.close();
                return;
            }
            list.add(number);
        }
        Collections.sort(list);

        out.println("<html>");
        out.println("<head><title>Sort</title></head>");
        out.println("<body>");
        for (float n:list) {
            out.print(n + " ");
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
