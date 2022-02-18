<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 02/17/2022
  Time: 10:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<h1>${name}</h1>--%>
<form action="/home/greeting" method="post">
    <input type="text" name="name">
    <input type="text" name="price">
    <button type="submit">Submit</button>
</form>
</body>
</html>
