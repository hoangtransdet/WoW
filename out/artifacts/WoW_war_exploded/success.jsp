<%@ page import="Website.User" %><%--
  Created by IntelliJ IDEA.
  User: Nathan
  Date: 3/13/2016
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
    <link rel=stylesheet type="text/css" href="success.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
    <script src="http://d3js.org/d3.v3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="jquery.tipsy.js"></script>
    <link rel="stylesheet" type="text/css" href="d3LineChart.css">
    <link href="tipsy.css" rel="stylesheet" type="text/css" />

    <script>
        $(document).ready(function() {
            $('#itemButton').click(function(event) {
                var $itemRequest = $("#itemRequest").val();
                $.get('WoWItemServlet',{itemName:$itemRequest},function(responseJson) {
                    var data = [];
                    $.each(responseJson, function(date, value) {
                        data.push({'value': parseInt(value), 'date': new Date(date)});
                    });
                    draw(data);
                });
            });
        });
    </script>

    <%--<script src="d3LineChart.js" type="text/javascript"></script>--%>
</head>
<body id="successBackground">
    <h3>Login success!</h3>
    <input id="itemRequest" type="text" name="item" placeholder="Input Item"/><br>
    <button id="itemButton">Request Item</button><br>
    <%--<button id="button">Redraw</button>--%>

    <%--<form action="/LoginServlet" method="post" id="loginForm">  //TODO action needs fix--%>
        <%--<input type="text" name="item" placeholder="Input Item"/>--%>
        <%--&lt;%&ndash;<button id="itemButton">Request Item</button><br>&ndash;%&gt;--%>
        <%--<br><input type="submit" value="Request Item">--%>
    <%--</form>--%>


    <div id="chart"/>

<%--<%--%>
    <%--User user = (User) request.getAttribute("user");--%>
<%--%>--%>

<%--Hello <%=user.getUserName()%>!--%>

    <script src="d3LineChart.js" type="text/javascript"></script>
    <%--<script src="wowLineGraph.js" type="text/javascript"></script>--%>

</body>
</html>
