<%-- 
    Document   : welcome.jsp
    Created on : May 23, 2024, 5:05:20 PM
    Author     : Mkh_A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <link rel="stylesheet" href="welcome.css">
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
        <div class="content1">
    <h1>Welcome, <%= session.getAttribute("name") %>! to the Pet Adoption Portal</h1>
    <p class="pl"><u>Click on the menu icon to navigate the portal</u></p>
    <p class="ani">The animals would be so happy to see you! </p>
  </div>
       <script src="portal.js"></script>
    </body>
    
</html>
