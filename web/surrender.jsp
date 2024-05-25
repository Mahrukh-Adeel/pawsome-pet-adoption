<%-- 
    Document   : surrender
    Created on : May 24, 2024, 9:15:46 PM
    Author     : Mkh_A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Surrender</title>
        <link rel="stylesheet" href="welcome.css">
        <link rel="stylesheet" href="surrender.css">
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

  <div class="sidebar" id="sidebar" style="margin-bottom: 10%">
    <ul>
       <li><a href="ViewAdoptPetsServlet">Adopt</a></li>
      <li><a href="ViewMyPetsServlet2">Return</a></li>
      <li><a href="ViewMyPetsServlet">Yours</a></li>
      <li><a href="surrender.jsp">Surrender</a></li>
      <li><a href="account.jsp">Account</a></li>
    </ul>
      
  </div>
      <div style="padding-top: 20%">
          <form class="form" action="LeavePetServlet" method="post">
            <span class="heading">Leave Pet</span>
            <span class="Name">Name</span>
            <input name="name" placeholder="Enter Name" type="text" class="input" />
            <span class="Age">Age</span>
            <input name="age" placeholder="Enter Age" type="numbers" class="input" />
            <span class="Species">Species</span>
            <input name="species" placeholder="Enter Species" type="text" class="input" />
            <span class="Breed">Breed</span>
            <input name="breed" placeholder="Enter Breed" type="text" class="input" />
            <span class="Description">Description</span>
            <input name="description" placeholder="Enter Detail" type="text" class="input" />
            <span class="Img">Image</span>
            <input name="img" placeholder="Enter image URL" type="text" class="input" />
<!--            avalaible set by servlet-->
            <button>Submit</button>
          </form>
      </div>
       <script src="portal.js"></script>
    </body>
    
</html>
