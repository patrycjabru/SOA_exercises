<%@ page import="java.util.Vector" %>
<%@ page import="src.com.example.GuestData" %><%--
  Created by IntelliJ IDEA.
  User: Patrycja
  Date: 2019-03-17
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guests</title>
</head>
<body>
    <h2>Please submit your feedback</h2>
    <form action="/guestServlet/" method="get">
        Name: <input type="text" name="name"><br>
        Email: <input type="text" name="email"><br>
        Comment: <input type="text" name="comment"><br>
        <input type="submit"><br>
    </form>
    <br>
    <%
        Vector<GuestData> guests = (Vector<GuestData>)request.getAttribute("guests");
        if (guests != null) {
            for (GuestData guest : guests) {
                out.print("Name: " + guest.name);
                out.print("<br/>");
                out.print("Email: " + guest.email);
                out.print("<br/>");
                out.print("Comment: " + guest.comment);

                out.print("<br/>");
                out.print("<br/>");
            }
        }

    %>
</body>
</html>
