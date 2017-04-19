<%--
  Created by IntelliJ IDEA.
  User: tianf
  Date: 2016/8/4
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Hello,World!</h1>

    <h2><a href="/rest/hello"></a></h2>

    <h2><a href="/rest/recordList"></a></h2>

<form action="/rest/mvc/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file"><br>
    <input type="submit" value="submit">
</form>


</body>
</html>
