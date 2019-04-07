<%--
  Created by IntelliJ IDEA.
  User: Patrycja
  Date: 2019-03-16
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String error = request.getParameter("error");
        out.println(error);
    %>
<form action="/form.html" method="get">
    <input type="submit" value="Try again">
</form>
</body>
</html>
