<%-- 
    Document   : return
    Created on : May 24, 2024, 6:58:12 PM
    Author     : Mkh_A
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View pet</title>
        <link rel="stylesheet" href="adopt.css">
    <link rel="stylesheet" href="welcome.css">
    <link rel="stylesheet" href="pets.css"><!--
-->    <link rel="stylesheet" href="style.css">
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
        <section class="pet" id="cardss" style="margin-top: 10%">
            <%
        Map<Integer, Map<String, Object>> pets = (Map<Integer, Map<String, Object>>) request.getAttribute("myPets");

        if (pets != null) {
            for (Map.Entry<Integer, Map<String, Object>> entry : pets.entrySet()) {
                Map<String, Object> pet = entry.getValue();
                int petId = (Integer) entry.getKey();

                String img = (String) pet.get("img");
                
                String species = (String) pet.get("species");
                String name = (String) pet.get("name");
                int age = (Integer) pet.get("age");
                String description = (String) pet.get("description");
                boolean available = (Boolean) pet.get("available");
    %>
        <div class="card" style="width:auto">
            <div class="card-image" style="background-image: url('<%= img %>');"></div>
            <div class="category"><%= species %></div>
            <div class="heading"><%= name %>
<div class="author"> Age: <span class="name"><%= age %></span></div>
                <div class="author"> Available: <span class="name"><%= available ? "Yes" : "No" %></span></div>
            </div>
            <div class="heading">Description: <%= description %></div>
            <div class="button-container">
                    <form action="ReturnPetServlet" method="post">
                        <input type="hidden" name="petId" value="<%= petId %>">
                        <button type="submit" class="bttn">Return me</button>
                    </form>
                </div>
        </div>
    <%
            }
        } else {
            out.println("<p>No pets available.</p>");
        }
    %>
       </section>
<script src="portal.js"></script>
    </body>
    
</html>

