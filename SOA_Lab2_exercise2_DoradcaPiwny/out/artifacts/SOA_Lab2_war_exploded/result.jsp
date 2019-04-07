<%--
  Created by IntelliJ IDEA.
  User: Patrycja
  Date: 2019-03-16
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ekspert piwny</title>
</head>
<body>
<h2>Wypróbuj tego:</h2><br>
    <%
        String beer = request.getParameter("beerType");
        out.println(beer);
    %>
</body>
</html>
