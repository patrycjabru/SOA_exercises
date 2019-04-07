package src.com.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet(name = "GuestServlet")
public class GuestServlet extends HttpServlet {
    Vector<GuestData> guests = new Vector<GuestData>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GuestData guest = new GuestData();
        guest.name = request.getParameter("name");
        guest.email = request.getParameter("email");
        guest.comment = request.getParameter("comment");

        guests.add(guest);

        request.setAttribute("guests", guests);

        request.getRequestDispatcher("/guests.jsp").forward(request, response);
    }
}
