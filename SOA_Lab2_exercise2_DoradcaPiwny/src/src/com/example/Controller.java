package src.com.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.abs;

@javax.servlet.annotation.WebServlet(name = "src.com.example.controller")
public class Controller extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Model model = new Model();

        String beerColorInput = request.getParameter("beerColorInput");
        String beerType = "Nie ma takiego koloru piwa, spróbuj ponownie.";

        for (Map.Entry<String[], String[]> entry : model.beer.entrySet()) {
            String[] colors = entry.getKey();
            if (containsColor(colors, beerColorInput)) {
                beerType = drawBeer(entry.getValue());
                break;
            }
        }

        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/result.jsp?beerType="+beerType);
        rd.forward(request, response);
    }

    private boolean containsColor(String[] entries, String color) {
        for (String entry : entries) {
            if (entry.equals(color))
                return true;
        }
        return false;
    }

    private String drawBeer(String[] beerTypes) {
        Random random = new Random();
        int length = beerTypes.length;
        int rand = abs(random.nextInt());
        int index = rand % length;
        return beerTypes[index];
    }
}
