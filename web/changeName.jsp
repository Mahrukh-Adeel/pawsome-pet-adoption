<%-- 
    Document   : changeName
    Created on : May 23, 2024, 7:49:22 PM
    Author     : Mkh_A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change name</title>
        <link rel="stylesheet" href="welcome.css">
        <link rel="stylesheet" href="account.css">
        <link rel="stylesheet" href="change.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:wght@100;400;900&family=Jersey+10&family=Kanit:wght@100;900&family=Pixelify+Sans:wght@400;700&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="box">
            
        <form class="form" action="ChangeNameServlet" method="post" >
            <span class="heading">Change name</span>
        <label class="newN" for="newName">New Name:</label>
        <input class="input" type="text" id="newName" name="newName" required>
        <button type="submit">Change Name</button>
    </form>
    <c:if test="${param.error != null}">
        <p style="color: red;">${param.error}</p>
    </c:if>
    <c:if test="${param.success != null}">
        <p style="color: green;">${param.success}</p>
    </c:if>
    </body>
</html>
