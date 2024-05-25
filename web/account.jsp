<%-- 
    Document   : account
    Created on : May 23, 2024, 6:56:58 PM
    Author     : Mkh_A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link rel="stylesheet" href="welcome.css">
        <link rel="stylesheet" href="account.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:wght@100;400;900&family=Jersey+10&family=Kanit:wght@100;900&family=Pixelify+Sans:wght@400;700&display=swap" rel="stylesheet">
    </head>
    <body>
        <header class="header">
    <div class="header_in">
      <button type="button" class="toggle" id="button">
        <span></span>
      </button>
    </div>
            <button class="comic-button" onclick="logout()">Log Out!</button>
  </header>

  <div class="sidebar" id="sidebar">
    <ul>
      <li><a href="ViewAdoptPetsServlet">Adopt</a></li>
      <li><a href="ViewMyPetsServlet2">Return</a></li>
      <li><a href="ViewMyPetsServlet">Yours</a></li>
      <li><a href="surrender.jsp">Surrender</a></li>
      <li><a href="account.jsp">Account</a></li>
    </ul>
  </div>
        <div class="box">
            <span class="heading">Account details</span>
            <div>
                <p><b>Your Name is:</b> <%= session.getAttribute("name") %><p>
                <p><b>Your Session ID is:</b> <%= session.getAttribute("sessionId") %></p>
                <p><b>Your E-mail is:</b> <%= session.getAttribute("email") %></p>
            </div>
            <div class="btns">
                <button class="a-btn" onclick="toPass()">Change Password</button>
                <button class="a-btn" onclick="toName()">Change Name</button>
                <button class="a-btn" onclick="deleteA()">Delete Account</button>
            </div>
        </div>
        <script src="portal.js"></script>
    </body>
</html>
