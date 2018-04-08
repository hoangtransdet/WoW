<%--
  Created by IntelliJ IDEA.
  User: Nathan
  Date: 3/13/2016
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel=stylesheet type="text/css" href="login.css">
</head>
<body>
    <div id="backgroundImage">
        <%--<form action="LoginServlet" method="post" id="loginForm">--%>
        <form action="LoginServlet" method="post" id="loginForm">
            <input type="text" name="userID" placeholder="User Name"/>
            <br><input type="password" name="password" id="logInPassword" placeholder="Password"/>
            <br><input type="submit"/>
        </form>
    </div>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="login.js"></script>

</body>
</html>
